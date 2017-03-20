package com.lgf.chapi.services;

import com.lgf.chapi.domain.Movie;
import com.lgf.chapi.domain.ResponsePage;
import com.lgf.chapi.respositories.MovieRepositoryImplFeign;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Optional;

/**
 * Created by lucasfavaro on 3/19/17.
 */
public class RemoteMovieService {

    private MovieRepositoryImplFeign movieRepositoryImplFeign;

    public RemoteMovieService(MovieRepositoryImplFeign movieRepositoryImplFeign) {
        this.movieRepositoryImplFeign = movieRepositoryImplFeign;
    }

    public Optional<Movie> getMovie(String name) {

        Optional<Movie> movie = Optional.empty();

        ResponsePage responsePage = movieRepositoryImplFeign.getMovie(name);
        if (responsePage.getData().getMovie_count() == 1){
            movie = Optional.of(responsePage.getData().getMovies().get(0));
        }

        return movie;
    }
}
