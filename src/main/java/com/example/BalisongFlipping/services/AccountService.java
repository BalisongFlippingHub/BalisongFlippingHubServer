package com.example.BalisongFlipping.services;

import com.example.BalisongFlipping.dtos.AccountDto;
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

    private AccountDto convertToDto(Account account) {
        AccountDto accountDto;

        if (account.getRole() == Account.Role.ADMIN) {
            Admin adminAccount = new Admin(account);

            accountDto = new AdminDto(
                    adminAccount.getUuid(),
                    adminAccount.getEmail(),
                    adminAccount.getAccountCreationDate(),
                    adminAccount.getLastLogin(),
                    adminAccount.getRole(),
                    adminAccount.getPosts()
            );
        }
        else if (account.getRole() == Account.Role.MAKER) {
            Maker makerAccount = new Maker(account);

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
            User userAccount = new User(account);

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

        return accountDto;
    }

    public AccountDto getSelf() {
        // get authentication context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // get authorized account from principle
        Account currentAccount = (Account) authentication.getPrincipal();

        return convertToDto(currentAccount);
    }

    public List<Account> allUsers() {
        List<Account> accounts = new ArrayList<>();

        accounts = accountRepository.findAll();

        return accounts;
    }

    public AccountDto getAccountById(String passedID) {
        Account foundAccount = accountRepository.findById(passedID).orElseThrow();
        return convertToDto(foundAccount);
    }
}