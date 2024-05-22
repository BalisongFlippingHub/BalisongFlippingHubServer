package com.example.BalisongFlipping.modals.accounts;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Accounts")
public abstract class Account {

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

    public Account(String email, String password, Date accountCreationDate, Date lastLogin, Role role) {
        this.email = email;
        this.password = password;
        this.accountCreationDate = accountCreationDate;
        this.lastLogin = lastLogin;
        this.role = role;
    }

    // methods

    // abstract methods
    public abstract String getAccountType();
}
