package com.example.BalisongFlipping.repositories;

import com.example.BalisongFlipping.modals.posts.PostWrapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PostsRepository extends MongoRepository<PostWrapper, String> {

    @Query(sort = "{ creationDate : -1 }")
    List<PostWrapper> findAllByAccountId(String accountId);


}
