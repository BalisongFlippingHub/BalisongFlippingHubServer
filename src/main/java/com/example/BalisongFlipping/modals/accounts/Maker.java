package com.example.BalisongFlipping.modals.accounts;

import com.example.BalisongFlipping.modals.knives.MakersKnife;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    private ArrayList<String> products;
    private ArrayList<Services> services;
    private ArrayList<String> links;

    // abstract methods
    @Override
    public String getAccountType() {
        return "Maker";
    }
}
