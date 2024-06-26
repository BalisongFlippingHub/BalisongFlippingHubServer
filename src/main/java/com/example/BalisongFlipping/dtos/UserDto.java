package com.example.BalisongFlipping.dtos;

import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.knives.OwnedKnife;
import com.example.BalisongFlipping.modals.posts.Post;

import java.util.List;

public record UserDto(
        String id,
        String email,
        String displayName,
        Account.Role role,
        List<String> posts,
        String bannerImg,
        String profileImg,
        List<OwnedKnife> ownedKnives,
        String facebookLink,
        String twitterLink,
        String instagramLink
){}
