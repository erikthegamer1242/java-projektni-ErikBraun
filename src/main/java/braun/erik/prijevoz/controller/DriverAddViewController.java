package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.controller.util.NodeProcessorUtil;
import braun.erik.prijevoz.model.exceptions.FieldArgumentException;
import braun.erik.prijevoz.model.subclasses.Driver;
import braun.erik.prijevoz.repository.JSONDriverRepository;
import braun.erik.prijevoz.repository.Repository;
import braun.erik.prijevoz.util.DialogUtil;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.List;

/**
 * Typed class of add view controller of type driver
 *
 * @author erik
 * @version 1.0
 */
public class DriverAddViewController extends AddViewController<Driver> {

    /**
     * Default constructor.
     */
    public DriverAddViewController() {
        // intentionally empty to remove Javadoc warning
    }

    @Override
    protected Repository<Driver> getRepository() {
        return new JSONDriverRepository();
    }

    @Override
    protected Class<Driver> getEntityClass() {
        return Driver.class;
    }

    @Override
    protected void addToRepository() throws FieldArgumentException {
        Driver driver = new Driver();
        ObservableList<Node> gridPaneChildren = searchGridPane.getChildren();

        for (var node : gridPaneChildren) {
            if (node instanceof DatePicker datePicker) {
                LocalDate date = datePicker.getValue();
                if (date == null || date.isAfter(LocalDate.now().minusYears(18))) {
                    DialogUtil.showErrorDialog("Error!", "Driver must be at least 18 years old!");
                    throw new FieldArgumentException("Driver must be at least 18 years old!");
                }
                driver.setDateOfBirth(date);
            } else {
                NodeProcessorUtil.processNode(node, driver);
            }
        }
        getRepository().set(List.of(driver));
    }
}
