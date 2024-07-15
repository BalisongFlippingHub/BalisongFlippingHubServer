package com.example.BalisongFlipping.dtos;

public record NewPostDto(
        String caption,
        String description,
        String creatorId,
        String identifier,
        boolean isPrivatePost,
        boolean isAnnouncement,
        boolean hasTimer
) {
}
