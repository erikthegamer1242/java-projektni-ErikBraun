package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.exceptions.DatabaseException;
import braun.erik.prijevoz.model.subclasses.Driver;

import java.util.List;

/**
 * Typed interface for driver repository
 *
 * @author erik
 * @version 1.0
 */
public interface DriverRepository extends Repository<Driver> {

    @Override
    List<Driver> get() throws DatabaseException;

    @Override
    void set(List<Driver> list) throws DatabaseException;
}
