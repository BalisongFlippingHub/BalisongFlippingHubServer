package com.example.BalisongFlipping.dtos;

import org.springframework.web.multipart.MultipartFile;

public record UploadAccountAssetDto (
        String accountId,
        MultipartFile img
) {
}
