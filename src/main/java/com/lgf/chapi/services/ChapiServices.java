package com.lgf.chapi.services;

import com.lgf.chapi.domain.FileSystemMovie;
import com.lgf.chapi.domain.Movie;
import com.lgf.chapi.domain.ResponsePage;
import com.lgf.chapi.respositories.MovieRepositoryImplFeign;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * Created by lucasfavaro on 3/18/17.
 */


public class ChapiServices {
    private FileSystemMovieService fileSystemMovieService;
    private RemoteMovieService remoteMovieService;

    public ChapiServices(FileSystemMovieService fsMovieService, RemoteMovieService remoteMovieService) {
        this.fileSystemMovieService = fsMovieService;
        this.remoteMovieService = remoteMovieService;
    }

    public void normalizeMovies() {

        List<FileSystemMovie> fileSystemMovies = fileSystemMovieService.getFSMovies();

        for (FileSystemMovie fileSystemMovie : fileSystemMovies) {
            Optional<Movie> movie = remoteMovieService.getMovie(fileSystemMovie.getMovieName());
            try {
                if (movie.isPresent()) {
                    System.out.println("Renombra: " + fileSystemMovie.getFile().getName() + "->" + movie.get().getTitle());
                    fileSystemMovieService.renameDirectory(fileSystemMovie.getFile(), buildDirectoryName(movie.get()));
                }
            } catch (Exception e) {

            }
        }
    }

    public String buildDirectoryName(Movie movie) {
        return (movie.getTitle() + " (" + movie.getYear() + ")" +
                " (" + movie.getRating() + ")" +
                " (" + movie.getMpa_rating() + ")" +
                " (" + movie.getRuntime() + ") "
                + movie.getGenres().toString()
        );
    }
}
