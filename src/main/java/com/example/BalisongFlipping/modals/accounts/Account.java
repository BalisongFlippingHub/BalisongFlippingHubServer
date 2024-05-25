package com.example.BalisongFlipping.modals.accounts;

import com.example.BalisongFlipping.modals.posts.Post;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Accounts")
public class Account implements UserDetails {

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

    public Account(Account account) {
        this.uuid = account.getUuid();
        this.email = account.getEmail();
        this.password = account.getPassword();
        this.accountCreationDate = account.getAccountCreationDate();
        this.lastLogin = account.lastLogin;
        this.role = account.getRole();
        this.posts = account.getPosts();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
