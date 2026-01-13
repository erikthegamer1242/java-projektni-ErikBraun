package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.controller.util.NodeProcessorUtil;
import braun.erik.prijevoz.model.Vehicle;
import braun.erik.prijevoz.repository.JSONVehicleRepository;
import braun.erik.prijevoz.repository.Repository;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.List;

/**
 * Typed class of add view controller of type vehicle
 *
 * @author erik
 * @version 1.0
 */
public class VehicleAddViewController extends AddViewController<Vehicle> {

    /**
     * Default constructor.
     */
    public VehicleAddViewController() {
        // intentionally empty to remove Javadoc warning
    }

    @Override
    protected Repository<Vehicle> getRepository() {
        return new JSONVehicleRepository();
    }

    @Override
    protected Class<Vehicle> getEntityClass() {
        return Vehicle.class;
    }

    @Override
    protected void addToRepository() throws IllegalArgumentException {
        Vehicle vehicle = new Vehicle();
        ObservableList<Node> gridPaneChildren = searchGridPane.getChildren();

        for (var node : gridPaneChildren) {
            NodeProcessorUtil.processNode(node, vehicle);
        }
        getRepository().set(List.of(vehicle));
    }
}
