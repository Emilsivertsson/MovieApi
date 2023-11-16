package com.example.movieapi.service;



import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.movieapi.models.ApplicationUser;
import com.example.movieapi.models.Movie;
import com.example.movieapi.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            log.info("User found");
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
        } catch (Exception e) {
            log.info("User not found");
            throw new UsernameNotFoundException("user is not valid");
        }
    }

    public ResponseEntity<Optional<ApplicationUser>> getOneUser (Long id){
        try{
            if (userRepository.existsById(id)){
                log.info("User found");
                return ResponseEntity.ok(userRepository.findById(id));
            } else {
                log.info("User not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            log.info("Error getting user");
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity <List<ApplicationUser>> getAllUsers () {
        try {
            List<ApplicationUser> users = userRepository.findAll();
            if(users.isEmpty()) {
                log.info("No users found");
                return ResponseEntity.notFound().build();
            } else {
                log.info("Users found");
                return ResponseEntity.ok(users);
            }
        } catch (Exception e) {
            log.info("Error getting users");
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<String> deleteUser(Long id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                log.info("User deleted successfully");
                return ResponseEntity.ok("User deleted successfully");
            } else {
                log.info("User not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.info("Error deleting User");
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<ApplicationUser> updateUser(Long id, ApplicationUser movie) {
        try {
            ApplicationUser userToUpdate = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            userToUpdate.setUsername(movie.getUsername());
            userToUpdate.setPassword(movie.getPassword());
            log.info("User updated successfully");
            return ResponseEntity.ok(userRepository.save(userToUpdate));
        } catch (Exception e) {
            log.info("Error updating user");
            return ResponseEntity.badRequest().build();
        }
    }
}
