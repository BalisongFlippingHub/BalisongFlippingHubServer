package com.example.BalisongFlipping.modals.accounts;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
public abstract class Account implements UserDetails {

    public enum Role {
        USER,
        ADMIN,
        MAKER
    }

    // members
    private String uuid;
    private String email;
    private String password;
    private Date accountCreationDate;
    private Date lastLogin;

    private Role role;

    // private constructors
    private Account() {
        setUuid("");
        setEmail("");
        setPassword("");
        setAccountCreationDate(null);
        setLastLogin(null);
    }

    // public constructors
    public Account(String uuid, String email, String password, Date accountCreationDate, Date lastLogin) {
        setUuid(uuid);
        setEmail(email);
        setPassword(password);
        setAccountCreationDate(accountCreationDate);
        setLastLogin(lastLogin);
    }

    // methods

    // abstract methods
    public abstract String getAccountType();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
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
