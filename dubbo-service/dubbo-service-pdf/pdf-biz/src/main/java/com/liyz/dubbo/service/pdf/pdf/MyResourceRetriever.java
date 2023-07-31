package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.styledxmlparser.resolver.resource.DefaultResourceRetriever;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/5/30 18:56
 */
@Slf4j
public class MyResourceRetriever extends DefaultResourceRetriever {
    private String classPathPrefix;
    private volatile CloseableHttpClient httpClient;
    public MyResourceRetriever(String classPathPrefix) {
        Assert.hasText(classPathPrefix, () -> "classpath前缀路径不能为空");
        this.classPathPrefix = classPathPrefix;
    }
    private void initHttp(){
        if (httpClient == null) {
            synchronized (MyResourceRetriever.class){
                if (httpClient == null){
                    CloseableHttpClient closeableHttpClient = HttpClientBuilder.create()
                            .setDefaultRequestConfig(
                                    RequestConfig.custom().setConnectionRequestTimeout(3000).setConnectTimeout(3000).setSocketTimeout(10000).build()
                            ).disableRedirectHandling()
                            .evictExpiredConnections()
                            .evictIdleConnections(60, TimeUnit.MILLISECONDS)
                            .disableAutomaticRetries()
                            .build();
                    httpClient = closeableHttpClient;
                }
            }
        }
    }

    @Override
    public InputStream getInputStreamByUrl(URL url) throws IOException {
        String uri = url.toExternalForm();
        if(uri.startsWith("http://") || uri.startsWith("https://")){
            return getInputStream(uri);
        }
        return new ClassPathResource(uri.substring(uri.lastIndexOf(classPathPrefix))).getInputStream();
    }

    private InputStream getInputStream(String uri) throws IOException {
        initHttp();
        try (CloseableHttpResponse response = httpClient.execute(new HttpGet(uri))) {
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()){
                return new ByteArrayInputStream(FileCopyUtils.copyToByteArray(response.getEntity().getContent()));
            }else{
                log.warn("请求资源失败：{}，响应code：{}", uri, response.getStatusLine());
                return default404InputStream();
            }
        }catch (Exception e){
            log.warn("请求资源失败："+uri, e);
            return default404InputStream();
        }
    }

    private InputStream default404InputStream() throws IOException {
        // 404图片
        return new ClassPathResource("templates/img/404.png").getInputStream();
    }
}
