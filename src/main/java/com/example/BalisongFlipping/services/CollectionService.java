package com.example.BalisongFlipping.services;

import com.example.BalisongFlipping.dtos.CollectionDataDto;
import com.example.BalisongFlipping.modals.collectionKnives.CollectionKnife;
import com.example.BalisongFlipping.modals.collectionKnives.GalleryFile;
import com.example.BalisongFlipping.modals.collections.Collection;
import com.example.BalisongFlipping.modals.posts.CollectionTimelinePost;
import com.example.BalisongFlipping.repositories.CollectionKnifeRepository;
import com.example.BalisongFlipping.repositories.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final CollectionKnifeRepository collectionKnifeRepository;
    private static  JavaFSService javaFSService;

    @Autowired
    private PostService postService;

    public CollectionService(CollectionRepository collectionRepository, CollectionKnifeRepository collectionKnifeRepository, JavaFSService javaFSService) {
        this.collectionRepository = collectionRepository;
        this.collectionKnifeRepository = collectionKnifeRepository;
        this.javaFSService = javaFSService;
    }

    private List<CollectionKnife> getCollectionKnives(String collectionID) {
        Optional<List<CollectionKnife>> knives = collectionKnifeRepository.findAllByCollectionId(collectionID);

        return knives.get();
    }

    private CollectionDataDto convertCollectionToDTO(Collection collection) {
        return new CollectionDataDto(
                collection.getId(),
                collection.getUserId(),
                collection.getBannerImg(),
                getCollectionKnives(collection.getId())
        );
    }

    public CollectionDataDto getCollection(String collectionId) {
        Optional<Collection> foundCollection = collectionRepository.findById(collectionId);

        if (foundCollection.isEmpty()) return null;

        return convertCollectionToDTO(foundCollection.get());
    }

    public CollectionDataDto getCollectionByAccountId(String accountId) {
        Optional<Collection> foundCollection = collectionRepository.findByUserId(accountId);

        if (foundCollection.isEmpty()) return null;

        return convertCollectionToDTO(foundCollection.get());
    }

    public boolean checkForCollectionExistance(String collectionId) {
        Optional<Collection> collection = collectionRepository.findById(collectionId);

        return collection.isPresent();
    }

    public boolean validateNewKnifeInfo(String displayName, String knifeMaker, String baseKnifeModel, String knifeType, String aqquiredDate, MultipartFile coverPhoto) {
        if (displayName.isEmpty() || knifeMaker.isEmpty() || baseKnifeModel.isEmpty() || knifeType.isEmpty() || aqquiredDate.isEmpty() || coverPhoto.isEmpty()) return false;

        return true;
    }

    public boolean checkForDuplicateDisplayName(String displayName, String collectionID) throws Exception {
        Optional<Collection> foundCollection = collectionRepository.findById(collectionID);

        for (String knifeId: foundCollection.get().getCollectedKnives()) {
            Optional<CollectionKnife> foundKnife = collectionKnifeRepository.findById(knifeId);

            if (foundKnife.isPresent()) {
                if (foundKnife.get().getDisplayName().equals(displayName)) return true;
            }
        }

        return false;
    }

    private CollectionTimelinePost createAddKnifeTimelinePost(String accountId, String collectionKnifeId) throws Exception {
        CollectionTimelinePost newTimelinePost = new CollectionTimelinePost();


        return newTimelinePost;
    }

    public CollectionKnife addNewKnife(
            String collectionID,
            String displayName,
            String knifeMaker,
            String baseKnifeModel,
            String knifeType,
            String aqquiredDate,
            String isFavoriteKnife,
            String isFavoriteFlipper,
            MultipartFile coverPhoto,
            String knifeMSRP,
            String overallLength,
            String weight,
            String pivotSystem,
            String latchType,
            String pinSystem,
            String hasModularBalance,
            String balanceValue,
            String bladeStyle,
            String bladeFinish,
            String bladeMaterial,
            String handleConstruction,
            String handleMaterial,
            String handleFinish,
            String averageScore,
            String feelScore,
            String flippingScore,
            String qualityScore,
            String soundScore,
            String durabilityScore,
            MultipartFile[] galleryFiles
    ) throws Exception {
        try {
            // cover photo
            String coverPhotoStr = javaFSService.addAsset("cover photo", coverPhoto);

            // create new knife obj
            CollectionKnife newKnife = new CollectionKnife(
                    collectionID,
                    displayName,
                    knifeMaker,
                    baseKnifeModel,
                    knifeType,
                    aqquiredDate,
                    isFavoriteKnife,
                    isFavoriteFlipper,
                    coverPhotoStr,
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
                    handleMaterial,
                    handleFinish,
                    averageScore,
                    feelScore,
                    flippingScore,
                    qualityScore,
                    soundScore,
                    durabilityScore,
                   null
            );

            // get collection from repo
            Optional<Collection> foundCollection = collectionRepository.findById(collectionID);

            // check for failed aqquisition of collection from repo
            if (foundCollection.isEmpty())
                throw new Exception("Couldn't Find Collection");

            // save new knife obj to repo to get id
            newKnife = collectionKnifeRepository.save(newKnife);

            // process gallery files
            List<String> galleryFileStrings = new ArrayList<>();
            if (galleryFiles != null) {
                for (MultipartFile file: galleryFiles) {
                    String fileId = javaFSService.addAsset("gallery file", file);
                    galleryFileStrings.add(fileId);
                }
            }

            // create collection timeline post
            CollectionTimelinePost newPost = postService.createAddKnifeCollectionTimelinePost(foundCollection.get().getUserId(), newKnife, galleryFileStrings);

            // update knife obj with gallery
            List<GalleryFile> gallery = new ArrayList<>();
            for (String fileId: galleryFileStrings) {
                gallery.add(new GalleryFile(fileId, newPost.getId()));
            }

            newKnife.setGalleryFiles(gallery);

            // update new knife obj in repo
            newKnife = collectionKnifeRepository.save(newKnife);

            // update collection array of collected knives
            List<String> updatedArr = foundCollection.get().getCollectedKnives();
            updatedArr.add(newKnife.getId());
            foundCollection.get().setCollectedKnives(updatedArr);

            // update collection in repo
            collectionRepository.save(foundCollection.get());

            // return newly saved collection knife
            return newKnife;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    /***
     *
     * @param collectionId     (String to represend the id of an account saved in the repo)
     * @param file          (Multipart file that needs to be stored
     * @return              (Returns boolean value based on successful storage of image and update on account)
     *
     * Function takes in an image and an id to identify the account a user wannts to update the babber image on,
     * then attempts to update that banner image on the db for that user.
     */
    public String updateBannerImg(String collectionId, MultipartFile file) {
        Optional<Collection> foundCollection = collectionRepository.findById(collectionId);

        // if account isn't found with passed id, return false
        if (foundCollection.isEmpty()) return null;

        try {
            // checks for banner img already existing for user
            if (!foundCollection.get().getBannerImg().isEmpty()) {
                // deletes old profile img
                if (!javaFSService.deleteAsset(foundCollection.get().getBannerImg())) {
                    return null;
                }
            }

            // stores new image in java fs
            String id = javaFSService.addAsset("Collection Banner Img",file);

            // updates account and saves it to repo
            foundCollection.get().setBannerImg(id);
            collectionRepository.save(foundCollection.get());
            return id;
        }
        catch(Exception e) {
            // catches exception during updating process
            return null;
        }
    }
}
