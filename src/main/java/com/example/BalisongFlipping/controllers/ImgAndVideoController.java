package com.example.BalisongFlipping.controllers;


import com.example.BalisongFlipping.dtos.FileDto;
import com.example.BalisongFlipping.repositories.AccountRepository;
import com.example.BalisongFlipping.services.ImgAndVideoService;
import com.example.BalisongFlipping.services.JavaFSService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("file")
@RestController
public class ImgAndVideoController {
    private final ImgAndVideoService imgAndVideoService;

    public ImgAndVideoController(ImgAndVideoService imgAndVideoService) {
        this.imgAndVideoService = imgAndVideoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable String id) {
        System.out.println(id);

        try {
            FileDto fileDto = imgAndVideoService.getFile(id);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(fileDto.fileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.fileName() + "\"")
                    .body(new ByteArrayResource(fileDto.file()));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Failed to get image data", HttpStatus.CONFLICT);
        }
    }
}
