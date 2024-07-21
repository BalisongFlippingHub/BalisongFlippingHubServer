package com.example.BalisongFlipping.services;

import com.example.BalisongFlipping.dtos.NewPostDto;
import com.example.BalisongFlipping.dtos.PostDto;
import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.accounts.Maker;
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
        newPostObj.setFiles(null);
        newPostObj.setIdentifer(newPost.identifier());
        newPostObj.setPrivate(newPost.isPrivatePost());
        newPostObj.setAnnouncement(newPost.isAnnouncement());
        newPostObj.setLikes(0);
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
        newPostObj.setIdentifer(newPost.identifier());
        newPostObj.setPrivate(newPost.isPrivatePost());
        newPostObj.setAnnouncement(newPost.isAnnouncement());
        newPostObj.setLikes(0);
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

    public PostDto getPost(String id) {
        Optional<Post> foundPost = postsRepository.findById(id);

        if (foundPost.isEmpty()) {
            return null;
        }

        Optional<Account> creatorAccount = accountRepository.findById(new ObjectId(foundPost.get().getCreatorId()));

        if (creatorAccount.isEmpty()) {
            return null;
        }

        if (foundPost.get().isHasTimer()) {
            if (creatorAccount.get().getRole() == Account.Role.USER) {
                return new PostDto(
                        foundPost.get().getId(),
                        foundPost.get().getCaption(),
                        foundPost.get().getDescription(),
                        foundPost.get().isPrivate(),
                        foundPost.get().isAnnouncement(),
                        false,
                        foundPost.get().getIdentifer(),
                        foundPost.get().getCreatorId(),
                        ((User) creatorAccount.get()).getDisplayName(),
                        creatorAccount.get().getProfileImg(),
                        foundPost.get().getCreationDate(),
                        foundPost.get().getComments(),
                        foundPost.get().getLikes(),
                        foundPost.get().getFiles()
                );
            }
            else if (creatorAccount.get().getRole() == Account.Role.MAKER) {
                return new PostDto(
                        foundPost.get().getId(),
                        foundPost.get().getCaption(),
                        foundPost.get().getDescription(),
                        foundPost.get().isPrivate(),
                        foundPost.get().isAnnouncement(),
                        false,
                        foundPost.get().getIdentifer(),
                        foundPost.get().getCreatorId(),
                        ((Maker) creatorAccount.get()).getCompanyName(),
                        creatorAccount.get().getProfileImg(),
                        foundPost.get().getCreationDate(),
                        foundPost.get().getComments(),
                        foundPost.get().getLikes(),
                        foundPost.get().getFiles()
                );
            }
            else {
                return new PostDto(
                        foundPost.get().getId(),
                        foundPost.get().getCaption(),
                        foundPost.get().getDescription(),
                        foundPost.get().isPrivate(),
                        foundPost.get().isAnnouncement(),
                        false,
                        foundPost.get().getIdentifer(),
                        foundPost.get().getCreatorId(),
                        ((User) creatorAccount.get()).getDisplayName(),
                        creatorAccount.get().getProfileImg(),
                        foundPost.get().getCreationDate(),
                        foundPost.get().getComments(),
                        foundPost.get().getLikes(),
                        foundPost.get().getFiles()
                );
            }
        }
        else {
            return new PostDto(
                    foundPost.get().getId(),
                    foundPost.get().getCaption(),
                    foundPost.get().getDescription(),
                    foundPost.get().isPrivate(),
                    foundPost.get().isAnnouncement(),
                    false,
                    foundPost.get().getIdentifer(),
                    foundPost.get().getCreatorId(),
                    creatorAccount.get().getUsername(),
                    creatorAccount.get().getProfileImg(),
                    foundPost.get().getCreationDate(),
                    foundPost.get().getComments(),
                    foundPost.get().getLikes(),
                    foundPost.get().getFiles()
            );
        }
    }
}
