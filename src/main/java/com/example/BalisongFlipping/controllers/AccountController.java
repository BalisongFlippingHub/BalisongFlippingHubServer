package com.example.BalisongFlipping.controllers;

import com.example.BalisongFlipping.dtos.AccountDto;
import com.example.BalisongFlipping.dtos.AdminDto;
import com.example.BalisongFlipping.dtos.MakerDto;
import com.example.BalisongFlipping.dtos.UserDto;
import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.accounts.Admin;
import com.example.BalisongFlipping.modals.accounts.Maker;
import com.example.BalisongFlipping.modals.accounts.User;
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
    public ResponseEntity<?> authenticatedUser() {
       AccountDto account = accountService.getSelf();

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
