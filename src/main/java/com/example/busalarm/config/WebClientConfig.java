package com.example.busalarm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class WebClientConfig {
    @Value("${bus.stop.api.url}")
    private String busStopUrl;

    @Bean
    public WebClient webClient() {
        MultiValueMap<String, Object> m = new LinkedMultiValueMap<>();
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(busStopUrl);
        factory.setDefaultUriVariables(m);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.TEMPLATE_AND_VALUES);
        return WebClient.builder()
                .uriBuilderFactory(factory)
                .build();
    }
}
