package com.cariq.trainticketing.config;

import com.cariq.trainticketing.client.BartAPI_new;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TicketConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public BartAPI_new getBartAPI_new(@Autowired RestTemplate restTemplate) {
        return new BartAPI_new(restTemplate);
    }
}
