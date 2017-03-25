import com.lgf.chapi.services.ChapiServices;
import com.lgf.chapi.services.FileSystemMovieService;
import com.lgf.chapi.respositories.MovieRepositoryImplFeign;
import com.lgf.chapi.services.RemoteMovieService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

/*
        RemoteMovieService remoteMovieService = new RemoteMovieService(new MovieRepositoryImplFeign());
        FileSystemMovieService fsMovieService = new FileSystemMovieService();

        ChapiServices chapiServices = new ChapiServices(fsMovieService , remoteMovieService);
        chapiServices.normalizeMovies();
        */

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Chapi");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
