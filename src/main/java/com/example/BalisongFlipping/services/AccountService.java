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
    public static UserDto convertAccountToDto(Account account) throws Exception {
        if (account == null)
            throw new Exception("Passed account is null.");

        return new UserDto(
                account.getId(),
                account.getEmail(),
                ((User) account).getDisplayName(),
                "USER",
                ((User) account).getCollectionId(),
                ((User) account).getBannerImg(),
                ((User) account).getProfileImg(),
                ((User) account).getFacebookLink(),
                ((User) account).getTwitterLink(),
                ((User) account).getInstagramLink(),
                ((User) account).getYoutubeLink(),
                ((User) account).getDiscordLink(),
                ((User) account).getRedditLink(),
                ((User) account).getPersonalEmailLink(),
                ((User) account).getPersonalWebsiteLink()
        );
    }

    public Account getAccount(String accountId) throws Exception {
        return accountRepository.findById(new ObjectId(accountId)).get();
    }

    public UserDto getSelf() throws Exception {
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

    private boolean validateDisplayName(String newDisplayName) {
        // return false if empty string
        System.out.println(newDisplayName);
        if (newDisplayName.isEmpty()) return false;

        // return false if passed string is smaller than 4 characters
        if (newDisplayName.length() < 4) return false;

        int numLetters = 0;
        int numNumbers = 0;

        for (int i = 0; i < newDisplayName.length(); i++) {
            // if character isn't a valid character, letter, or number, return false
            if (!Character.isDigit(newDisplayName.charAt(i)) && !Character.isLetter(newDisplayName.charAt(i)) && newDisplayName.charAt(i) != '_') return false;

            if (Character.isLetter(newDisplayName.charAt(i))) {
                numLetters += 1;
            }
            else if (Character.isDigit(newDisplayName.charAt(i))) {
                numNumbers += 1;
            }
        }

        if (numNumbers > numLetters) return false;

        if (numLetters == 0) return false;

        return true;
    }

    public String updateFacebookLink(String newLink, String accountId) throws Exception {
        try {


            Optional<Account> foundAccount = accountRepository.findById(new ObjectId(accountId));

            if (foundAccount.isEmpty()) throw new Exception("Account Not Found.");

            User castedAccount = (User) foundAccount.get();

            castedAccount.setFacebookLink(newLink);
            accountRepository.save(castedAccount);

            return newLink;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public String updateInstagramLink(String newLink, String accountId) throws Exception {
        try {


            Optional<Account> foundAccount = accountRepository.findById(new ObjectId(accountId));

            if (foundAccount.isEmpty()) throw new Exception("Account Not Found.");

            User castedAccount = (User) foundAccount.get();

            castedAccount.setInstagramLink(newLink);
            accountRepository.save(castedAccount);

            return newLink;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public String updateTwitterLink(String newLink, String accountId) throws Exception {
        try {


            Optional<Account> foundAccount = accountRepository.findById(new ObjectId(accountId));

            if (foundAccount.isEmpty()) throw new Exception("Account Not Found.");

            User castedAccount = (User) foundAccount.get();

            castedAccount.setTwitterLink(newLink);
            accountRepository.save(castedAccount);

            return newLink;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public String updateYoutubeLink(String newLink, String accountId) throws Exception {
        try {


            Optional<Account> foundAccount = accountRepository.findById(new ObjectId(accountId));

            if (foundAccount.isEmpty()) throw new Exception("Account Not Found.");

            User castedAccount = (User) foundAccount.get();

            castedAccount.setYoutubeLink(newLink);
            accountRepository.save(castedAccount);

            return newLink;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public String updateRedditLink(String newLink, String accountId) throws Exception {
        try {


            Optional<Account> foundAccount = accountRepository.findById(new ObjectId(accountId));

            if (foundAccount.isEmpty()) throw new Exception("Account Not Found.");

            User castedAccount = (User) foundAccount.get();

            castedAccount.setRedditLink(newLink);
            accountRepository.save(castedAccount);

            return newLink;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public String updateDiscordLink(String newLink, String accountId) throws Exception {
        try {


            Optional<Account> foundAccount = accountRepository.findById(new ObjectId(accountId));

            if (foundAccount.isEmpty()) throw new Exception("Account Not Found.");

            User castedAccount = (User) foundAccount.get();

            castedAccount.setDiscordLink(newLink);
            accountRepository.save(castedAccount);

            return newLink;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public String updatePersonalEmailLink(String newLink, String accountId) throws Exception {
        try {


            Optional<Account> foundAccount = accountRepository.findById(new ObjectId(accountId));

            if (foundAccount.isEmpty()) throw new Exception("Account Not Found.");

            User castedAccount = (User) foundAccount.get();

            castedAccount.setPersonalEmailLink(newLink);
            accountRepository.save(castedAccount);

            return newLink;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public String updatePersonalWebsiteLink(String newLink, String accountId) throws Exception {
        try {


            Optional<Account> foundAccount = accountRepository.findById(new ObjectId(accountId));

            if (foundAccount.isEmpty()) throw new Exception("Account Not Found.");

            User castedAccount = (User) foundAccount.get();

            castedAccount.setPersonalWebsiteLink(newLink);
            accountRepository.save(castedAccount);

            return newLink;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public String changeDisplayName(String accountID, String newDisplayName) throws Exception {
        try {
            // get object
            Optional<Account> foundAccount = accountRepository.findById(new ObjectId(accountID));

            // check if it is empty
            if (foundAccount.isEmpty()) {
                throw new Exception("Couldn't find account.");
            }

            // validate new display name
            if (!validateDisplayName(newDisplayName)) {
                throw new Exception("Display name not valid.");
            }

            User accountObj = ((User) foundAccount.get());
            accountObj.setDisplayName(newDisplayName);
            accountRepository.save(accountObj);

            return newDisplayName;
        }
        catch (Exception e) {
            throw e;
        }
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