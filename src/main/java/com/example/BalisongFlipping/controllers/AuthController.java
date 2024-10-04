package com.example.BalisongFlipping.controllers;

import com.example.BalisongFlipping.dtos.LoginAccountDto;
import com.example.BalisongFlipping.dtos.RegisterAccountDto;
import com.example.BalisongFlipping.modals.accounts.Account;
import com.example.BalisongFlipping.modals.tokens.RefreshToken;
import com.example.BalisongFlipping.services.AccountService;
import com.example.BalisongFlipping.services.AuthService;
import com.example.BalisongFlipping.services.JwtService;
import com.example.BalisongFlipping.services.RefreshTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    private final AuthService authenticationService;

    public AuthController(JwtService jwtService, RefreshTokenService refreshTokenService, AuthService authenticationService) {
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/check-token")
    public ResponseEntity<?> checkToken(@RequestParam String token) {

        return ResponseEntity.ok("token valid");
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
        // validate new user info
        if (!authenticationService.validateNewUser(registerUserDto)) {
            return ResponseEntity.badRequest().body("Invalid user info passed.");
        }

        // create new user in db
        Account registeredUser = authenticationService.signup(registerUserDto);

        // return if email is found to already exist
        if (registeredUser == null) {
            return ResponseEntity.badRequest().body("Email already exists.");
        }

        // successful account creation
        return ResponseEntity.ok(registeredUser.getId() + " successfully created.");
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
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginAccountDto loginUserDto, HttpServletResponse response) throws Exception {
        // attempts to retrieve account from authentication service
        Account authenticatedUser = authenticationService.authenticate(loginUserDto);

        // creates new access token
        String accessToken = jwtService.generateAccessToken(authenticatedUser);

        // create new refresh token
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(loginUserDto.email());

        // set refresh token in cookie
        Cookie refreshTokenCookie = new Cookie("refreshTokenCookie", refreshToken.getToken());
        refreshTokenCookie.isHttpOnly();
        refreshTokenCookie.setSecure(true);

        response.addCookie(refreshTokenCookie);

        // creates new login response
        LoginResponse loginResponse = new LoginResponse();

        // sets values for login response
        loginResponse.setToken(accessToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setAccount(AccountService.convertAccountToDto(authenticatedUser));

        return ResponseEntity.ok(loginResponse);
    }

    @Getter
    @Setter
    private class LoginResponse {
        private String token;
        private long expiresIn;
        Record account;
    }
}
