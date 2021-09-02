package com.quikdev.controller;

import com.quikdev.dto.MoviePostRequestBody;
import com.quikdev.dto.MoviePutRequestBody;
import com.quikdev.exception.MovieNotFoundException;
import com.quikdev.model.Movie;
import com.quikdev.service.MovieService;
import com.quikdev.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private MovieService movieService;
    private TokenService tokenService;

    @Autowired
    public MovieController(MovieService movieService, TokenService tokenService) {
        this.movieService = movieService;
        this.tokenService = tokenService;
    }
    public MovieController() { }


    @GetMapping
    public List<Movie> listdAll (@RequestHeader String authorization) {
        tokenService.validate(authorization);
        return movieService.findAll();
    }

    @GetMapping("/{movieId}")
    public Movie findbyId(@PathVariable Long movieId, @RequestHeader String authorization) throws MovieNotFoundException {
        tokenService.validate(authorization);
        Optional<Movie> movieOptional = movieService.findById(movieId);
        Movie movie = movieOptional.orElseThrow(MovieNotFoundException::new);
        return movie;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody MoviePostRequestBody moviePostRequestBody,
                                       @RequestHeader String authorization) {
        tokenService.validate(authorization);
        movieService.save(moviePostRequestBody);
        return new ResponseEntity<>("Movie saved successfully", HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody MoviePutRequestBody moviePutRequestBody,
                                        @RequestHeader String authorization) {
        tokenService.validate(authorization);
        movieService.replace(moviePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader String authorization) {
        tokenService.validate(authorization);
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
