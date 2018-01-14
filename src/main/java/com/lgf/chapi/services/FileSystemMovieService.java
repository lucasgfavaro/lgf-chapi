package com.lgf.chapi.services;

import com.lgf.chapi.domain.FileSystemMovie;
import com.lgf.chapi.domain.Movie;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasfavaro on 3/18/17.
 */
public class FileSystemMovieService {

    private String fsMoviesHome = "/Users/lucasfavaro/Downloads/movies";

    public String getFsMoviesHome() {
        return fsMoviesHome;
    }

    public void setFsMoviesHome(String fsMoviesHome) {
        this.fsMoviesHome = fsMoviesHome;
    }

    private String getMovieName(String fsMovieName) {
        String[] parts = fsMovieName.split("\\(");
        return rtrim(parts[0]);
    }

    public static String rtrim(String s) {
        int i = s.length()-1;
        while (i >= 0 && Character.isWhitespace(s.charAt(i))) {
            i--;
        }
        return s.substring(0,i+1);
    }

    public List<FileSystemMovie> getFSMovies() {
        List<FileSystemMovie> fileSystemMovies = new ArrayList<>();
        File file = new File(fsMoviesHome);

        File[] files = file.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (!files[i].getName().toLowerCase().contains("ds_store")) {
                fileSystemMovies.add(new FileSystemMovie(files[i], getMovieName(files[i].getName()), new Movie()));
            }
        }

        // Reading directory contents
        return fileSystemMovies;
    }

    public void renameDirectory(File file, String newName) throws Exception {

        // File (or directory) with new name
        File newFile = new File(file.getParent()+"/"+newName);

        if (newFile.exists())
            throw new java.io.IOException("File exists");

        // Rename file (or directory)
        boolean success = file.renameTo(newFile);

        if (!success) {
            throw new java.io.IOException("File couldn't be renamed");
        }
    }

}
