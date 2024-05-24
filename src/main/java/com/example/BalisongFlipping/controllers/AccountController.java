package com.example.BalisongFlipping.controllers;

import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.repositories.AccountRepository;
import com.example.BalisongFlipping.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/accounts")
@RestController
public class AccountController {

   private final AccountService accountService;

   public AccountController(AccountService accountService) {
       this.accountService = accountService;
   }

   @GetMapping("/me")
    public ResponseEntity<Account> authenticatedUser() {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

       Account currentAccount = (Account) authentication.getPrincipal();

       return ResponseEntity.ok(currentAccount);
   }

   @GetMapping("/")
    public ResponseEntity<List<Account>> allUsers() {
       List<Account> accounts = accountService.allUsers();

       return ResponseEntity.ok(accounts);
   }
}
