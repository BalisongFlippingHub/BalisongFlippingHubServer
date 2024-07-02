package com.example.BalisongFlipping.modals.posts;

import com.example.BalisongFlipping.modals.comments.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("posts")
public class Post {

    @Id
    private String id;

    private String caption;
    private boolean captionAbove;
    private String accountId;

    private List<Comment> comments;
    private int likes;

    private Date creationDate;

    private List<String> files;
}
