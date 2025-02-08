package com.example.BalisongFlipping.dtos.postsDtos;

import com.example.BalisongFlipping.enums.posts.PostTypes;
import com.example.BalisongFlipping.enums.posts.tags.CollectionTimelineTags;

import java.util.List;

public record CollectionTimelineDto(
        String id,
        String accountId,
        String creationDate,
        String collectionKnifeCoverPhoto,
        List<String> galleryFiles,
        PostTypes postType,
        List<CollectionTimelineTags> postTags,
        String collectionKnifeDisplayName
) {
}
