package braun.erik.prijevoz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private static Stage mainStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("views/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        mainStage = stage;
        stage.setTitle("Main screen");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getMainStage() {
        return mainStage;
    }
}
