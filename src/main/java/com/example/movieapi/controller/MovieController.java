package com.example.movieapi.controller;

import com.example.movieapi.models.Movie;
import com.example.movieapi.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@NoArgsConstructor
@AllArgsConstructor
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity <Optional<Movie>> getOneMovie (@PathVariable Long id) {
        try {
            return ResponseEntity.ok(movieService.getOneMovie(id).getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity <List<Movie>> getAllMovies () {
        try {
            return ResponseEntity.ok(movieService.getAllMovies().getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity <Movie> createMovie (@RequestBody Movie movie) {
        try {
            return ResponseEntity.ok(movieService.createMovie(movie).getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteMovie (@PathVariable Long id) {
        try {
            return ResponseEntity.ok(movieService.deleteMovie(id).getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity <Movie> updateMovie (@PathVariable Long id, @RequestBody Movie movie) {
        try {
            return ResponseEntity.ok(movieService.updateMovie(id, movie).getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
