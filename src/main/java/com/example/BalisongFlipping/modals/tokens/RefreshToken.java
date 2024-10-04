package com.example.BalisongFlipping.modals.tokens;

import com.example.BalisongFlipping.modals.accounts.Account;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;

@Document("refresh-tokens")
@Data
public class RefreshToken {
    @Id
    private String id;

    private String token;
    private Instant expiration;

    @DocumentReference(lazy = true)
    private Account owner;
}
