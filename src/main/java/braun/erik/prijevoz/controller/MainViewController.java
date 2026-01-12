package braun.erik.prijevoz.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

/**
 * Main controller for the application.
 */
public class MainViewController {

    @FXML
    private GridPane searchGridPane;

    @FXML
    private TableView<?> tableView;

    @FXML
    private MenuController menuController;

    @FXML
    private void initialize() {
        menuController.setContentArea(searchGridPane, tableView);
        menuController.switchController(new RouteAddViewController());
    }
}

