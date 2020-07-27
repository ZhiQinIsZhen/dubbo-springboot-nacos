package com.liyz.dubbo.service.file.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;
import java.util.Map;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;


public class HttpClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientService.class);

    private static PoolingHttpClientConnectionManager poolConnManager;
    private static final int maxTotalPool = 300;
    private static final int maxConPerRoute = 200;
    private static final int socketTimeout = 2000;
    private static final int connectionRequestTimeout = 3000;
    private static final int connectTimeout = 1000;

    static {
        try {
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null,
                    new TrustSelfSignedStrategy())
                    .build();
            HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,hostnameVerifier);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslsf)
                    .build();
            poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // Increase max total connection to 200
            poolConnManager.setMaxTotal(maxTotalPool);
            // Increase default max connection per route to 20
            poolConnManager.setDefaultMaxPerRoute(maxConPerRoute);
            SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(socketTimeout).build();
            poolConnManager.setDefaultSocketConfig(socketConfig);
        } catch (Exception e) {
            LOGGER.error("init http client pool err.",e);
        }
    }


    private static CloseableHttpClient getConnection(){
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
                .setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(poolConnManager).setDefaultRequestConfig(requestConfig).build();
        if(poolConnManager!=null&&poolConnManager.getTotalStats()!=null){
            LOGGER.debug("now client pool "+poolConnManager.getTotalStats().toString());
        }
        return httpClient;
    }


    public static String httpPost(String url, String jsonStr, Map<String, String> headers)
    {
        //参数检测
        if(url==null||"".equals(url))
        {
            return null;
        }
        HttpPost httpPost = new HttpPost(url);
        //这里创建一个默认的httpClient,线上调用需改成连接池
        CloseableHttpClient client = getConnection();
        String result = "";
        try {
            httpPost.setEntity(new StringEntity(jsonStr, APPLICATION_JSON));
            if(headers != null){
                for(Map.Entry<String,String> headerKV : headers.entrySet()){
                    httpPost.addHeader(headerKV.getKey(), headerKV.getValue());
                }
            }
            CloseableHttpResponse httpResponse = client.execute(httpPost);
            if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                result = EntityUtils.toString(httpResponse.getEntity(), Charset.forName("UTF-8"));
            }else{
                throw new RuntimeException("err response:" + httpResponse);
            }
            return result;
        } catch (Exception e) {
            httpPost.abort();
        }finally {
            httpPost.releaseConnection();
        }
        return null;
    }

}

