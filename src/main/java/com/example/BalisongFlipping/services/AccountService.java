package com.example.BalisongFlipping.services;

import com.example.BalisongFlipping.dtos.AdminDto;
import com.example.BalisongFlipping.dtos.MakerDto;
import com.example.BalisongFlipping.dtos.UserDto;
import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.accounts.Admin;
import com.example.BalisongFlipping.modals.accounts.Maker;
import com.example.BalisongFlipping.modals.accounts.User;
import com.example.BalisongFlipping.repositories.AccountRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private static  JavaFSService javaFSService;

    public AccountService(AccountRepository accountRepository, JavaFSService javaFSService) {
        this.accountRepository = accountRepository;
        this.javaFSService = javaFSService;
    }

    public static Record converAccountToDto(Account account) throws Exception {
        switch(account.getRole()) {
            case ADMIN -> {
                Admin admin = (Admin) account;

                return new AdminDto(
                        account.getId(),
                        account.getEmail(),
                        account.getRole(),
                        account.getPosts()
                );

            }
            case MAKER -> {
                Maker admin = (Maker) account;
                return new MakerDto(
                        account.getId(),
                        account.getEmail(),
                        ((Maker) account).getCompanyName(),
                        ((Maker) account).getCompanyDuration(),
                        account.getRole(),
                        account.getPosts(),
                        javaFSService.getAsset(account.getBannerImg()),
                        javaFSService.getAsset(account.getProfileImg()),
                        ((Maker) account).getProducts(),
                        ((Maker) account).getServices(),
                        ((Maker) account).getFacebookLink(),
                        ((Maker) account).getInstagramLink(),
                        ((Maker) account).getTwitterLink(),
                        ((Maker) account).getEmailLink(),
                        ((Maker) account).getPersonalWebsiteLink()
                );
            }
            case USER -> {
                User user = (User) account;
                return new UserDto(
                        account.getId(),
                        account.getEmail(),
                        ((User) account).getDisplayName(),
                        account.getRole(),
                        account.getPosts(),
                        account.getBannerImg(),
                        account.getProfileImg(),
                        ((User) account).getOwnedKnives(),
                        ((User) account).getFacebookLink(),
                        ((User) account).getTwitterLink(),
                        ((User) account).getInstagramLink()
                );
            }
            default -> {
                throw new Exception("Failed to convert based on role type");
            }
        }
    }

    public Record getSelf() throws Exception {
        // get authentication context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // get authorized account from principle
        Account currentAccount = (Account) authentication.getPrincipal();

        return converAccountToDto(currentAccount);
    }

    public List<Account> allUsers() {
        List<Account> accounts = new ArrayList<>();

        accounts = accountRepository.findAll();

        return accounts;
    }

    public boolean updateProfileImg(String accountId, MultipartFile file)  {
        Optional<Account> foundAccount = accountRepository.findById(new ObjectId(accountId.substring(1, accountId.length() - 1)));

        if (foundAccount.isEmpty()) return false;

        try {
            String id = javaFSService.addAsset("Profile Img",file);
            foundAccount.get().setProfileImg(id);
            accountRepository.save(foundAccount.get());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}