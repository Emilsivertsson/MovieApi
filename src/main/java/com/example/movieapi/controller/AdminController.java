package com.example.movieapi.controller;

import com.example.movieapi.models.ApplicationUser;
import com.example.movieapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This class is the controller for the administration CRUD
 * It is responsible for handling the requests and responses for the admin routes
 */


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
//this annotation is used to allow requests from any origin.
//if only one origin is needed, it can be specified in the annotation upon deployment
@CrossOrigin("*")
public class AdminController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity <Optional <ApplicationUser>> getOneUser (@PathVariable Long id){
        try {
            return userService.getOneUser(id);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity <List<ApplicationUser>> getAllUsers () {
        try {
            return userService.getAllUsers();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity <ApplicationUser> updateUser (@PathVariable Long id, @RequestBody ApplicationUser user) {
        try {
            return userService.updateUser(id, user);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteUser (@PathVariable Long id) {
        try {
            return userService.deleteUser(id);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}