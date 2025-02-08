package com.example.BalisongFlipping.modals.collectionKnives;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GalleryFile {
    private String postId;

    private String fileId;

    public GalleryFile(String fileId, String postId) {
        this.fileId = fileId;
        this.postId = postId;
    }
}
