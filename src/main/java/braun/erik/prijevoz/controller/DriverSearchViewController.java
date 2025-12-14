package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.subclasses.Driver;
import braun.erik.prijevoz.repository.JSONDriverRepository;
import braun.erik.prijevoz.repository.Repository;

public class DriverSearchViewController extends SearchViewController<Driver> {

    @Override
    protected Repository<Driver> getRepository() {
        return new JSONDriverRepository();
    }

    @Override
    protected Class<Driver> getEntityClass() {
        return Driver.class;
    }
}
