package com.example.BalisongFlipping.dtos;

import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.posts.Post;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;


public class AdminDto extends AccountDto {

    public AdminDto(String uuid, String email, Date accountCreationDate, Date lastLogin, Account.Role role, List<Post> posts) {
        this.setUuid(uuid);
        this.setEmail(email);
        this.setAccountCreationDate(accountCreationDate);
        this.setLastLogin(lastLogin);
        this.setRole(role);
        this.setPosts(posts);
    }
}
