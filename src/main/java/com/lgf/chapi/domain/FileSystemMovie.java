package com.lgf.chapi.domain;

import java.io.File;

/**
 * Created by lucasfavaro on 3/18/17.
 */
public class FileSystemMovie {

    private File file;
    private String movieName;

    public FileSystemMovie(File file, String movieName){
        this.file = file;
        this.movieName = movieName;
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

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public String toString() {
        return "FileSystemMovie{" +
                "file=" + file +
                ", movieName='" + movieName + '\'' +
                '}';
    }
}
