package braun.erik.prijevoz;

import javafx.application.Application;

/**
 * Main class that starts the application
 *
 * @author erik
 * @version 1.0
 */
public class Launcher {

    /**
     * Default constructor.
     */
    public Launcher() {
        // intentionally empty to remove Javadoc warning
    }

    /**
     * Path to the logback configuration XML
     */
    public static final String PATH = "src/main/resources/braun/erik/prijevoz/logback.xml";

    /**
     * App entry point
     * @param args console arguments
     */
    public static void main(String[] args) {
        System.setProperty("logback.configurationFile", PATH);
        Application.launch(MainApp.class, args);
    }
}
