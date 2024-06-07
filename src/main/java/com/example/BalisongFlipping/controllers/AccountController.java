package com.example.BalisongFlipping.controllers;

import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.services.AccountService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> authenticatedUser() throws Exception {
       Record account = accountService.getSelf();

       if (account == null) {
           return (ResponseEntity<?>) ResponseEntity.notFound();
       }
       else {
           return ResponseEntity.ok(account);
       }
   }

   @GetMapping("/")
    public ResponseEntity<List<Account>> allUsers() {
       List<Account> accounts = accountService.allUsers();

       return ResponseEntity.ok(accounts);
   }

//   @GetMapping("/$userId")
//    public ResponseEntity<?> getUserById() {
//       AccountDto foundAccount = accountService.getAccountById();
//
//       if (foundAccount == null) {
//           return (ResponseEntity<?>) ResponseEntity.notFound();
//       }
//       else {
//           return ResponseEntity.ok(foundAccount);
//       }
//   }
}
