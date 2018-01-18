package com.lgf.chapi.services;

import com.lgf.chapi.domain.FileSystemMovie;
import com.lgf.chapi.domain.Movie;
import com.lgf.chapi.domain.ResponsePage;
import com.lgf.chapi.respositories.MovieRepositoryImplFeign;
import javafx.scene.control.ProgressBar;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void getMoviesMetadata(List<FileSystemMovie> fileSystemMovies, ProgressBar progressBar) {

        for (FileSystemMovie fileSystemMovie : fileSystemMovies) {
            //progressBar.setProgress(0.25F);
            List<Movie> movies = remoteMovieService.getMovie(fileSystemMovie.getMovieName());

            if (movies != null && !movies.isEmpty()) {
                if (movies.size() == 1) {
                    fileSystemMovie.setMovie(movies.get(0));
                } else {
                    List<Movie> filteredMovies = movies.stream().filter(movie -> movie.getTitle().equals(fileSystemMovie.getMovieName())
                    ).collect(Collectors.toList());
                    if (filteredMovies != null && filteredMovies.size()>0) {
                        fileSystemMovie.setMovie(filteredMovies.get(0));
                    }
                }
            }
            System.out.println("FS Movie: " + fileSystemMovie.getMovieName() + " <--------- Remote: "+fileSystemMovie.getMovie().getTitle());
        }
    }

    public String buildDirectoryName(Movie movie) {
        return (movie.getTitle().replace(":","").replace("/","-") + " (" + movie.getRating() + ") "
                + movie.getGenres().toString() +
                " (" + movie.getYear() + ")" +
                " (" + movie.getMpa_rating() + ")" +
                " (" + movie.getRuntime() + ") "

        );
    }

    public void renameMovies(List<FileSystemMovie> fileSystemMovies) {

        fileSystemMovies.forEach(fileSystemMovie -> {
            if (fileSystemMovie.getMovie() != null && !fileSystemMovie.getMovieName().isEmpty()) {
                try {
                    fileSystemMovieService.renameDirectory(fileSystemMovie.getFile(), buildDirectoryName(fileSystemMovie.getMovie()));
                } catch (Exception e) {

                }
            }
        });

    }
}
