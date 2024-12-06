package com.bank.CustomerMs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class RestTemplateConfig {

    @Value("${accountms.url}")
    private String accountMsUrl;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(accountMsUrl)
                .build();
    }
}