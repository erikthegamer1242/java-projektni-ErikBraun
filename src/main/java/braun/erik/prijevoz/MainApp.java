package braun.erik.prijevoz;

import braun.erik.prijevoz.controller.DriverSearchViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MainApp extends Application {

    private static Stage mainStage;
    public static final Logger logger = LoggerFactory.getLogger(MainApp.class);

    @SuppressWarnings({"squid:S2696"})
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("views/main-view.fxml"));
        fxmlLoader.setController(new DriverSearchViewController());
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        mainStage = stage;
        stage.setTitle("Main screen");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getMainStage() {
        return mainStage;
    }
}
