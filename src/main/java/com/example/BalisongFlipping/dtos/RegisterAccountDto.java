package com.example.BalisongFlipping.dtos;

import com.example.BalisongFlipping.modals.accounts.Account;


public record RegisterAccountDto(
         String email,
         String displayName,
         String password
) {}
