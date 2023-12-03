package com.webcrawling.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
    //@Value("${elasticsearch.host}")
    private String host;
    //@Value("${elasticsearch.port}")
    private int port;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
    }
}