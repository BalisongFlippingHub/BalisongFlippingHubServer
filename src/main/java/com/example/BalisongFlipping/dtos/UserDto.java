package com.example.BalisongFlipping.dtos;



public record UserDto(
        // user account info
        String id,
        String email,
        Boolean emailVerified,
        String displayName,
        String identifierCode,
        String role,
        String collectionId,
        String bannerImg,
        String profileImg,
        String facebookLink,
        String twitterLink,
        String instagramLink,
        String youtubeLink,
        String discordLink,
        String redditLink,
        String personalEmailLink,
        String personalWebsiteLink
){}
