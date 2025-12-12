package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.builder.TableViewBuilder;
import braun.erik.prijevoz.builder.utils.NestedPropertyValueFactory;
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

public class MainViewController {

    @FXML
    private TableView<Route> tableView;

    @FXML
    void initialize() {
        //DriverRepository driverRepository = new MockDriverRepository();
        //TableViewBuilder.build(tableView, driverRepository.getDrivers(), Driver.class);
        //tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        RouteRepository routeRepository = new MockRouteRepository();
        TableColumn<Route, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("driver.name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("driver.id"));
        tableView.getColumns().add(idColumn);
        tableView.getItems().addAll(routeRepository.getRoutes());
//
//        TableViewBuilder.build(tableView, routeRepository.getRoutes(), Route.class);
//        System.out.println("Init done");
    }
}
