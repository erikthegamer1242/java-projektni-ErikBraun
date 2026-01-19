package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.Vehicle;
import braun.erik.prijevoz.repository.DBVehicleRepository;
import braun.erik.prijevoz.repository.Repository;

/**
 * Typed class of search view controller of type vehicle
 *
 * @author erik
 * @version 1.0
 */
public class VehicleSearchViewController extends SearchViewController<Vehicle> {

    /**
     * Default constructor.
     */
    public VehicleSearchViewController() {
        // intentionally empty to remove Javadoc warning
    }

    @Override
    protected Repository<Vehicle> getRepository() {
        return new DBVehicleRepository();
    }

    @Override
    protected Class<Vehicle> getEntityClass() {
        return Vehicle.class;
    }
}
