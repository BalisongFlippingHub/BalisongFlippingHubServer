package com.example.BalisongFlipping.modals.accounts;

import com.example.BalisongFlipping.modals.knives.MakersKnife;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


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
    private List<String> products;
    private List<Services> services;
    private List<String> links;

    public Maker(Account account) {
        super(account);

    }
}
