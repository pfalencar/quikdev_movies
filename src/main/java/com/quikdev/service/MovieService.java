package com.quikdev.service;

import com.quikdev.dto.MoviePostRequestBody;
import com.quikdev.dto.MoviePutRequestBody;
import com.quikdev.exception.MovieNotFoundException;
import com.quikdev.model.Movie;
import com.quikdev.repository.MovieRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private MovieRespository movieRepository;

    @Autowired
    public MovieService(MovieRespository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public MovieService() { }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Long movieId) throws MovieNotFoundException {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (!movieOptional.isPresent()) {
            throw new MovieNotFoundException("Filme com este ID não foi encontrado.");
        }
        return movieOptional;
    }

    public Movie save(MoviePostRequestBody moviePostRequestBody) {
        return movieRepository.save(build(moviePostRequestBody));
    }

    private Movie build(Movie movie1) {
        Movie movie = new Movie();
        movie.setTitle(movie1.getTitle());
        movie.setReleaseDate(movie1.getReleaseDate());
        movie.setGenre(movie1.getGenre());
        movie.setOverview(movie1.getOverview());
        movie.setCoverImage(movie1.getCoverImage());
        movie.setRating(movie1.getRating());
        return movie;
    }

    public void replace(MoviePutRequestBody moviePutRequestBody) {
        Movie savedMovie = findByIdOrThrowBadRequestException(moviePutRequestBody.getId());
        Movie movie = build(moviePutRequestBody);
        movie.setId(savedMovie.getId());
        movieRepository.save(movie);
    }

    public Movie findByIdOrThrowBadRequestException(long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not Found"));
    }

    public void delete(Long id) {
        movieRepository.delete(findByIdOrThrowBadRequestException(id));
    }
}
