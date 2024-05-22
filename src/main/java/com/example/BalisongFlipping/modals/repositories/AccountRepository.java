package com.example.BalisongFlipping.modals.repositories;

import com.example.BalisongFlipping.modals.accounts.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    Optional<Account> findAccountByEmail(String email);
}
