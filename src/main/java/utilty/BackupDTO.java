package utilty;

import entity.Route;
import entity.Stop;
import entity.Vehicle;
import entity.subclasses.Driver;
import entity.subclasses.User;

import java.io.Serializable;
import java.util.List;

public record BackupDTO(
        List<User> users,
        List<Driver> drivers,
        List<Vehicle> vehicles,
        List<Stop> stops,
        List<Route> routes
) implements Serializable {
}
