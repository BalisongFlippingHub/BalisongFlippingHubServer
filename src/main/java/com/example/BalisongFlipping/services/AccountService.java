package com.example.BalisongFlipping.services;

import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> allUsers() {
        List<Account> accounts = new ArrayList<>();

        accountRepository.findAll();

        return accounts;
    }
}