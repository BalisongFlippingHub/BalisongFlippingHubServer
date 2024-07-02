package com.example.BalisongFlipping.dtos;

import java.util.List;

public record CommentDto(
        String id,
        String comment,
        String accountId,
        String accountDisplayName,
        String accountProfileImg,
        String creationDate,
        List<CommentDto> comments,
        int likes
) {
}
