package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.Vehicle;
import braun.erik.prijevoz.model.exceptions.DatabaseException;

import java.util.List;

/**
 * Typed interface for vehicle repository
 *
 * @author erik
 * @version 1.0
 */
public interface VehicleRepository extends Repository<Vehicle> {

    @Override
    public List<Vehicle> get() throws DatabaseException;

    @Override
    public void set(List<Vehicle> list) throws DatabaseException;
}
