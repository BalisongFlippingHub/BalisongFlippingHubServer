package com.example.BalisongFlipping.dtos;
import java.util.List;

public record PostDto(
    String id,

    String caption,
    boolean captionAbove,

    String tagIdentifier,

    String accountId,
    String accountName,
    String accountProfileImg,

    String creationDate,

    List<CommentDto>comments,

    int likes,

    List<String> files
) {
}
