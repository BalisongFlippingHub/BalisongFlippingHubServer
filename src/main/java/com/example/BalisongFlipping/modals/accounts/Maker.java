package com.example.BalisongFlipping.modals.accounts;

import com.example.BalisongFlipping.modals.knives.MakersKnife;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class Maker extends Account{

    enum Services {
        PRODUCTION,
        TUNING,
        MODDING,
        ANODIZATION,
        BLADE_SHARPENING,
        BLADE_REFURBRISH
    }

    // members
    private String companyName;
    private double companyDuration;
    private ArrayList<MakersKnife> products;
    private ArrayList<Services> services;
    private ArrayList<String> links;

    // public constructors
    public Maker(String uuid, String email, String password, Date accountCreationDate, Date lastLogin, Role role) {
        super(uuid, email, password, accountCreationDate, lastLogin);
        setRole(Role.MAKER);
    }

    // abstract methods
    @Override
    public String getAccountType() {
        return "Maker";
    }
}
