package com.example.BalisongFlipping;

import com.example.BalisongFlipping.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class BalisongFlippingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalisongFlippingApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(AccountRepository accountRepository, MongoTemplate mongoTemplate) {
		return args -> {
			accountRepository.deleteAll();
		};
	}
}
