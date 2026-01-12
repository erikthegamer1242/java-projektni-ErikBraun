package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.Vehicle;
import braun.erik.prijevoz.repository.JSONVehicleRepository;
import braun.erik.prijevoz.repository.Repository;

public class VehicleAddViewController extends AddViewController<Vehicle> {

    @Override
    protected Repository<Vehicle> getRepository() {
        return new JSONVehicleRepository();
    }

    @Override
    protected Class<Vehicle> getEntityClass() {
        return Vehicle.class;
    }

    @Override
    protected void addToRepository() {
        System.out.println("VehicleAddViewController.addToRepository");
    }
}
