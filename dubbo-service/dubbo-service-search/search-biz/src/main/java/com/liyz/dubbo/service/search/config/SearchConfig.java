package com.liyz.dubbo.service.search.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.google.common.collect.Lists;
import com.liyz.dubbo.common.util.JsonMapperUtil;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 14:52
 */
@Configuration
@EnableConfigurationProperties(ElasticsearchProperties.class)
public class SearchConfig {

    private final ElasticsearchProperties properties;

    public SearchConfig(ElasticsearchProperties properties) {
        this.properties = properties;
    }

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(this.restClientBuilder());
    }

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        JacksonJsonpMapper jacksonJsonpMapper = new JacksonJsonpMapper(JsonMapperUtil.getObjectMapper());
        ElasticsearchTransport transport = new RestClientTransport(this.restClientBuilder().build(), jacksonJsonpMapper);
        return new ElasticsearchClient(transport);
    }

    private RestClientBuilder restClientBuilder() {
        RestClientBuilder restClientBuilder = RestClient.builder(properties.getUris().stream().map(HttpHost::create).toArray(HttpHost[]::new));
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(properties.getUsername(), properties.getPassword()));
        restClientBuilder.setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
                .setDefaultCredentialsProvider(credentialsProvider)
                .setMaxConnTotal(100)
                .setMaxConnPerRoute(20)
                .setDefaultHeaders(Lists.newArrayList(new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString())))
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout((int) properties.getConnectionTimeout().getSeconds() * 1000)
                        .setSocketTimeout((int) properties.getSocketTimeout().getSeconds())
                        .setConnectionRequestTimeout(5000)
                        .setExpectContinueEnabled(true)
                        .setRedirectsEnabled(false)
                        .build())
                .addInterceptorLast((HttpResponseInterceptor) (response, context) -> response.addHeader("X-Elastic-Product", "Elasticsearch"))
        );
        return restClientBuilder;
    }
}
