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
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

       Account currentAccount = (Account) authentication.getPrincipal();
       AccountDto accountDto;
       if (currentAccount.getRole() == Account.Role.ADMIN) {
           Admin adminAccount = new Admin(currentAccount);

           accountDto = new AdminDto(
                   adminAccount.getUuid(),
                   adminAccount.getEmail(),
                   adminAccount.getAccountCreationDate(),
                   adminAccount.getLastLogin(),
                   adminAccount.getRole(),
                   adminAccount.getPosts()
           );
       }
       else if (currentAccount.getRole() == Account.Role.MAKER) {
            Maker makerAccount = new Maker(currentAccount);

            accountDto = new MakerDto(
                    makerAccount.getUuid(),
                    makerAccount.getEmail(),
                    makerAccount.getCompanyName(),
                    makerAccount.getCompanyDuration(),
                    makerAccount.getAccountCreationDate(),
                    makerAccount.getLastLogin(),
                    makerAccount.getRole(),
                    makerAccount.getPosts()
            );
       }
       else {
            User userAccount = new User(currentAccount);

            accountDto = new UserDto(
                    userAccount.getUuid(),
                    userAccount.getEmail(),
                    userAccount.getDisplayName(),
                    userAccount.getAccountCreationDate(),
                    userAccount.getLastLogin(),
                    userAccount.getRole(),
                    userAccount.getPosts()
            );
       }

       return ResponseEntity.ok(accountDto);
   }

   @GetMapping("/")
    public ResponseEntity<List<Account>> allUsers() {
       List<Account> accounts = accountService.allUsers();

       return ResponseEntity.ok(accounts);
   }
}
