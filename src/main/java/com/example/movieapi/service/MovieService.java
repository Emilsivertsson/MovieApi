package com.example.movieapi.service;

import com.example.movieapi.models.Movie;
import com.example.movieapi.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * This class is the service for the movie CRUD
 * It is responsible for handling the business logic for the movie routes
 */

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;
    public ResponseEntity<String> deleteMovie(Long id) {
        try {
            if (movieRepository.existsById(id)) {
                movieRepository.deleteById(id);
                log.info("Movie deleted successfully");
                return ResponseEntity.ok("Movie deleted successfully");
            } else {
                log.info("Movie not found");
                return ResponseEntity.status(404).body("Movie not found");
            }
        } catch (Exception e) {
            log.info("Error deleting movie");
            return ResponseEntity.status(400).build();
        }
    }

    public ResponseEntity<Movie> updateMovie(Long id, Movie movie) {
        try {
            Movie movieToUpdate = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
            movieToUpdate.setTitle(movie.getTitle());
            movieToUpdate.setYear(movie.getYear());
            log.info("Movie updated successfully");
            return ResponseEntity.ok(movieRepository.save(movieToUpdate));
        } catch (Exception e) {
            log.info("Error updating movie");
            return ResponseEntity.status(400).build();
        }
    }

    public  ResponseEntity <Optional<Movie>> getOneMovie(Long id) {
        try {
            if (movieRepository.existsById(id)) {
                log.info("Movie found");
                return ResponseEntity.ok(movieRepository.findById(id));
            } else {
                log.info("Movie not found");
                return ResponseEntity.status(204).body(Optional.empty());
            }
        } catch (Exception e) {
            log.info("Error getting movie");
            return ResponseEntity.status(400).body(Optional.empty());
        }
    }

    public ResponseEntity <List<Movie>> getAllMovies () {
        try {
            List<Movie> movies = movieRepository.findAll();
            if(movies.isEmpty()) {
                log.info("No movies found");
                return ResponseEntity.status(204).body(List.of());
            } else {
                log.info("Movies found");
                return ResponseEntity.ok(movies);
            }
        } catch (Exception e) {
            log.info("Error getting movies");
            return ResponseEntity.status(400).body(List.of());
        }
    }

    public ResponseEntity<Movie> createMovie(Movie movie) {
        try {
            log.info("Movie created successfully");
            return ResponseEntity.ok(movieRepository.save(movie));
        } catch (Exception e) {
            log.info("Error creating movie");
            return ResponseEntity.status(400).build();
        }
    }
}
