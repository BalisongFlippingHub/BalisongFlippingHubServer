package com.example.BalisongFlipping.dtos;

import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.knives.MakersKnife;
import com.example.BalisongFlipping.modals.posts.Post;
import io.jsonwebtoken.impl.lang.Services;

import java.util.List;

public record AdminDto(
        String id,
        String email,
        Account.Role role,
        List<String> posts
){}
