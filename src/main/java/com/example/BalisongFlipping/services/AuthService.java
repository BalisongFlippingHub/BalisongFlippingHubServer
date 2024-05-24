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
        switch (input.getRole()) {
            case ADMIN  -> {
                Admin admin = new Admin();
                admin.setEmail(input.getEmail());
                admin.setPassword(passwordEncoder.encode(input.getPassword()));
                return accountRepository.save(admin);
            }
            case MAKER -> {
                Maker maker = new Maker();
                maker.setEmail(input.getEmail());
                maker.setPassword(passwordEncoder.encode(input.getPassword()));
                return accountRepository.save(maker);
            }
            default -> {
                User user = new User();
                user.setEmail(input.getEmail());
                user.setPassword(passwordEncoder.encode(input.getPassword()));
                return accountRepository.save(user);
            }
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
