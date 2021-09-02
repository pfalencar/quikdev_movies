package com.quikdev.controller;

import com.quikdev.service.StasMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsMovieController {

    private StasMovieService stasMovieService;

    @Autowired
    public StatsMovieController(StasMovieService stasMovieService) {
        this.stasMovieService = stasMovieService;
    }
    public StatsMovieController() { }

    @GetMapping("/available")
    public ResponseEntity<Integer> findAvailableMovies() {
        int quantityAvailableMovies = stasMovieService.findAvailableMovies();
        return new ResponseEntity<>(quantityAvailableMovies, HttpStatus.OK);
    }

    @GetMapping("/{genre}")
    public int findMoviesByGenre(@PathVariable String genre) {
        return stasMovieService.findMoviesByGenre(genre);
    }
}
