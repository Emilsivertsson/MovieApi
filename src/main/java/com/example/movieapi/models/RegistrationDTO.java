package com.example.movieapi.models;

import lombok.*;


/**
 * This class is used to represent the registration data for the register route
 * It contains the username and password
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegistrationDTO {
    private String username;
    private String password;


}