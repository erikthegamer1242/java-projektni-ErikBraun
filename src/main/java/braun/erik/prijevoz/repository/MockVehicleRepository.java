package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class MockVehicleRepository implements VehicleRepository {

    private final List<Vehicle> vehicles = new ArrayList<>();

    public MockVehicleRepository() {
        vehicles.add(new Vehicle("Opel", "Corsa", "ZG-1242-12", "Ar43f23", 2001, Vehicle.MotorType.DIESEL));
    }

    @Override
    public List<Vehicle> get() {
        return vehicles;
    }

    @Override
    public void set(List<Vehicle> list) {
        vehicles.addAll(list);
    }
}
