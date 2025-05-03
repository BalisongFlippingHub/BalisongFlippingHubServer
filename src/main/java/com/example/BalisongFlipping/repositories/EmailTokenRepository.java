package com.example.BalisongFlipping.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.BalisongFlipping.modals.tokens.EmailVerificationToken;

public interface EmailTokenRepository extends MongoRepository<EmailVerificationToken, String> {
    void deleteByOwner_Id(ObjectId id);

    default void deleteByOwner_Id(String id) {
        deleteByOwner_Id(new ObjectId(id));
    }

    Optional<EmailVerificationToken> findByToken(String token);
}
