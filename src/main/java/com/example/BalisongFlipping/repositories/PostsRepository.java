package com.example.BalisongFlipping.repositories;

import com.example.BalisongFlipping.modals.posts.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostsRepository extends MongoRepository<Post, String> {

}
