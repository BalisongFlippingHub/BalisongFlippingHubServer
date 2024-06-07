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
        if (input.getRole() == Account.Role.MAKER) {
            Maker maker = new Maker();
            maker.setEmail(input.getEmail());
            maker.setPassword(passwordEncoder.encode(input.getPassword()));
            maker.setRole(input.getRole());
            maker.setAccountCreationDate(new Date());
            maker.setLastLogin(new Date());
            maker.setCompanyName(input.getAccountName());
            maker.setCompanyDuration(0.0);
            maker.setPosts(new ArrayList<>());
            maker.setServices(new ArrayList<>());
            maker.setProducts(new ArrayList<>());
            maker.setLinks(new ArrayList<>());
            return accountRepository.save(maker);
        }
        else {
            User user = new User();
            user.setEmail(input.getEmail());
            user.setPassword(passwordEncoder.encode(input.getPassword()));
            user.setRole(input.getRole());
            user.setAccountCreationDate(new Date());
            user.setLastLogin(new Date());
            user.setDisplayName(input.getAccountName());
            user.setPosts(new ArrayList<>());
            user.setOwnedKnives(new ArrayList<>());
            return accountRepository.save(user);
        }
    }

    public Account authenticate(LoginAccountDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return accountRepository.findAccountByEmail(input.getEmail())
                .orElseThrow();
    }
}
