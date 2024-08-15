package com.example.BalisongFlipping.dtos;

import java.util.List;

public record AdminDto(
        String id,
        String email,
        List<String> posts
){}
