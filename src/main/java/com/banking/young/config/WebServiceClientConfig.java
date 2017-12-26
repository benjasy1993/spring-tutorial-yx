package com.banking.young.config;

import com.banking.young.clients.AccountServiceClient;
import com.banking.young.clients.AccountServiceClientErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebServiceClientConfig {

    @Bean
    @Lazy
    public AccountServiceClient accountServiceClient(RestTemplate restTemplate) {
        return new AccountServiceClient(restTemplate);
    }

    @Bean
    public RestTemplate restTemplate(AccountServiceClientErrorHandler handler) {
        RestTemplate template =  new RestTemplate();
        template.setErrorHandler(handler);
        return template;
    }

    @Bean
    public AccountServiceClientErrorHandler accountServiceClientErrorHandler() {
        return new AccountServiceClientErrorHandler();
    }
}
