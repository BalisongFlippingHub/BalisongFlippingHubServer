package com.example.BalisongFlipping.modals.accounts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin extends Account{

    public Admin(Account currentAccount) {
        super(currentAccount);

    }
}
