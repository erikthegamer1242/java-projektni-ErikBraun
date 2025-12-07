package utilty;

import entity.Route;
import entity.Stop;
import entity.Vehicle;
import entity.subclasses.Driver;
import entity.subclasses.User;

import java.io.*;
import java.util.List;

/**
 * Static interface implementing methods for reading and writing binary files with serialization and deserialization included
 *
 * @author erik
 * @version 1.0
 */

public interface BinaryHelper {
    public static void writeAllDataToFile(List<User> users, List<Driver> drivers, List<Vehicle> vehicles, List<Stop> stops, List<Route> routes, String pathName) throws IOException {
        internalWriter(new BackupDTO(users, drivers, vehicles, stops, routes), pathName);
    }
    private static void internalWriter(BackupDTO backupDTO, String pathName) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(pathName))) {
            out.writeObject(backupDTO);
        }
    }
    public static BackupDTO readAllDataFromFile(String pathName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(pathName))) {
            return (BackupDTO) in.readObject();
        }
    }
}
