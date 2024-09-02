package com.wildpulse.commons.configurations.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }
}
