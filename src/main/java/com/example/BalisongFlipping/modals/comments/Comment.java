package com.example.BalisongFlipping.modals.comments;

import com.example.BalisongFlipping.modals.likes.Like;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    private String uuid;

    private String comment;
    private String accountID;
    private String accountDisplayName;

    private List<Like> likes;
    private List<Comment> replies;
}
