package com.example.BalisongFlipping.controllers;

import com.example.BalisongFlipping.dtos.RegisterAccountDto;
import com.example.BalisongFlipping.dtos.UploadAccountAssetDto;
import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
       System.out.println("Updating Profile Img on account: " + accountId);
       System.out.println(("Passed File: " + file.getOriginalFilename()));

       // store image in JavaFS
       if (accountService.updateProfileImg(accountId, file)) {
           // return ok upon successful update of profile img
           System.out.println("Return true call for successfully storing file");
           return new ResponseEntity<>("Success", HttpStatus.OK);
       }
       else {
           // return bad request status for failed storage of file
           System.out.println("Return false call for failing to store file");
           return new ResponseEntity<>("Failed", HttpStatus.CONFLICT);
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
