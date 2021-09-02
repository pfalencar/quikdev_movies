package com.quikdev.service;

import com.quikdev.model.Movie;
import com.quikdev.repository.MovieRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StasMovieService {

    private MovieRespository movieRespository;

    @Autowired
    public StasMovieService(MovieRespository movieRespository) {
        this.movieRespository = movieRespository;
    }
    public StasMovieService() { }


    public int findAvailableMovies() {
        List<Movie> moviesAvailableByReleaseDate = new ArrayList<>();

        List<Movie> movies = movieRespository.findAll();
        for (int i = 0; i < movies.size(); i++) {
            LocalDate dataHj = LocalDate.now();
            if (movies.get(i).getReleaseDate().isBefore(dataHj)) {
                moviesAvailableByReleaseDate.add(movies.get(i));
            }
        }
        return moviesAvailableByReleaseDate.size();
    }

    public int findMoviesByGenre(String genre) {
        List<Movie> moviesByGenre = movieRespository.findMoviesByGenre(genre);
        return moviesByGenre.size();
    }
}
