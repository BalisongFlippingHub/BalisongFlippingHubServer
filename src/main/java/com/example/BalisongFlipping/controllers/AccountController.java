package com.example.BalisongFlipping.controllers;
import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

   @PostMapping(value = "/me/update-profile-img", consumes = "multipart/form-data")
   public ResponseEntity<String> updateProfileImg(@RequestParam("accountId") String accountId, @RequestParam("file") MultipartFile file) throws Exception {

       if (!accountService.checkForAccountExistance(accountId)) {
           return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
       }

        String imgId = accountService.updateProfileImg(accountId, file);

       if (imgId.isEmpty()) {
           return new ResponseEntity<>("Failed to store img", HttpStatus.CONFLICT);
       }

       return new ResponseEntity<String>(imgId, HttpStatus.OK);
   }

    @PostMapping(value = "/me/update-banner-img", consumes = "multipart/form-data")
    public ResponseEntity<String> updateBannerImg(@RequestParam("accountId") String accountId, @RequestParam("file") MultipartFile file) throws Exception {

        if (!accountService.checkForAccountExistance(accountId)) {
            return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        }

        String imgId = accountService.updateBannerImg(accountId, file);

        if (imgId.isEmpty()) {
            return new ResponseEntity<>("Failed to store img", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<String>(imgId, HttpStatus.OK);
    }

   @GetMapping("/")
    public ResponseEntity<List<Account>> allUsers() {
       List<Account> accounts = accountService.allUsers();

       return ResponseEntity.ok(accounts);
   }
}
