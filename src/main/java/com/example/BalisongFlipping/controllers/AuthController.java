package com.example.BalisongFlipping.controllers;

import com.example.BalisongFlipping.dtos.LoginAccountDto;
import com.example.BalisongFlipping.dtos.RegisterAccountDto;
import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.services.AccountService;
import com.example.BalisongFlipping.services.AuthService;
import com.example.BalisongFlipping.services.JwtService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final JwtService jwtService;

    private final AuthService authenticationService;

    public AuthController(JwtService jwtService, AuthService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    /**
     *
     * @param registerUserDto
     * @return
     *
     * POST ENDPOINT
     * - Takes in a body of params to register a new user. Check RegisterAccountDto for details
     * - Calls Auth Service to attempt to store new user in DB
     * - todo -- Implement defensive programming that checks incoming body for correct data
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterAccountDto registerUserDto) {
        System.out.println("Calling Post API auth/signup...");
        Account registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser.getUuid() + " successfully created.");
    }

    /**
     *
     * @param loginUserDto
     * @return
     *
     * POST ENDPOINT
     * - Takes in a body of params to check for an authenticated user.
     * - Translation... attempts to log in a user by checking user credentials and jwt passed
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginAccountDto loginUserDto) throws Exception {
        Account authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setAccount(AccountService.converAccountToDto(authenticatedUser));

        return ResponseEntity.ok(loginResponse);
    }

    @Getter
    @Setter
    public class LoginResponse {
        private String token;
        private long expiresIn;
        Record account;
    }
}
