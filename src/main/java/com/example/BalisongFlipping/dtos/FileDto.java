package com.example.BalisongFlipping.dtos;

public record FileDto(
        String fileName,
        String fileType,
        String fileSize,
        byte[] file
) {
}
