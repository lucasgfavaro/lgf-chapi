package com.lgf.chapi.controllers;

import com.lgf.chapi.domain.FileSystemMovie;
import com.lgf.chapi.services.FileSystemMovieService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private Button loadMovie;
    @FXML
    private Button loadMoviesData;
    @FXML
    private Button renameMovies;

    @FXML
    private ListView<FileSystemMovie> directoryList = new ListView<>();

    FileSystemMovieService fileSystemMovieService = new FileSystemMovieService();

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        loadMovie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<FileSystemMovie> fileSytemMovies = fileSystemMovieService.getFSMovies();
                ObservableList<FileSystemMovie> items =FXCollections.observableArrayList (fileSytemMovies);
                directoryList.setItems(items);
                System.out.println("That was easy, wasn't it?");
            }
        });

    }

}
