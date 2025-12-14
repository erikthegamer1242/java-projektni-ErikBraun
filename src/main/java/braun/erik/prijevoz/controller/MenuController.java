package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.MainApp;
import braun.erik.prijevoz.repository.util.XMLHelper;
import braun.erik.prijevoz.util.DialogUtil;
import jakarta.xml.bind.JAXBException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuController {
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
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("views/main-view.fxml"));
            fxmlLoader.setController(chooseController(location));
            Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
            Stage stage = MainApp.getMainStage();
            stage.setTitle("Main screen");
            stage.setScene(scene);
            stage.show();
            XMLHelper.writeOneAction(XMLHelper.getCurrentDateAndTime() + " - User selected: " + String.join("", location), "src/main/resources/braun/erik/prijevoz/actions/actions.xml");
        } catch (JAXBException e) {
            DialogUtil.showLoadingScreenErrorDialog();
            MainApp.logger.error("Cannot save action", e);
        } catch (IOException e) {
            DialogUtil.showLoadingScreenErrorDialog();
            MainApp.logger.error("Cannot load actions and save action: " + location, e);
        }

    }

    private static Object chooseController(List<String> location) {
        if ("search".equals(location.getLast())) {
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
        }
        DialogUtil.showErrorDialog("Feature not yet implemented!", "You currently cannot add anything, we will revert you back to the first screen!");
        return new DriverSearchViewController();
    }
}
