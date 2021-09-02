package com.dio.jwt.repository;

import com.dio.jwt.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRespository extends JpaRepository<Movie, Long> {

    List<Movie> findMoviesByGenre (String genre);

}
