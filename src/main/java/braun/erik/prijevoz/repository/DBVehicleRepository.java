package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.Vehicle;
import braun.erik.prijevoz.model.exceptions.DatabaseException;
import braun.erik.prijevoz.repository.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Concrete implementation of vehicle repository using H2 as backend
 *
 * @author erik
 * @version 1.0
 *
 * Database scheme:
 * <pre>
 * {@code
 * CREATE TABLE IF NOT EXISTS Vehicle (
 * id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 * name VARCHAR(255) NOT NULL,
 * model VARCHAR(255) NOT NULL,
 * licensePlate VARCHAR(255) NOT NULL,
 * vin VARCHAR(255) NOT NULL UNIQUE,
 * prodYear INT NOT NULL,
 * motorType VARCHAR(50) NOT NULL
 * }
 * </pre>
 * );

 */

public class DBVehicleRepository implements VehicleRepository {

    private static final String SELECT_ALL_QUERY = "SELECT ID, NAME, MODEL, LICENSEPLATE, VIN, PRODYEAR, MOTORTYPE  FROM VEHICLE";
    private static final String SELECT_LAST = SELECT_ALL_QUERY + " ORDER BY id DESC LIMIT 1";
    private static final String SELECT_ONE_BY_ID = SELECT_ALL_QUERY + " WHERE ID = ?";
    private static final String INSERT_ONE_QUERY = "INSERT INTO VEHICLE (name, model, licenseplate, vin, prodyear, motortype) VALUES (?, ?, ?, ?, ?, ?)";

    private Vehicle.MotorType convertStringToMotorType(String string) {
        return Vehicle.MotorType.valueOf(string);
    }

    /**
     * Default constructor
     */
    public DBVehicleRepository() {
        // intentionally empty to remove Javadoc warning
    }

    @Override
    public List<Vehicle> get() throws DatabaseException {
        List<Vehicle> vehicles = new ArrayList<>();
        Connection connection = DatabaseConnector.connectToDatabase();

        try (ResultSet rs = connection.prepareStatement(SELECT_ALL_QUERY).executeQuery()) {
            while (rs.next()) {
                vehicles.add(buildVehicle(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        DatabaseConnector.closeConnection(connection);
        return vehicles;
    }

    private Vehicle buildVehicle(ResultSet rs) throws SQLException {
        return new Vehicle(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("model"),
                rs.getString("licenseplate"),
                rs.getString("vin"),
                rs.getInt("prodyear"),
                convertStringToMotorType(rs.getString("motortype")));
    }

    @Override
    public void set(List<Vehicle> list) throws DatabaseException {
        Connection connection = DatabaseConnector.connectToDatabase();

        if (list.isEmpty()) {
            throw new DatabaseException("Vehicle list is empty");
        }
        Vehicle vehicle = list.getFirst();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ONE_QUERY)) {
            preparedStatement.setString(1, vehicle.getName());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setString(3, vehicle.getLicensePlate());
            preparedStatement.setString(4, vehicle.getVin());
            preparedStatement.setInt(5, vehicle.getProdYear());
            preparedStatement.setString(6, vehicle.getMotorType().getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        DatabaseConnector.closeConnection(connection);
    }

    @Override
    public Vehicle getLastInserted() throws DatabaseException {
        Connection connection = DatabaseConnector.connectToDatabase();

        try (ResultSet rs = connection.prepareStatement(SELECT_LAST).executeQuery()) {
            if (rs.next()) {
                return buildVehicle(rs);
            } else {
                throw new SQLException("No driver found!");
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    /**
     * Get a specific vehicle from the DB based on its ID
     *
     * @param id Integer ID of the vehicle
     * @return Vehicle found vehicle
     * @throws DatabaseException when there is an error in executing thr query
     */
    public Vehicle getById(Integer id) throws DatabaseException {
        Connection connection = DatabaseConnector.connectToDatabase();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return buildVehicle(rs);
            } else {
                throw new SQLException("No selected vehicle found!");
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }
}
