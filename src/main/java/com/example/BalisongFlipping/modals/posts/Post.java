package com.example.BalisongFlipping.modals.posts;

import com.example.BalisongFlipping.modals.comments.Comment;
import com.example.BalisongFlipping.modals.likes.Like;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Posts")
public class Post {

    private enum PostType {
        IMAGE_POST,
        VIDEO_POST,
        COLLAGE_POST,
    }

    private enum Tags {
        TRICK_TUTORIAL,
        COLLECTION,
        NEW_PRODUCT,
        PRODUCTION,
    }

    @Id
    private String uuid;

    private String title;
    private String caption;
    private String accountId;
    private String accountDisplayName;
    private List<Comment> comments;
    private List<Like> likes;
    private List<Tags> tags;
    private PostType postType;
}
