package com.lgf.chapi.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasfavaro on 3/18/17.
 */
public class FileSystemMovie {

    private File file;
    private String movieName;
    private Movie movie;

    public FileSystemMovie(File file, String movieName, Movie movie){
        this.file = file;
        this.movieName = movieName;
        this.movie = movie;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getMovieName() {
        return movieName;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "FileSystemMovie{" +
                "file=" + file +
                ", movieName='" + movieName + '\'' +
                '}' + movie.toString();
    }
}
