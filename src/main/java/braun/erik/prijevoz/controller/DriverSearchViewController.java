package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.subclasses.Driver;
import braun.erik.prijevoz.repository.JSONDriverRepository;
import braun.erik.prijevoz.repository.Repository;

/**
 * Typed class of search view controller of type driver
 *
 * @author erik
 * @version 1.0
 */
public class DriverSearchViewController extends SearchViewController<Driver> {

    /**
     * Default constructor.
     */
    public DriverSearchViewController() {
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
}
