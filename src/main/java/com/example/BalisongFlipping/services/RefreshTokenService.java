package com.example.BalisongFlipping.services;

import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.tokens.RefreshToken;
import com.example.BalisongFlipping.repositories.AccountRepository;
import com.example.BalisongFlipping.repositories.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private AccountRepository accountRepository;

    public RefreshToken createRefreshToken(String email) throws Exception {
        // checking for an already existing refresh token assigned to user
        Optional<Account> accountCheck = accountRepository.findAccountByEmail(email);

        // if empty account, return with exception
        if (accountCheck.isEmpty())
            throw new Exception("Couldn't find account.");

        // deleting refresh token in repo that matches account id
        refreshTokenRepository.deleteByOwner_Id(accountCheck.get().getId());

        // create new refresh token
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setOwner(accountCheck.get());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiration(Instant.now().plusMillis(6000000));

        // return new refresh token
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verityExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpiration().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token is expired.");
        }

        return refreshToken;
    }

    public void removeRefreshToken(String token) throws Exception {
       refreshTokenRepository.delete(refreshTokenRepository.findByToken(token).get());
    }
}
