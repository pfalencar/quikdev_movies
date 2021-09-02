package com.dio.jwt.controller;

import com.dio.jwt.dto.MoviePostRequestBody;
import com.dio.jwt.dto.MoviePutRequestBody;
import com.dio.jwt.exception.MovieNotFoundException;
import com.dio.jwt.model.Movie;
import com.dio.jwt.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    public MovieController() { }

    @GetMapping
    public List<Movie> listdAll () { return movieService.findAll(); }

    @GetMapping("/{movieId}")
    public Movie findbyId(@PathVariable Long movieId) throws MovieNotFoundException {
        Optional<Movie> movieOptional = movieService.findById(movieId);
        Movie movie = movieOptional.orElseThrow(MovieNotFoundException::new);
        return movie;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody MoviePostRequestBody moviePostRequestBody) {
        movieService.save(moviePostRequestBody);
        return new ResponseEntity<>("Movie saved successfully", HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody MoviePutRequestBody moviePutRequestBody) {
        movieService.replace(moviePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
