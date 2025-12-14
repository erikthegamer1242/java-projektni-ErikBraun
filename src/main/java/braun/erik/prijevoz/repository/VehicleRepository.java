package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.Vehicle;

import java.util.List;

public interface VehicleRepository extends Repository<Vehicle> {

    @Override
    public List<Vehicle> get();

    @Override
    public void set(List<Vehicle> list);
}
