package com.lgf.chapi.domain;

import com.lgf.chapi.domain.Movie;

import java.util.List;
import java.util.Optional;

/**
 * Created by lucasfavaro on 3/18/17.
 */
public class Page {

    Integer movie_count;
    Integer limit;
    Integer page_number;
    List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getMovie_count() {
        return movie_count;
    }

    public void setMovie_count(Integer movie_count) {
        this.movie_count = movie_count;
    }

    public Integer getPage_number() {
        return page_number;
    }

    public void setPage_number(Integer page_number) {
        this.page_number = page_number;
    }

    @Override
    public String toString() {
        return "Page{" +
                "movie_count=" + movie_count +
                ", limit=" + limit +
                ", page_number=" + page_number +
                ", movies=" + movies +
                '}';
    }
}
