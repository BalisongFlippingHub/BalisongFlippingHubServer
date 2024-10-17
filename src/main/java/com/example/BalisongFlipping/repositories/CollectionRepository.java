package com.example.BalisongFlipping.repositories;

import com.example.BalisongFlipping.modals.collections.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CollectionRepository extends MongoRepository<Collection, String> {
    Optional<Collection> findByUserId(String userId);
}
