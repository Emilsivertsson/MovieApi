package com.example.movieapi.controller;

import com.example.movieapi.models.ApplicationUser;
import com.example.movieapi.models.LoginResponseDTO;
import com.example.movieapi.models.RegistrationDTO;
import com.example.movieapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is the controller for the authentication
 * It is responsible for handling the requests and responses for the authentication routes
 */

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    // This method is responsible for handling the register route and returns a
    // json object with the user information if successful
    @PostMapping("/register")
    public ResponseEntity <ApplicationUser> registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    // This method is responsible for handling the login route and returns a json Object
    // with the user information and a token if successful
    @PostMapping("/login")
    public ResponseEntity <LoginResponseDTO> loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}

