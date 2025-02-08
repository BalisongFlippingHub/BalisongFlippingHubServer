package com.example.BalisongFlipping.modals.posts;

import com.example.BalisongFlipping.enums.posts.PostTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("posts")
public class PostWrapper {

    @Id
    private String id;

    private String accountId;

    private String caption;

    private PostTypes postType;

    private Date creationDate;

}
