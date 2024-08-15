package com.example.BalisongFlipping.services;

import com.example.BalisongFlipping.dtos.UserDto;
import com.example.BalisongFlipping.modals.accounts.Account;

import com.example.BalisongFlipping.modals.accounts.User;
import com.example.BalisongFlipping.repositories.AccountRepository;

import org.bson.types.ObjectId;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private static  JavaFSService javaFSService;

    public AccountService(AccountRepository accountRepository, JavaFSService javaFSService) {
        this.accountRepository = accountRepository;
        this.javaFSService = javaFSService;
    }

    /***
     *
     * @param account       (Account of any sub type passed to function to be converted to the dto equivilant)
     * @return              (Returns the dto of every type of account based on the role of each account
     * @throws Exception    (throws excepion based on failed update)
     */
    public static Record convertAccountToDto(Account account) throws Exception {
        return new UserDto(
                account.getId(),
                account.getEmail(),
                ((User) account).getDisplayName(),
                "USER",
                ((User) account).getCollectionId(),
                account.getPosts(),
                ((User) account).getBannerImg(),
                ((User) account).getProfileImg(),
                ((User) account).getFacebookLink(),
                ((User) account).getTwitterLink(),
                ((User) account).getInstagramLink(),
                ((User) account).getYoutubeLink(),
                ((User) account).getDiscordLink(),
                ((User) account).getRedditLink()
        );
    }

    public Record getSelf() throws Exception {
        // get authentication context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // get authorized account from principle
        Account currentAccount = (Account) authentication.getPrincipal();

        return convertAccountToDto(currentAccount);
    }

    public List<Account> allUsers() {
        List<Account> accounts = new ArrayList<>();

        accounts = accountRepository.findAll();

        return accounts;
    }

    /***
     *
     * @param accountId     (String to represend the id of a account saved in the repo)
     * @return              (Returns true/false value for account existing in repo based on passed id
     */
    public boolean checkForAccountExistance(String accountId) {
        return !accountRepository.existsById(new ObjectId(accountId));
    }

    /***
     *
     * @param accountId     (String to represend the id of an account saved in the repo)
     * @param file          (Multipart file that needs to be stored
     * @return              (Returns boolean value based on successful storage of image and update on account)
     *
     * Function takes in an image and an id to identify the account a user wannts to update the profile image on,
     * then attempts to update that profile image on the db for that user.
     */
    public String updateProfileImg(String accountId, MultipartFile file)  {
        Optional<Account> foundAccount = accountRepository.findById(new ObjectId(accountId));

        // if account isn't found with passed id, return false
        if (foundAccount.isEmpty()) return null;

        try {
            // checks for profile img already existing for user
            if (!((User) foundAccount.get()).getProfileImg().isEmpty()) {
                // deletes old profile img
                 if (!javaFSService.deleteAsset(((User) foundAccount.get()).getProfileImg())) {
                     return null;
                 }
            }

            // stores new image in java fs
            String id = javaFSService.addAsset("Profile Img",file);

            // updates account and saves it to repo
            ((User) foundAccount.get()).setProfileImg(id);
            accountRepository.save(foundAccount.get());

            return id;
        }
        catch(Exception e) {
            // catches exception during updating process 
            return null;
        }
    }

    /***
     *
     * @param accountId     (String to represend the id of an account saved in the repo)
     * @param file          (Multipart file that needs to be stored
     * @return              (Returns boolean value based on successful storage of image and update on account)
     *
     * Function takes in an image and an id to identify the account a user wannts to update the babber image on,
     * then attempts to update that banner image on the db for that user.
     */
    public String updateBannerImg(String accountId, MultipartFile file) {
        Optional<Account> foundAccount = accountRepository.findById(new ObjectId(accountId));

        // if account isn't found with passed id, return false
        if (foundAccount.isEmpty()) return null;

        try {
            // checks for banner img already existing for user
            if (!((User) foundAccount.get()).getBannerImg().isEmpty()) {
                // deletes old profile img
                if (!javaFSService.deleteAsset(((User) foundAccount.get()).getBannerImg())) {
                    return null;
                }
            }

            // stores new image in java fs
            String id = javaFSService.addAsset("Banner Img",file);

            // updates account and saves it to repo
            ((User) foundAccount.get()).setBannerImg(id);
            accountRepository.save(foundAccount.get());
            return id;
        }
        catch(Exception e) {
            // catches exception during updating process
            return null;
        }
    }
}