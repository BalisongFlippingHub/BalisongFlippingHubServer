package com.example.BalisongFlipping.dtos;

import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.posts.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MakerDto extends AccountDto {
    private String companyName;
    private double companyDuration;

    public MakerDto(String uuid, String email, String companyName, double companyDuration, Date accountCreationDate, Date lastLogin, Account.Role role, List<Post> posts) {
        super(uuid, email, accountCreationDate, lastLogin, role, posts);
        this.companyDuration = companyDuration;
        this.companyName = companyName;

    }
}
