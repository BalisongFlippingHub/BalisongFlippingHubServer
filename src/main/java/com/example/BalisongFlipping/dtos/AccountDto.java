package com.example.BalisongFlipping.dtos;

import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.posts.Post;
import com.example.BalisongFlipping.repositories.AccountRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AccountDto {

    private String uuid;
    private String email;
    private Date accountCreationDate;
    private Date lastLogin;
    private Account.Role role;
    private List<Post> posts;

    public AccountDto(String uuid, String email, Date accountCreationDate, Date lastLogin, Account.Role role, List<Post> posts) {
        this.uuid = uuid;
        this.email = email;
        this.accountCreationDate = accountCreationDate;
        this.lastLogin = lastLogin;
        this.role = role;

        this.posts = posts;
    }
}
