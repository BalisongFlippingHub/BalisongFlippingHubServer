package com.example.BalisongFlipping.dtos;

public record LoginResponseDto(
        String accessToken,
        long expireTime,
        Record account
) {
}
