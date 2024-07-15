package com.example.BalisongFlipping.dtos;
import java.util.List;

public record PostDto(
    String id,

    String caption,
    String description,

    boolean isPrivate,
    boolean isAnnouncement,
    boolean hasTimer,

    String identifier,

    String creatorId,
    String creatorName,
    String creatorProfileImg,

    String creationDate,

    List<String>comments,

    int likes,

    List<String> files
) {
}
