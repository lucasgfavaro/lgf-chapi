package com.lgf.chapi.services;

import com.lgf.chapi.domain.FileSystemMovie;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasfavaro on 3/18/17.
 */
public class FileSystemMovieService {

    private static final String FS_MOVIES_HOME = "/Users/lucasfavaro/Downloads/moviesAux";

    private String getMovieName(String fsMovieName) {
        String[] parts = fsMovieName.split("\\(");
        return parts[0];
    }


    public List<FileSystemMovie> getFSMovies() {
        List<FileSystemMovie> fileSystemMovies = new ArrayList<FileSystemMovie>();
        File file = new File(FS_MOVIES_HOME);

        File[] files = file.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (!files[i].getName().toLowerCase().contains("ds_store")) {
                fileSystemMovies.add(new FileSystemMovie(files[i], getMovieName(files[i].getName())));
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
