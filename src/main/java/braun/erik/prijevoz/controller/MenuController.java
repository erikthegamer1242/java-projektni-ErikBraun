package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.MainApp;
import braun.erik.prijevoz.repository.util.XMLHelper;
import braun.erik.prijevoz.util.DialogUtil;
import jakarta.xml.bind.JAXBException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuController {

    private ViewController<?> activeController;
    private GridPane searchGridPane;
    private TableView<?> tableView;

    public void setContentArea(GridPane searchGridPane, TableView<?> tableView) {
        this.searchGridPane = searchGridPane;
        this.tableView = tableView;
    }

    public void switchController(ViewController<?> newController) {
        if (activeController != null) {
            activeController.onDeactivate();
        }

        activeController = newController;
        activeController.setContentArea(searchGridPane, tableView);
        activeController.onActivate();
    }

    @FXML
    public void showScreen(ActionEvent event) {
        Object clickedButton = event.getSource();
        List<String> location = new ArrayList<>();
        if (clickedButton instanceof MenuItem menuItem) {
            String buttonId = menuItem.getId();
            if (buttonId != null && !buttonId.isEmpty()) {
                location.addAll(List.of(buttonId.splitWithDelimiters("_", 0)));
            }
        }

        try {
            if ("search".equals(location.getLast())) {
                switchController(chooseSearchController(location));
            } else if ("add".equals(location.getLast())) {
                switchController(chooseAddController(location));
            }
            XMLHelper.writeOneAction(XMLHelper.getCurrentDateAndTime() + " - User selected: " + String.join("", location), "src/main/resources/braun/erik/prijevoz/actions/actions.xml");
        } catch (JAXBException e) {
            DialogUtil.showLoadingScreenErrorDialog();
            MainApp.logger.error("Cannot save action", e);
        } catch (IOException e) {
            DialogUtil.showLoadingScreenErrorDialog();
            MainApp.logger.error("Cannot load actions and save action: " + location, e);
        }

    }

    private static SearchViewController<?> chooseSearchController(List<String> location) {

        if ("driver".equals(location.getFirst())) {
            return new DriverSearchViewController();
        }
        if ("route".equals(location.getFirst())) {
            return new RouteSearchViewController();
        }
        if ("stop".equals(location.getFirst())) {
            return new StopSearchViewController();
        }
        if ("user".equals(location.getFirst())) {
            return new UserSearchViewController();
        }
        if ("vehicle".equals(location.getFirst())) {
            return new VehicleSearchViewController();
        }
        DialogUtil.showErrorDialog("Feature not yet implemented!", "We currently don't support that, you will be reverted back to the first screen!");
        return new DriverSearchViewController();
    }

    private static AddViewController<?> chooseAddController(List<String> location) {
        if ("driver".equals(location.getFirst())) {
            return new DriverAddViewController();
        }
        if ("route".equals(location.getFirst())) {
            return new RouteAddViewController();
        }
        if ("stop".equals(location.getFirst())) {
            return new StopAddViewController();
        }
        if ("user".equals(location.getFirst())) {
            return new UserAddViewController();
        }
        if ("vehicle".equals(location.getFirst())) {
            return new VehicleAddViewController();
        }

        DialogUtil.showErrorDialog("Feature not yet implemented!", "We currently don't support that, you will be reverted back to the first screen!");
        return new DriverAddViewController();
    }
}
