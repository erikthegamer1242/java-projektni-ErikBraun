package braun.erik.prijevoz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Class that loads FXML, creates a logger and shows the stage
 *
 * @author erik
 * @version 1.0
 */
public class MainApp extends Application {

    /**
     * Default constructor.
     */
    public MainApp() {
        // intentionally empty to remove Javadoc warning
    }

    /**
     * Static logger class used in the whole application
     */
    public static final Logger logger = LoggerFactory.getLogger(MainApp.class);

    /**
     * Start application method
     * @param stage initial stage
     * @throws IOException when the app cannot load FXML
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("views/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("Main screen");
        stage.setScene(scene);
        stage.show();
    }
}
