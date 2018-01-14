package com.lgf.chapi.services;

import com.lgf.chapi.domain.Movie;
import com.lgf.chapi.domain.ResponsePage;
import com.lgf.chapi.respositories.MovieRepositoryImplFeign;
import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by lucasfavaro on 3/19/17.
 */
public class RemoteMovieService {

    private MovieRepositoryImplFeign movieRepositoryImplFeign;

    public RemoteMovieService(MovieRepositoryImplFeign movieRepositoryImplFeign) {
        this.movieRepositoryImplFeign = movieRepositoryImplFeign;
    }

    public List<Movie> getMovie(String name) {

        List<Movie> movies = movieRepositoryImplFeign.getMovie(name).getData().getMovies();
        return movies;
    }
}
