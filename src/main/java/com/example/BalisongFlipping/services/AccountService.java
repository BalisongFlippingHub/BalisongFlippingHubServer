package com.example.BalisongFlipping.services;

import com.example.BalisongFlipping.dtos.AdminDto;
import com.example.BalisongFlipping.dtos.MakerDto;
import com.example.BalisongFlipping.dtos.UserDto;
import com.example.BalisongFlipping.modals.accounts.Account;

import com.example.BalisongFlipping.modals.accounts.Maker;
import com.example.BalisongFlipping.modals.accounts.User;
import com.example.BalisongFlipping.repositories.AccountRepository;

import org.bson.types.ObjectId;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public static Record converAccountToDto(Account account) throws Exception {
        switch(account.getRole()) {
            case ADMIN -> {
                // returns dto of admin account
                return new AdminDto(
                        account.getId(),
                        account.getEmail(),
                        account.getRole(),
                        account.getPosts()
                );

            }
            case MAKER -> {
                return new MakerDto(
                        account.getId(),
                        account.getEmail(),
                        ((Maker) account).getCompanyName(),
                        ((Maker) account).getCompanyDuration(),
                        account.getRole(),
                        account.getPosts(),
                        account.getBannerImg(),
                        account.getProfileImg(),
                        ((Maker) account).getProducts(),
                        ((Maker) account).getServices(),
                        ((Maker) account).getFacebookLink(),
                        ((Maker) account).getInstagramLink(),
                        ((Maker) account).getTwitterLink(),
                        ((Maker) account).getEmailLink(),
                        ((Maker) account).getPersonalWebsiteLink()
                );
            }
            case USER -> {
                return new UserDto(
                        account.getId(),
                        account.getEmail(),
                        ((User) account).getDisplayName(),
                        account.getRole(),
                        account.getPosts(),
                        account.getBannerImg(),
                        account.getProfileImg(),
                        ((User) account).getOwnedKnives(),
                        ((User) account).getFacebookLink(),
                        ((User) account).getTwitterLink(),
                        ((User) account).getInstagramLink()
                );
            }
            default -> {
                throw new Exception("Failed to convert based on role type");
            }
        }
    }

    public Record getSelf() throws Exception {
        // get authentication context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // get authorized account from principle
        Account currentAccount = (Account) authentication.getPrincipal();

        return converAccountToDto(currentAccount);
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
        return accountRepository.existsById(new ObjectId(accountId.substring(1, accountId.length() - 1)));
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
        Optional<Account> foundAccount = accountRepository.findById(new ObjectId(accountId.substring(1, accountId.length() - 1)));

        // if account isn't found with passed id, return false
        if (foundAccount.isEmpty()) return null;

        try {
            // checks for profile img already existing for user
            if (!Objects.equals(foundAccount.get().getProfileImg(), "")) {
                // deletes old profile img
                 if (!javaFSService.deleteAsset(foundAccount.get().getProfileImg())) {
                     return null;
                 }
            }

            // stores new image in java fs
            String id = javaFSService.addAsset("Profile Img",file);

            // updates account and saves it to repo
            foundAccount.get().setProfileImg(id);
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
        Optional<Account> foundAccount = accountRepository.findById(new ObjectId(accountId.substring(1, accountId.length() - 1)));

        // if account isn't found with passed id, return false
        if (foundAccount.isEmpty()) return null;

        try {
            // checks for banner img already existing for user
            if (!Objects.equals(foundAccount.get().getBannerImg(), "")) {
                // deletes old profile img
                if (!javaFSService.deleteAsset(foundAccount.get().getBannerImg())) {
                    return null;
                }
            }

            // stores new image in java fs
            String id = javaFSService.addAsset("Banner Img",file);

            // updates account and saves it to repo
            foundAccount.get().setBannerImg(id);
            accountRepository.save(foundAccount.get());
            return id;
        }
        catch(Exception e) {
            // catches exception during updating process
            return null;
        }
    }
}