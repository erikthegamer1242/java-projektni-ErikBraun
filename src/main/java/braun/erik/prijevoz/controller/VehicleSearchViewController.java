package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.subclasses.Driver;
import braun.erik.prijevoz.repository.MockDriverRepository;
import braun.erik.prijevoz.repository.Repository;

public class VehicleSearchViewController extends SearchViewController<Driver> {

    @Override
    protected Repository<Driver> getRepository() {
        return new MockDriverRepository();
    }

    @Override
    protected Class<Driver> getEntityClass() {
        return Driver.class;
    }
}
