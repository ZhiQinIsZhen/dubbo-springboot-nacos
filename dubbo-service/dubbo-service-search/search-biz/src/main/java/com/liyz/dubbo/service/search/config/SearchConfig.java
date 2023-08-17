package com.liyz.dubbo.service.search.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        RestClientBuilder restClientBuilder = RestClient.builder(properties.getUris().stream().map(HttpHost::create).toArray(HttpHost[]::new));
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(properties.getUsername(), properties.getPassword()));
        restClientBuilder.setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
                .setMaxConnTotal(100)
                .setMaxConnPerRoute(20)
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout((int) properties.getConnectionTimeout().getSeconds() * 1000)
                        .setSocketTimeout((int) properties.getSocketTimeout().getSeconds())
                        .setConnectionRequestTimeout(5000)
                        .setExpectContinueEnabled(true)
                        .setRedirectsEnabled(false)
                        .build())
        );
        return new RestHighLevelClient(restClientBuilder);
    }
}
