package com.example.BalisongFlipping.modals.posts;

import com.example.BalisongFlipping.enums.posts.tags.CollectionTimelineTags;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CollectionTimelinePost extends PostWrapper {

    private List<String> files;

    private String collectionKnife;

    private List<CollectionTimelineTags> postTags;

    public CollectionTimelinePost() {
        super();
        postTags = new ArrayList<>();
    }

    public void addPostTag(CollectionTimelineTags tag) {
        this.postTags.add(tag);
    }
}
