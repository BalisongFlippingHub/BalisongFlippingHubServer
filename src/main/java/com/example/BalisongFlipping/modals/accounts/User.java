package com.example.BalisongFlipping.modals.accounts;

import com.example.BalisongFlipping.modals.knives.OwndedKnife;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Setter
@Getter
public class User extends Account {
    private String displayName;
    private ArrayList<OwndedKnife> ownedKnives;

    // Public Constructor
    public User(String uuid, String username, String password, Date accountCreationDate, Date lastLogin) {
        super(uuid, username, password, accountCreationDate, lastLogin);
    }

    @Override
    public String getAccountType() {
        return "User";
    }
}
