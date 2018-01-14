package com.lgf.chapi.controllers;

import com.lgf.chapi.domain.FileSystemMovie;
import com.lgf.chapi.domain.Movie;
import com.lgf.chapi.respositories.MovieRepositoryImplFeign;
import com.lgf.chapi.services.ChapiServices;
import com.lgf.chapi.services.FileSystemMovieService;
import com.lgf.chapi.services.RemoteMovieService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button getDownloadedMoviesButton;
    @FXML
    private Button getMoviesMetadataButton;
    @FXML
    final ProgressBar loadMoviesMetadataProgressBar = new ProgressBar(0);
    @FXML
    private Button    renameMoviesButton;
    @FXML
    private Button changeFsMovieHomeDirectoryButton;
    @FXML
    private ListView<FileSystemMovie> directoryList = new ListView<>();
    @FXML
    private TableView<Movie> moviesTable = new TableView<>();
    @FXML
    private TextField fsMovieHomeDirectory;

    private List<FileSystemMovie> fileSystemMovies;

    RemoteMovieService remoteMovieService = new RemoteMovieService(new MovieRepositoryImplFeign());
    FileSystemMovieService fileSystemMovieService = new FileSystemMovieService();
    ChapiServices chapiServices = new ChapiServices(fileSystemMovieService, remoteMovieService);

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        moviesTable.setEditable(true);
        TableColumn nameCol = new TableColumn("Title");
        nameCol.setMinWidth(300);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("title"));
        TableColumn yearCol = new TableColumn("Year");
        yearCol.setMinWidth(300);
        yearCol.setCellValueFactory(
                new PropertyValueFactory<>("year"));

        moviesTable.getColumns().addAll(nameCol, yearCol);


        fsMovieHomeDirectory.setText(fileSystemMovieService.getFsMoviesHome());

        changeFsMovieHomeDirectoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                directoryList.getItems().clear();
                directoryList.refresh();
                fileSystemMovieService.setFsMoviesHome(fsMovieHomeDirectory.getText());
            }
        });

        directoryList.setCellFactory(new Callback<ListView<FileSystemMovie>, ListCell<FileSystemMovie>>() {
            @Override
            public ListCell<FileSystemMovie> call(ListView<FileSystemMovie> p) {

                ListCell<FileSystemMovie> cell = new ListCell<FileSystemMovie>() {

                    @Override
                    protected void updateItem(FileSystemMovie t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getMovieName());
                        }
                    }
                };
                return cell;
            }
        });
    }

    @FXML
    private void getDownloadedMovies(ActionEvent event) {
        directoryList.getItems().clear();
        fileSystemMovies = fileSystemMovieService.getFSMovies();
        ObservableList<FileSystemMovie> items = FXCollections.observableArrayList(fileSystemMovies);
        loadMoviesMetadataProgressBar.setOpacity(1.0);
        for (double i = 0; i <= 100; i++) {
            loadMoviesMetadataProgressBar.setProgress(i / 100);
        }
        loadMoviesMetadataProgressBar.setVisible(false);
        directoryList.setItems(items);
        directoryList.refresh();
    }

    @FXML
    private void renameMovies(ActionEvent event) {
        chapiServices.renameMovies(fileSystemMovies);
    }

    @FXML
    private void getMoviesMetadata(ActionEvent event) {

        chapiServices.getMoviesMetadata(fileSystemMovies, loadMoviesMetadataProgressBar);
        //ObservableList<Movie> items = FXCollections.observableArrayList(fileSystemMovies.get(0).getMoviesList());
        //moviesTable.setItems(items);
        //moviesTable.refresh();
    }

}
