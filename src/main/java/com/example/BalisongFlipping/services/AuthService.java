package com.example.BalisongFlipping.services;

import com.example.BalisongFlipping.dtos.LoginAccountDto;
import com.example.BalisongFlipping.dtos.RegisterAccountDto;
import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.accounts.Admin;
import com.example.BalisongFlipping.modals.accounts.Maker;
import com.example.BalisongFlipping.modals.accounts.User;
import com.example.BalisongFlipping.repositories.AccountRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {
    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService(
            AccountRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.accountRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Account signup(RegisterAccountDto input) {
        if (input.role() == Account.Role.MAKER) {
            Maker maker = new Maker();
            maker.setEmail(input.email());
            maker.setPassword(passwordEncoder.encode(input.password()));
            maker.setRole(input.role());
            maker.setBannerImg("");
            maker.setProfileImg("");
            maker.setAccountCreationDate(new Date());
            maker.setLastLogin(new Date());
            maker.setCompanyName(input.accountName());
            maker.setCompanyDuration(0.0);
            maker.setPosts(new ArrayList<>());
            maker.setServices(new ArrayList<>());
            maker.setProducts(new ArrayList<>());
            maker.setFacebookLink("");
            maker.setInstagramLink("");
            maker.setPersonalWebsiteLink("");
            maker.setEmailLink("");
            maker.setTwitterLink("");

            Optional<Account> holder = accountRepository.findAccountByEmail(input.email());
            if (holder.isPresent()) {
                return null;
            }

            return accountRepository.save(maker);
        }
        else {
            User user = new User();
            user.setEmail(input.email());
            user.setPassword(passwordEncoder.encode(input.password()));
            user.setRole(input.role());
            user.setBannerImg("");
            user.setProfileImg("");
            user.setAccountCreationDate(new Date());
            user.setLastLogin(new Date());
            user.setDisplayName(input.accountName());
            user.setPosts(new ArrayList<>());
            user.setOwnedKnives(new ArrayList<>());
            user.setFacebookLink("");
            user.setInstagramLink("");
            user.setTwitterLink("");

            Optional<Account> holder = accountRepository.findAccountByEmail(input.email());
            if (holder.isPresent()) {
                return null;
            }

            return accountRepository.save(user);
        }
    }

    public Account authenticate(LoginAccountDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email(),
                        input.password()
                )
        );

        return accountRepository.findAccountByEmail(input.email())
                .orElseThrow();
    }
}
