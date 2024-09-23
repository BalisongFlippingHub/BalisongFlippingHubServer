package com.example.BalisongFlipping.repositories;

import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.collectionKnives.CollectionKnife;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CollectionKnifeRepository extends MongoRepository<CollectionKnife, String> {

    Optional<List<CollectionKnife>> findAllByCollectionId(String collectionId);
}
