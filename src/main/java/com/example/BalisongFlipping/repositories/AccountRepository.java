package com.example.BalisongFlipping.repositories;

import com.example.BalisongFlipping.modals.accounts.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    Optional<Account> findAccountByEmail(String email);
}
