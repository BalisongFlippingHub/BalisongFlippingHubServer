package com.example.BalisongFlipping.dtos;

import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.accounts.Maker;
import com.example.BalisongFlipping.modals.knives.MakersKnife;
import com.example.BalisongFlipping.modals.posts.Post;
import io.jsonwebtoken.impl.lang.Services;

import java.util.List;

public record MakerDto(
        String id,
        String email,
        String companyName,
        double companyDuration,
        Account.Role role,
        List<String> posts,
        FileDto profileImg,
        FileDto bannerImg,
        List<String> products,
        List<Maker.Services> services,
        String facebookLink,
        String instagramLink,
        String twitterLink,
        String personalEmailLink,
        String personalWebsiteLink
){

}
