package com.example.BalisongFlipping.dtos;

import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.posts.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDto extends AccountDto {
    private String displayName;

    public UserDto(String uuid, String email, String displayName, Date accountCreationDate, Date lastLogin, Account.Role role, List<Post> posts) {
        super(uuid, email, accountCreationDate, lastLogin, role, posts);


    }
}
