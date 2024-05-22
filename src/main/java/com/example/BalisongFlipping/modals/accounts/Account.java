package com.example.BalisongFlipping.modals.accounts;

import com.example.BalisongFlipping.modals.accounts.tokens.Token;
import com.example.BalisongFlipping.modals.posts.Post;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Accounts")
public class Account {

    public enum Role {
        USER,
        ADMIN,
        MAKER
    }

    // members
    @Id
    private String uuid;

    @Indexed(unique = true)
    private String email;

    private Token token;

    private String password;
    private Date accountCreationDate;
    private Date lastLogin;
    private Role role;

    private List<Post> posts;

    public Account(String email, String password, Date accountCreationDate, Date lastLogin, Role role) {
        this.email = email;
        this.password = password;
        this.accountCreationDate = accountCreationDate;
        this.lastLogin = lastLogin;
        this.role = role;
        this.posts = new ArrayList<Post>();
    }
}
