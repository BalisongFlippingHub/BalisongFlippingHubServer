package com.example.BalisongFlipping;

import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.accounts.User;
import com.example.BalisongFlipping.modals.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BalisongFlippingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalisongFlippingApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(AccountRepository accountRepository, MongoTemplate mongoTemplate) {
//		return args -> {
//			User u = new User(
//					"test@gmail.com",
//					"Display Name",
//					"Test Password",
//					new Date(),
//					new Date(),
//					Account.Role.USER
//			);
//
//			User u2 = new User(
//					"test2@gmail.com",
//					"Display Name",
//					"Test Password",
//					new Date(),
//					new Date(),
//					Account.Role.ADMIN
//			);
//
//			User u3 = new User(
//					"test3@gmail.com",
//					"Display Name",
//					"Test Password",
//					new Date(),
//					new Date(),
//					Account.Role.MAKER
//			);
//
//
//
//			Query query = new Query();
//			query.addCriteria(Criteria.where("email").is("test@gmail.com"));
//
//			List<Account> accounts = mongoTemplate.find(query, Account.class);
//			if (accounts.isEmpty()) {
//				System.out.println("Adding user...");
//				accountRepository.insert(u);
//			}
//			else {
//				System.out.println("User already exists.");
//			}
//		};
//	}
}
