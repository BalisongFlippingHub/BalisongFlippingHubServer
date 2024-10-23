package com.example.BalisongFlipping.modals.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Account {

    public User(String email, String password, String displayName) {
        super(email, password);

        this.displayName = displayName;
        profileImg = "";
        bannerImg = "";
        collectionId = "";

        setRole("USER");

        facebookLink = "";
        twitterLink = "";
        instagramLink = "";
        youtubeLink = "";
        discordLink = "";
        redditLink = "";
        personalEmailLink = "";
        personalWebsiteLink = "";
    }

    private String displayName;
    private String profileImg;
    private String bannerImg;
    private String collectionId;

    private String facebookLink;
    private String twitterLink;
    private String instagramLink;
    private String youtubeLink;
    private String discordLink;
    private String redditLink;
    private String personalEmailLink;
    private String personalWebsiteLink;

}
