package com.example.BalisongFlipping.modals.accounts;


import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
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
@Document("accounts")
public class Account implements UserDetails {

    // constructors
    public Account(String email, String password) {
        this.email = email;
        this.emailVerified = false;
        this.password = password;
        accountCreationDate = new Date();
        lastLogin = new Date();
        posts = new ArrayList<>();
    }

    // members
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Indexed(unique = true)
    private String email;

    private String role;

    private String password;
    private Date accountCreationDate;
    private Date lastLogin;

    private Boolean emailVerified; 

    private List<String> posts;

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
