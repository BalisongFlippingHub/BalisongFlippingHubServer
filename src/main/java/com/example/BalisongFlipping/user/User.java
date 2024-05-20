package com.example.BalisongFlipping.user;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Date;

@Setter
@Getter
public class User {
    // Members
    private String UID;
    private String username;
    private String password;
    private Date accountCreationDate;
    private Date lastLogin;

    private ArrayList<Knife> ownedKnives;

    // Private Constructors
    private User() {
        this.UID = "";
        this.username = "";
        this.password = "";
        this.accountCreationDate = null;
        this.lastLogin = null;
        ownedKnives = new ArrayList<Knife>();
    }

    // Public Constructors
    public User(String UID, String username, String password, Date accountCreationDate, Date lastLogin) {
        this.UID = UID;
        this.username = username;
        this.password = password;
        this.accountCreationDate = accountCreationDate;
        this.lastLogin = lastLogin;
        ownedKnives = new ArrayList<Knife>();
    }
}
