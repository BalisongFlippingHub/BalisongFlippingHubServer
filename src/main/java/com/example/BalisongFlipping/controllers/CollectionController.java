package com.example.BalisongFlipping.controllers;

import com.example.BalisongFlipping.dtos.CollectionDataDto;
import com.example.BalisongFlipping.modals.collectionKnives.CollectionKnife;
import com.example.BalisongFlipping.services.AccountService;
import com.example.BalisongFlipping.services.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/collection")
@RestController
public class CollectionController {
    private final CollectionService collectionService;

    @Autowired
    private AccountService accountService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping(value = "/any/{collectionId}")
    public ResponseEntity<?> getCollectionById(@PathVariable("collectionId") String collectionId) {
        CollectionDataDto foundCollection = collectionService.getCollection(collectionId);

        if (foundCollection == null) {
            return new ResponseEntity<String>("Error retreiving collection", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<CollectionDataDto>(foundCollection, HttpStatus.OK);
    }

    @PostMapping(value = "/me/update-banner-img", consumes = "multipart/form-data")
    public ResponseEntity<String> updateBannerImg(@RequestParam("file") MultipartFile file) throws Exception {
        String collectionId =  accountService.getSelf().collectionId();

        if (!collectionService.checkForCollectionExistance(collectionId)) {
            return new ResponseEntity<>("Collection doesn't exist", HttpStatus.NOT_FOUND);
        }

        String imgId = collectionService.updateBannerImg(collectionId, file);

        if (imgId.isEmpty()) {
            return new ResponseEntity<>("Failed to store img", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<String>(imgId, HttpStatus.OK);
    }

    @PostMapping(value = "/me/add-knife", consumes = "multipart/form-data")
    public ResponseEntity<?> addKnifeToCollection(
            @RequestParam("displayName") String displayName,
            @RequestParam("knifeMaker") String knifeMaker,
            @RequestParam("baseKnifeModel") String baseKnifeModel,
            @RequestParam("knifeType") String knifeType,
            @RequestParam("aqquiredDate") String aqquiredDate,
            @RequestParam("isFavoriteKnife") String isFavoriteKnife,
            @RequestParam("isFavoriteFlipper") String isFavoriteFlipper,
            @RequestParam("coverPhoto") MultipartFile coverPhoto,
            @RequestParam("knifeMSRP") String knifeMSRP,
            @RequestParam("overallLength") String overallLength,
            @RequestParam("weight") String weight,
            @RequestParam("pivotSystem") String pivotSystem,
            @RequestParam("latchType") String latchType,
            @RequestParam("pinSystem") String pinSystem,
            @RequestParam("hasModularBalance") String hasModularBalance,
            @RequestParam("balanceValue") String balanceValue,
            @RequestParam("bladeStyle") String bladeStyle,
            @RequestParam("bladeFinish") String bladeFinish,
            @RequestParam("bladeMaterial") String bladeMaterial,
            @RequestParam("handleConstruction") String handleConstruction,
            @RequestParam("handleMaterial") String handleMaterial,
            @RequestParam("handleFinish") String handleFinish,
            @RequestParam("averageScore") String averageScore,
            @RequestParam("qualityScore") String qualityScore,
            @RequestParam("feelScore") String feelScore,
            @RequestParam("flippingScore") String flippingScore,
            @RequestParam("soundScore") String soundScore,
            @RequestParam("durabilityScore") String durabilityScore,
            @RequestParam(value = "galleryFiles", required = false) MultipartFile[] galleryFiles
    ) throws Exception {
        System.out.println("Adding new Knife");
        // check for collection existance
        String collectionID =  accountService.getSelf().collectionId();

        if (!collectionService.checkForCollectionExistance(collectionID)) {
            return new ResponseEntity<>("Collection doesn't exist", HttpStatus.NOT_FOUND);
        }

        // checks and validates passed info
        if (!collectionService.validateNewKnifeInfo(displayName, knifeMaker, baseKnifeModel, knifeType, aqquiredDate, coverPhoto)) {
            return new ResponseEntity<>("Invalid Info Passed", HttpStatus.CONFLICT);
        }

        // attempt to create object and store in db
        try {
            CollectionKnife newKnife = collectionService.addNewKnife(
                    collectionID,
                    displayName,
                    knifeMaker,
                    baseKnifeModel,
                    knifeType,
                    aqquiredDate,
                    isFavoriteKnife,
                    isFavoriteFlipper,
                    coverPhoto,
                    knifeMSRP,
                    overallLength,
                    weight,
                    pivotSystem,
                    latchType,
                    pinSystem,
                    hasModularBalance,
                    balanceValue,
                    bladeStyle,
                    bladeFinish,
                    bladeMaterial,
                    handleConstruction,
                    handleFinish,
                    handleMaterial,
                    averageScore,
                    qualityScore,
                    feelScore,
                    flippingScore,
                    soundScore,
                    durabilityScore,
                    galleryFiles
            );

            return new ResponseEntity<CollectionKnife>(newKnife, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Creation Error", HttpStatus.CONFLICT);
        }
    }
}
