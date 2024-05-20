package com.example.BalisongFlipping.modals.accounts;

import com.example.BalisongFlipping.modals.knives.MakersKnife;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

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
    public Maker(String uuid, String username, String password, Date accountCreationDate, Date lastLogin) {
        super(uuid, username, password, accountCreationDate, lastLogin);

    }

    // abstract methods
    @Override
    public String getAccountType() {
        return "Maker";
    }
}
