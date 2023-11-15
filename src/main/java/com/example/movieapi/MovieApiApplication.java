package com.example.movieapi;

import com.example.movieapi.models.ApplicationUser;
import com.example.movieapi.models.Role;
import com.example.movieapi.repository.RoleRepository;
import com.example.movieapi.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MovieApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApiApplication.class, args);
    }
    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
        return args ->{
            if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncode.encode("password"), roles);

            userRepository.save(admin);
        };
    }
}
