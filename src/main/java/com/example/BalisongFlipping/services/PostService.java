package com.example.BalisongFlipping.services;

import com.example.BalisongFlipping.dtos.NewPostDto;
import com.example.BalisongFlipping.dtos.PostCoverDTO;
import com.example.BalisongFlipping.dtos.PostDto;
import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.accounts.User;
import com.example.BalisongFlipping.modals.posts.Post;
import com.example.BalisongFlipping.repositories.AccountRepository;
import com.example.BalisongFlipping.repositories.PostsRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final AccountRepository accountRepository;
    private final PostsRepository postsRepository;
    private final JavaFSService javaFSService;

    public PostService(AccountRepository accountRepository, PostsRepository postsRepository, JavaFSService javaFSService) {
        this.accountRepository = accountRepository;
        this.postsRepository = postsRepository;
        this.javaFSService = javaFSService;
    }

    public PostCoverDTO generatePostCoverDTO(Post p) throws Exception {

        if (p.getFiles().isEmpty()) {
            return new PostCoverDTO(
                    p.getId(),
                    p.getLikes(),
                    p.getCommentsNum(),
                    p.getIdentifier(),
                    p.getCaption(),
                    "",
                    p.isPrivate(),
                    p.isAnnouncement()
            );
        }
        else {
            return new PostCoverDTO(
                    p.getId(),
                    p.getLikes(),
                    p.getCommentsNum(),
                    p.getIdentifier(),
                    p.getCaption(),
                    p.getFiles().getFirst(),
                    p.isPrivate(),
                    p.isAnnouncement()
            );
        }
    }

    public PostDto generatePostDto(Post p) throws Exception {
        Optional<Account> account = accountRepository.findById(new ObjectId(p.getCreatorId()));

        if (account.isEmpty()) {
            throw new Exception("User Not Found");
        }

        return new PostDto(
                p.getId(),
                p.getCaption(),
                p.getDescription(),
                p.isPrivate(),
                p.isAnnouncement(),
                p.isHasTimer(),
                p.getIdentifier(),
                p.getCreatorId(),
                ((User) account.get()).getDisplayName(),
                ((User) account.get()).getProfileImg(),
                p.getCreationDate(),
                p.getComments(),
                p.getLikes(),
                p.getFiles()
        );
    }

    public String createPost(NewPostDto newPost) throws Exception {
        // get account in repo
        Optional<Account> account = accountRepository.findById(new ObjectId(newPost.creatorId()));

        // create new post and store in repo
        Post newPostObj = new Post();
        newPostObj.setCreatorId(newPost.creatorId());
        newPostObj.setCaption(newPost.caption());
        newPostObj.setDescription(newPost.description());
        newPostObj.setCreationDate(new Date());
        newPostObj.setComments(new ArrayList<String>());
        newPostObj.setFiles(new ArrayList<String>());
        newPostObj.setIdentifier(newPost.identifier());
        newPostObj.setPrivate(newPost.isPrivatePost());
        newPostObj.setAnnouncement(newPost.isAnnouncement());
        newPostObj.setLikes(0);
        newPostObj.setCommentsNum(0);
        newPostObj.setHasTimer(false);

        try {
            // update account in repo
            if (account.isPresent()) {
                Post p = postsRepository.insert(newPostObj);
                List<String> tempArr = account.get().getPosts();
                tempArr.add(p.getId());
                account.get().setPosts(tempArr);
                accountRepository.save(account.get());
                return p.getId();
            }
            else {
                return "No User";
            }
        }
        catch(Exception e) {
            return "Error";
        }
    }

    public String createPost(NewPostDto newPost, List<MultipartFile> files) throws Exception {
        // get account in repo
        Optional<Account> account = accountRepository.findById(new ObjectId(newPost.creatorId()));

        // store files
        List<String> fileIds = new ArrayList<String>();
        if (files != null) {
            for (MultipartFile file : files) {
                try {
                    String fileId = javaFSService.addAsset("new post file", file);
                    fileIds.add(fileId);
                } catch (Exception e) {
                    return "Error";
                }
            }
        }

        // create new post and store in repo
        Post newPostObj = new Post();
        newPostObj.setCreatorId(newPost.creatorId());
        newPostObj.setCaption(newPost.caption());
        newPostObj.setDescription(newPost.description());
        newPostObj.setCreationDate(new Date());
        newPostObj.setComments(new ArrayList<String>());
        newPostObj.setFiles(fileIds);
        newPostObj.setIdentifier(newPost.identifier());
        newPostObj.setPrivate(newPost.isPrivatePost());
        newPostObj.setAnnouncement(newPost.isAnnouncement());
        newPostObj.setLikes(0);
        newPostObj.setCommentsNum(0);
        newPostObj.setHasTimer(false);

        try {
            if (account.isPresent()) {
                Post p = postsRepository.insert(newPostObj);
                List<String> tempArr = account.get().getPosts();
                tempArr.add(p.getId());
                account.get().setPosts(tempArr);
                accountRepository.save(account.get());
                return p.getId();
            }
            else {
                return "No User";
            }
        }
        catch(Exception e) {
            return "Error";
        }
    }

    public List<PostDto> getUsersPosts(String userId) throws Exception {
        try {
            Optional<Account> account = accountRepository.findById(new ObjectId(userId));

            if (account.isEmpty()) {
                throw new Exception("User Not Found");
            }

            List<Post> posts = postsRepository.findAllByCreatorId(userId);
            List<PostDto> postsDto = new ArrayList<>();

            for(Post p: posts) {
                postsDto.add(generatePostDto(p));
            }

            return postsDto;
        }
        catch(Exception e) {
            throw e;
        }
    }

    public List<PostCoverDTO> getUsersPostsCovers(String userId) throws Exception {
        try {
            Optional<Account> account = accountRepository.findById(new ObjectId(userId));

            if (account.isEmpty()) {
                throw new Exception("User Not Found");
            }

            List<Post> posts = postsRepository.findAllByCreatorId(userId);
            List<PostCoverDTO> postsDto = new ArrayList<>();

            for(Post p: posts) {
                postsDto.add(generatePostCoverDTO(p));
            }

            return postsDto;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public PostDto getPost(String id) throws Exception {
        Optional<Post> foundPost = postsRepository.findById(id);

        if (foundPost.isEmpty()) {
            return null;
        }

        Optional<Account> creatorAccount = accountRepository.findById(new ObjectId(foundPost.get().getCreatorId()));

        if (creatorAccount.isEmpty()) {
            return null;
        }

        return generatePostDto(foundPost.get());
    }
}
