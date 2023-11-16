package com.example.movieapi.controller;

import com.example.movieapi.models.ApplicationUser;
import com.example.movieapi.repository.UserRepository;
import com.example.movieapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity <Optional <ApplicationUser>> getOneUser (@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.getOneUser(id).getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity <List<ApplicationUser>> getAllUsers () {
        try {
            return ResponseEntity.ok(userService.getAllUsers().getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity <ApplicationUser> updateUser (@PathVariable Long id, @RequestBody ApplicationUser user) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, user).getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteUser (@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.deleteUser(id).getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}