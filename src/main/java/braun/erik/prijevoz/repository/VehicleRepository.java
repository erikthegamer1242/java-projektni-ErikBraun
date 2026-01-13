package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.Vehicle;

import java.util.List;

/**
 * Typed interface for vehicle repository
 *
 * @author erik
 * @version 1.0
 */
public interface VehicleRepository extends Repository<Vehicle> {

    @Override
    public List<Vehicle> get();

    @Override
    public void set(List<Vehicle> list);
}
