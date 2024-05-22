package com.example.BalisongFlipping.modals.likes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Like {

    @Id
    private String uuid;

    private String accountId;
    private String accountDisplayName;

}
