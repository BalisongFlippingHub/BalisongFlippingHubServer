package com.example.BalisongFlipping.dtos;

import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.knives.OwnedKnife;
import com.example.BalisongFlipping.modals.posts.Post;

import java.util.List;

public record UserDto(
        String uuid,
        String email,
        String displayName,
        Account.Role role,
        List<Post> posts,
        List<OwnedKnife> ownedKnives
){}
