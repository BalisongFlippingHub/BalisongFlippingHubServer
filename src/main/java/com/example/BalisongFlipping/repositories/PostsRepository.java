package com.example.BalisongFlipping.repositories;

import com.example.BalisongFlipping.dtos.PostDto;
import com.example.BalisongFlipping.modals.posts.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PostsRepository extends MongoRepository<Post, String> {

    @Query(sort = "{ creationDate : -1 }")
    List<Post> findAllByCreatorId(String creatorId);

}
