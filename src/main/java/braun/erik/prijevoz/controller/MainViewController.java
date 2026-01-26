package braun.erik.prijevoz.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Main controller for the application.
 * @author erik
 * @version 1.0
 */
public class MainViewController {

    /**
     * Default constructor.
     */
    public MainViewController() {
        // intentionally empty to remove Javadoc warning
    }

    @FXML
    private GridPane searchGridPane;

    @FXML
    private TableView<?> tableView;

    @FXML
    private VBox mainPane;

    @FXML
    private MenuController menuController;

    @FXML
    private void initialize() {
        menuController.setContentArea(searchGridPane, tableView, mainPane);
        menuController.switchController(new RouteAddViewController());
    }
}

