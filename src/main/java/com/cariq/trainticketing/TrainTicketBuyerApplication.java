package com.cariq.trainticketing;

import com.cariq.trainticketing.client.BartAPI_new;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class TrainTicketBuyerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainTicketBuyerApplication.class, args);
	}
}