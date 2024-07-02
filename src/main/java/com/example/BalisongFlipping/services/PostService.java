package com.example.BalisongFlipping.services;

import com.example.BalisongFlipping.dtos.PostDto;
import com.example.BalisongFlipping.dtos.PostUploadDto;
import com.example.BalisongFlipping.modals.posts.Post;
import com.example.BalisongFlipping.repositories.AccountRepository;
import com.example.BalisongFlipping.repositories.PostsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PostService {

    private final AccountRepository accountRepository;
    private final PostsRepository postsRepository;

    public PostService(AccountRepository accountRepository, PostsRepository postsRepository) {
        this.accountRepository = accountRepository;
        this.postsRepository = postsRepository;
    }

//    public Post addPost(PostUploadDto post, List<MultipartFile> files) {
//
//    }
}
