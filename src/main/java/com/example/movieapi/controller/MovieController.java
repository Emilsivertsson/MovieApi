package com.example.movieapi.controller;

import com.example.movieapi.models.Movie;
import com.example.movieapi.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This class is the controller for the movie CRUD
 * It is responsible for handling the requests and responses for the movie routes
 */

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
//this annotation is used to allow requests from any origin.
//if only one origin is needed, it can be specified in the annotation upon deployment
@CrossOrigin("*")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity <Optional<Movie>> getOneMovie (@PathVariable Long id) {
        try {
            return movieService.getOneMovie(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity <List<Movie>> getAllMovies () {
        try {
            return movieService.getAllMovies();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity <Movie> createMovie (@RequestBody Movie movie) {
        try {
            return movieService.createMovie(movie);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteMovie (@PathVariable Long id) {
        try {
            return movieService.deleteMovie(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity <Movie> updateMovie (@PathVariable Long id, @RequestBody Movie movie) {
        try {
            return movieService.updateMovie(id, movie);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
