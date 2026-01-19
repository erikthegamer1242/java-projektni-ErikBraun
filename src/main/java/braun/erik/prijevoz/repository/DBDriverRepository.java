package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.exceptions.DatabaseException;
import braun.erik.prijevoz.model.subclasses.Driver;
import braun.erik.prijevoz.repository.util.DatabaseConnector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * CREATE TABLE IF NOT EXISTS Driver (
 * id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 * oib VARCHAR(11) NOT NULL UNIQUE,
 * surname VARCHAR(255) NOT NULL,
 * name VARCHAR(255) NOT NULL,
 * email VARCHAR(255),
 * phoneNumber VARCHAR(20),
 * dateOfBirth DATE,
 * licenseNumber VARCHAR(50) NOT NULL,
 * salary DECIMAL(10, 2) NOT NULL,
 * workingHours DECIMAL(5, 2) NOT NULL
 * );
 */

public class DBDriverRepository implements DriverRepository {

    private static final String SELECT_ALL_QUERY = "SELECT id, oib, surname, name, email, phonenumber, dateofbirth, licensenumber, salary, workinghours FROM DRIVER";
    private static final String INSERT_ONE_QUERY = "INSERT INTO driver (oib, surname, name, email, phonenumber, dateofbirth, licensenumber, salary, workinghours) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public List<Driver> get() throws DatabaseException {
        List<Driver> drivers = new ArrayList<>();
        Connection connection = DatabaseConnector.connectToDatabase();

        try (ResultSet rs = connection.prepareStatement(SELECT_ALL_QUERY).executeQuery()) {
            while (rs.next()) {
                drivers.add(new Driver.DriverBuilder(
                        rs.getInt("id"),
                        rs.getString("oib"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("licensenumber"),
                        rs.getBigDecimal("salary"),
                        rs.getBigDecimal("workinghours"))
                        .email(rs.getString("email"))
                        .phoneNumber(rs.getString("phonenumber"))
                        .dateOfBirth(rs.getDate("dateofbirth") != null ? rs.getDate("dateofbirth").toLocalDate() : LocalDate.EPOCH)
                        .build()
                );
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        DatabaseConnector.closeConnection(connection);
        return drivers;
    }

    @Override
    public void set(List<Driver> list) throws DatabaseException {
        Connection connection = DatabaseConnector.connectToDatabase();

        if (list.isEmpty()) {
            throw new DatabaseException("Driver list is empty");
        }
        Driver driver = list.getFirst();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ONE_QUERY)) {
            preparedStatement.setString(1, driver.getOib());
            preparedStatement.setString(2, driver.getSurname());
            preparedStatement.setString(3, driver.getName());
            preparedStatement.setString(4, driver.getEmail());
            preparedStatement.setString(5, driver.getPhoneNumber());
            preparedStatement.setDate(6, Date.valueOf(driver.getDateOfBirth()));
            preparedStatement.setString(7, driver.getLicenseNumber());
            preparedStatement.setBigDecimal(8, driver.getSalary());
            preparedStatement.setBigDecimal(9, driver.getWorkingHours());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        DatabaseConnector.closeConnection(connection);
    }
}
