package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.builder.TableViewBuilder;
import braun.erik.prijevoz.model.Route;
import braun.erik.prijevoz.model.subclasses.Driver;
import braun.erik.prijevoz.repository.DriverRepository;
import braun.erik.prijevoz.repository.MockDriverRepository;
import braun.erik.prijevoz.repository.MockRouteRepository;
import braun.erik.prijevoz.repository.RouteRepository;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

public class MainViewController {

    @FXML
    private TableView<Driver> tableView;

    @FXML
    void initialize() {
        DriverRepository driverRepository = new MockDriverRepository();
        TableViewBuilder.build(tableView, driverRepository.getDrivers(), Driver.class);
    }
}
