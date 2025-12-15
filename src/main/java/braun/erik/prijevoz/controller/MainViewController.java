package braun.erik.prijevoz.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

/**
 * Main controller for the application. Wires together the menu controller
 * and the content area (searchGridPane, tableView).
 */
public class MainViewController {

    @FXML
    private GridPane searchGridPane;

    @FXML
    private TableView<?> tableView;

    // Injected by FXMLLoader via naming convention: fx:id="menu" -> menuController
    @FXML
    private MenuController menuController;

    @FXML
    private void initialize() {
        // Pass the content area references to the MenuController
        menuController.setContentArea(searchGridPane, tableView);
        // Initialize with the default view
        menuController.switchController(new DriverSearchViewController());
    }
}

