package com.dio.jwt.controller;

import com.dio.jwt.model.Movie;
import com.dio.jwt.service.StasMovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsMovieController {

    private StasMovieService stasMovieService;

    public StatsMovieController(StasMovieService stasMovieService) {
        this.stasMovieService = stasMovieService;
    }
    public StatsMovieController() { }

    @GetMapping("/available")
    public ResponseEntity<Integer> findAvailableMovies() {
        int quantityAvailableMovies = stasMovieService.findAvailableMovies();
        return new ResponseEntity<>(quantityAvailableMovies, HttpStatus.FOUND);
    }

    @GetMapping("/{genre}")
    public int findMoviesByGenre(@PathVariable String genre) {
        return stasMovieService.findMoviesByGenre(genre);
    }
}
