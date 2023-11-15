package com.example.movieapi.service;

import com.example.movieapi.models.Movie;
import com.example.movieapi.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Slf4j
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public ResponseEntity<String> deleteMovie(Long id) {
        try {
            if (movieRepository.existsById(id)) {
                movieRepository.deleteById(id);
                log.info("Movie deleted successfully");
                return ResponseEntity.ok("Movie deleted successfully");
            } else {
                log.info("Movie not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.info("Error deleting movie");
            return ResponseEntity.badRequest().build();
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
            return ResponseEntity.badRequest().build();
        }
    }

    public  ResponseEntity <Optional<Movie>> getOneMovie(Long id) {
        try {
            if (movieRepository.existsById(id)) {
                log.info("Movie found");
                return ResponseEntity.ok(movieRepository.findById(id));
            } else {
                log.info("Movie not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.info("Error getting movie");
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity <List<Movie>> getAllMovies () {
        try {
            List<Movie> movies = movieRepository.findAll();
            if(movies.isEmpty()) {
                log.info("No movies found");
                return ResponseEntity.notFound().build();
            } else {
                log.info("Movies found");
                return ResponseEntity.ok(movies);
            }
        } catch (Exception e) {
            log.info("Error getting movies");
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Movie> createMovie(Movie movie) {
        try {
            log.info("Movie created successfully");
            return ResponseEntity.ok(movieRepository.save(movie));
        } catch (Exception e) {
            log.info("Error creating movie");
            return ResponseEntity.badRequest().build();
        }
    }
}
