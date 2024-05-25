package com.example.BalisongFlipping.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrickTutorialsController {

    @GetMapping("/tutorials")
    public ResponseEntity<?> getTrickTutorialVideos() {
        return new ResponseEntity<>("No tutorials yet", HttpStatus.NOT_FOUND);
    }
}
