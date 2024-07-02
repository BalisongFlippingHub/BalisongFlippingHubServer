package com.example.BalisongFlipping.dtos;

public record PostUploadDto(
        String accountId,

        String caption,
        boolean captionTop,

        String tagIdentifier
) {
}
