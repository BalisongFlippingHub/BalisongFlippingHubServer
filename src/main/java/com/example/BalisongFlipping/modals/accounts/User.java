package com.example.BalisongFlipping.modals.accounts;

import com.example.BalisongFlipping.modals.knives.OwnedKnife;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Account {
    private String displayName;
    private List<OwnedKnife> ownedKnives;

    public User(String email, String displayName, String password, Date accountCreationDate, Date lastLogin, Role role) {
        super(email, password, accountCreationDate, lastLogin, role);
        this.displayName = displayName;
        this.ownedKnives = new ArrayList<OwnedKnife>();
    }
}
