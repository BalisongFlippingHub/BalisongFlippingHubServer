package com.example.BalisongFlipping.modals.tokens;

import java.time.Instant;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.example.BalisongFlipping.modals.accounts.Account;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("email-verification-tokens")
@Getter
@Setter
@NoArgsConstructor
public class EmailVerificationToken {
    @Id
    private String id;

    private String token;
    private Instant expiration;

    @DocumentReference(lazy = true)
    private Account owner;

    public EmailVerificationToken(Account u) {
        System.out.println("Attemtping creation of email token with : " + u.getEmail()); 

        this.owner =  u;
        this.expiration = Instant.now().plusMillis(600000); 
        this.token = generateToken();
    }

    public String generateToken() {
        String t = "";
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(9) + 1;
            t += String.valueOf(randomNumber); 
        }

        return t; 
    }
}
