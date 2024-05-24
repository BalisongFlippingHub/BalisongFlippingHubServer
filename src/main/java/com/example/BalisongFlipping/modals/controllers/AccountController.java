package com.example.BalisongFlipping.modals.controllers;

import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccounts() {
        try {
            List<Account> accounts = accountRepository.findAll();
            if (!accounts.isEmpty()) {
                return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
            }

            return new ResponseEntity<>("No accounts available", HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Couldn't access the DB", HttpStatus.NOT_FOUND);
        }
    }
}
