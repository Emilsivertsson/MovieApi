package com.example.movieapi.service;

import java.util.HashSet;
import java.util.Set;

import com.example.movieapi.models.ApplicationUser;
import com.example.movieapi.models.LoginResponseDTO;
import com.example.movieapi.models.Role;
import com.example.movieapi.repository.RoleRepository;
import com.example.movieapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    /**
     * This method is responsible for registering a new user
     * it takes username and password as parameters
     * encodes the password and gets the user role
     * creates a new user with the parameters and
     * saves and returns the user.
     */

    public ResponseEntity<ApplicationUser> registerUser(String username, String password){
        try{
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        ApplicationUser newUser = new ApplicationUser(0, username, encodedPassword, authorities);

        return ResponseEntity.ok(userRepository.save(newUser));
        } catch (Exception e){
            return ResponseEntity.status(400).build();
        }
    }

    /**
     * This method is responsible for logging in a user
     * it takes username and password as parameters
     * checks if the user exists
     * authenticates the user
     * generates a token and returns the user and token
     */
    public ResponseEntity <LoginResponseDTO> loginUser(String username, String password){
        try{
            if(!userRepository.existsByUsername(username)){
                return ResponseEntity.status(400).build();
            }
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            String token = tokenService.generateJwt(auth);
            LoginResponseDTO loggedUser = new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
            return ResponseEntity.ok(loggedUser);

        } catch(AuthenticationException e){
            return ResponseEntity.status(401).build();
        }
    }

}
