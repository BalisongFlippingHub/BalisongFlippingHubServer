package com.example.BalisongFlipping.repositories;

import com.example.BalisongFlipping.modals.collections.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CollectionRepository extends MongoRepository<Collection, String> {

}
