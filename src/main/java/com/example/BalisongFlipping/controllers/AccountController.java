package com.example.BalisongFlipping.controllers;
import com.example.BalisongFlipping.dtos.UserDto;
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

   @PostMapping("/me/change-display-name")
   public ResponseEntity<?> changeUsersDisplayName(@RequestBody String newDisplayName) throws Exception {
        String accountId =  accountService.getSelf().id();
        newDisplayName = newDisplayName.substring(0, newDisplayName.length() - 1);

        if (accountId.isEmpty()) {
            return new ResponseEntity<>("Failed to get account info", HttpStatus.NOT_FOUND);
        }

        try {
            return new ResponseEntity<>(accountService.changeDisplayName(accountId, newDisplayName), HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Failed", HttpStatus.CONFLICT);
        }
   }

   @PostMapping(value = "/me/update-profile-img", consumes = "multipart/form-data")
   public ResponseEntity<String> updateProfileImg(@RequestParam("file") MultipartFile file) throws Exception {
       String accountId =  accountService.getSelf().id();

       if (accountService.checkForAccountExistance(accountId)) {
           return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
       }

        String imgId = accountService.updateProfileImg(accountId, file);

       if (imgId.isEmpty()) {
           return new ResponseEntity<>("Failed to store img", HttpStatus.CONFLICT);
       }

       return new ResponseEntity<String>(imgId, HttpStatus.OK);
   }

    @PostMapping(value = "/me/update-banner-img", consumes = "multipart/form-data")
    public ResponseEntity<String> updateBannerImg(@RequestParam("file") MultipartFile file) throws Exception {
        String accountId =  accountService.getSelf().id();

        if (accountService.checkForAccountExistance(accountId)) {
            return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        }

        String imgId = accountService.updateBannerImg(accountId, file);

        if (imgId.isEmpty()) {
            return new ResponseEntity<>("Failed to store img", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<String>(imgId, HttpStatus.OK);
    }

   @GetMapping("/me/all")
    public ResponseEntity<?> allUsers() {
       System.out.println("Called.");
       List<Account> accounts = accountService.allUsers();

       return ResponseEntity.ok("Test");
   }
}
