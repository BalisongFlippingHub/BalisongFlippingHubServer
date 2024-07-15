package com.example.BalisongFlipping.controllers;

import com.example.BalisongFlipping.dtos.FileDto;
import com.example.BalisongFlipping.dtos.NewPostDto;
import com.example.BalisongFlipping.dtos.PostDto;
import com.example.BalisongFlipping.services.AccountService;
import com.example.BalisongFlipping.services.PostService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;
    private final AccountService accountService;

    public PostController(PostService postService, AccountService accountService) {
        this.postService = postService;
        this.accountService = accountService;
    }

    @PostMapping(value = "/me/create-post", consumes = "multipart/form-data")
    public ResponseEntity<String> createPost(@RequestParam("caption") String caption, @RequestParam("description") String description, @RequestParam("identifier") String identifier, @RequestParam("creatorId") String creatorId, @RequestParam("isPrivatePost") String isPrivatePost, @RequestParam("isAnnouncement") String isAnnouncement, @RequestParam("hasTimer") String hasTimer, @RequestParam(value = "files", required = false) List<MultipartFile> files) throws Exception {
        NewPostDto newPost = new NewPostDto(
                caption,
                description,
                creatorId,
                identifier,
                isPrivatePost.equals("true"),
                isAnnouncement.equals("true"),
                false
        );

        if (files != null) {
            String result = postService.createPost(newPost, files);

            if (result.equals("Error") || result.isEmpty()) {
                return new ResponseEntity<String>("Error creating new post", HttpStatus.CONFLICT);
            }

            if (result.equals("No User")) {
                return new ResponseEntity<String>("Couldn't find account Id of creator", HttpStatus.CONFLICT);
            }

            return new ResponseEntity<String>(result, HttpStatus.CREATED);
        }
        else {
            String result = postService.createPost(newPost);

            if (result.equals("Error") || result.isEmpty()) {
                return new ResponseEntity<String>("Error creating new post", HttpStatus.CONFLICT);
            }

            if (result.equals("No User")) {
                return new ResponseEntity<String>("Couldn't find account Id of creator", HttpStatus.CONFLICT);
            }

            return new ResponseEntity<String>(result, HttpStatus.CREATED);
        }
    }

    @GetMapping("/any/{id}")
    public ResponseEntity<?> getPost(@PathVariable String id) {
        System.out.println(id);
        PostDto post = postService.getPost(id);

        if (post == null) {
            return new ResponseEntity<String>("Couldn't find post", HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<PostDto>(post, HttpStatus.OK);
        }
    }
}
