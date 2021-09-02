package com.quikdev.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name="release_date")
    private LocalDate releaseDate;

    private String genre;
    private String overview;

    @Column(name="cover_image")
    private String coverImage;

    private int rating;

    public Movie() { }
    public Movie(Long id, String title, LocalDate releaseDate, String genre, String overview, String coverImage, int rating) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.overview = overview;
        this.coverImage = coverImage;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
