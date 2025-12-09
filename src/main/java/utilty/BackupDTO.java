package utilty;

import entity.Route;
import entity.Stop;
import entity.Vehicle;
import entity.subclasses.Driver;
import entity.subclasses.User;

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
