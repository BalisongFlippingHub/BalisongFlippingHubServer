package com.example.BalisongFlipping.modals.accounts;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class Account {
    // members
    private String uuid;
    private String username;
    private String password;
    private Date accountCreationDate;
    private Date lastLogin;

    // private constructors
    private Account() {
        setUuid("");
        setUsername("");
        setPassword("");
        setAccountCreationDate(null);
        setLastLogin(null);
    }

    // public constructors
    public Account(String uuid, String username, String password, Date accountCreationDate, Date lastLogin) {
        setUuid(uuid);
        setUsername(username);
        setPassword(password);
        setAccountCreationDate(accountCreationDate);
        setLastLogin(lastLogin);
    }

    // methods

    // abstract methods
    public abstract String getAccountType();
}
