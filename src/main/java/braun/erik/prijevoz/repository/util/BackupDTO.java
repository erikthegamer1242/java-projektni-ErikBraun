package braun.erik.prijevoz.repository.util;


import braun.erik.prijevoz.model.Route;
import braun.erik.prijevoz.model.Stop;
import braun.erik.prijevoz.model.Vehicle;
import braun.erik.prijevoz.model.subclasses.Driver;
import braun.erik.prijevoz.model.subclasses.User;

import java.io.Serializable;
import java.util.List;

/**
 * Helper record used to store information for binary file serialization and deserialization
 * @param users list of users
 * @param drivers list of drivers
 * @param vehicles list of vehicles
 * @param stops list of stops
 * @param routes list of routes
 * @author erik
 * @version 1.0
 */

public record BackupDTO(
        List<User> users,
        List<Driver> drivers,
        List<Vehicle> vehicles,
        List<Stop> stops,
        List<Route> routes
) implements Serializable {
}
