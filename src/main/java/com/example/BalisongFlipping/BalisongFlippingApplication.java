package com.example.BalisongFlipping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
