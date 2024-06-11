package com.example.BalisongFlipping.services;

import com.example.BalisongFlipping.dtos.AdminDto;
import com.example.BalisongFlipping.dtos.MakerDto;
import com.example.BalisongFlipping.dtos.UserDto;
import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.accounts.Admin;
import com.example.BalisongFlipping.modals.accounts.Maker;
import com.example.BalisongFlipping.modals.accounts.User;
import com.example.BalisongFlipping.repositories.AccountRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public static Record converAccountToDto(Account account) throws Exception {
        switch(account.getRole()) {
            case ADMIN -> {
                Admin admin = (Admin) account;

                return new AdminDto(
                        account.getUuid(),
                        account.getEmail(),
                        account.getRole(),
                        account.getPosts()
                );

            }
            case MAKER -> {
                Maker admin = (Maker) account;
                return new MakerDto(
                        account.getUuid(),
                        account.getEmail(),
                        ((Maker) account).getCompanyName(),
                        ((Maker) account).getCompanyDuration(),
                        account.getRole(),
                        account.getPosts(),
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
                        account.getUuid(),
                        account.getEmail(),
                        ((User) account).getDisplayName(),
                        account.getRole(),
                        account.getPosts(),
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

    public Record getAccountById(String passedID) throws Exception {
        Account foundAccount = accountRepository.findById(passedID).orElseThrow();
        return converAccountToDto(foundAccount);
    }
}