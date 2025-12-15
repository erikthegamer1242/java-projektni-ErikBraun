package braun.erik.prijevoz;

import braun.erik.prijevoz.controller.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MainApp extends Application {

    public static final Logger logger = LoggerFactory.getLogger(MainApp.class);

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("views/main-view.fxml"));
        MainViewController mainController = new MainViewController();
        fxmlLoader.setController(mainController);
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("Main screen");
        stage.setScene(scene);
        stage.show();
    }
}
