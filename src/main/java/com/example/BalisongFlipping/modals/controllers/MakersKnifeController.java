package com.example.BalisongFlipping.modals.controllers;

import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.knives.MakersKnife;
import com.example.BalisongFlipping.modals.repositories.MakersKnifeRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MakersKnifeController {

    @Autowired
    private MakersKnifeRepository makersKnifeRepository;

    @GetMapping("/makers-knives")
    public ResponseEntity<?> getAllMakersKnives() {
        List<MakersKnife> knives = makersKnifeRepository.findAll();
        if (!knives.isEmpty()) {
            return new ResponseEntity<List<MakersKnife>>(knives, HttpStatus.OK);
        }

        return new ResponseEntity<>("No production knives available", HttpStatus.NOT_FOUND);
    }
}
