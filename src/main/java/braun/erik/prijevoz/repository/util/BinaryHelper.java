package braun.erik.prijevoz.repository.util;



import braun.erik.prijevoz.model.Route;
import braun.erik.prijevoz.model.Stop;
import braun.erik.prijevoz.model.Vehicle;
import braun.erik.prijevoz.model.subclasses.Driver;
import braun.erik.prijevoz.model.subclasses.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Static interface implementing methods for reading and writing binary files with serialization and deserialization included
 *
 * @author erik
 * @version 1.0
 */

public interface BinaryHelper {

    /**
     * Writes all data from the application to a binary file
     * @param users list of users to write
     * @param drivers list of drivers to write
     * @param vehicles list of vehicles to write
     * @param stops list of stops to write
     * @param routes list of routes to write
     * @param pathName the path to write to
     * @throws IOException when there is an issue with writing
     */
    public static void writeAllDataToFile(List<User> users, List<Driver> drivers, List<Vehicle> vehicles, List<Stop> stops, List<Route> routes, String pathName) throws IOException {
        Files.createDirectories(Paths.get(pathName).getParent());
        internalWriter(new BackupDTO(users, drivers, vehicles, stops, routes), pathName);
    }

    private static void internalWriter(BackupDTO backupDTO, String pathName) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(pathName))) {
            out.writeObject(backupDTO);
        }
    }

    /**
     * Used to read data from a binary file
     * @param pathName the path to read from
     * @return a {@link BackupDTO} record storing all the data
     * @throws IOException when there is an issue reading the file
     * @throws ClassNotFoundException when there is an issue in casting, usually from a corrupted binary file
     */
    public static BackupDTO readAllDataFromFile(String pathName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(pathName))) {
            return (BackupDTO) in.readObject();
        }
    }
}
