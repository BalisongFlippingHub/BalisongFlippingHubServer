package com.example.BalisongFlipping.dtos;

public record PostCoverDTO(
        String id,
        int likes,
        int comments,
        String identifier,
        String caption,
        String coverFile,
        boolean isPrivate,
        boolean isAnnouncement
) {
}
