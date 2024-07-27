package com.shared.info.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.graphql.client.HttpGraphQlClient;

@Configuration
public class GraphqlClientConfig {

    @Value("${com.shared.info.url.greet-service}")
    private String greetService;

    @Bean(name = "greetService")
    public GraphQlClient greetService() {
        return HttpGraphQlClient.builder().url(greetService).build();
    }
}
