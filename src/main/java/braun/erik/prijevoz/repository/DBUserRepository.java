package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.exceptions.DatabaseException;
import braun.erik.prijevoz.model.subclasses.User;
import braun.erik.prijevoz.repository.util.DatabaseConnector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * CREATE TABLE IF NOT EXISTS AppUser (
 * id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 * oib VARCHAR(11) NOT NULL UNIQUE,
 * surname VARCHAR(255) NOT NULL,
 * name VARCHAR(255) NOT NULL,
 * email VARCHAR(255),
 * phoneNumber VARCHAR(20),
 * dateOfBirth DATE,
 * subscriberID VARCHAR(50) NOT NULL
 * );
 */

public class DBUserRepository implements UserRepository {

    private static final String SELECT_ALL_QUERY = "SELECT id, oib, surname, name, email, phonenumber, dateofbirth, subscriberid  FROM APPUSER";
    private static final String SELECT_ONE_BY_ID = SELECT_ALL_QUERY + " WHERE id = ?";
    private static final String INSERT_ONE_QUERY = "INSERT INTO APPUSER (oib, surname, name, email, phonenumber, dateofbirth, subscriberid) VALUES (?, ?, ?, ?, ?, ?, ?)";

    @Override
    public List<User> get() throws DatabaseException {
        List<User> users = new ArrayList<>();
        Connection connection = DatabaseConnector.connectToDatabase();

        try (ResultSet rs = connection.prepareStatement(SELECT_ALL_QUERY).executeQuery()) {
            while (rs.next()) {
                users.add(new User.UserBuilder(
                        rs.getInt("id"),
                        rs.getString("oib"),
                        rs.getString("surname"),
                        rs.getString("name"))
                        .email(rs.getString("email"))
                        .phoneNumber(rs.getString("phonenumber"))
                        .dateOfBirth(rs.getDate(DOB_COLUMN) != null ? rs.getDate(DOB_COLUMN).toLocalDate() : LocalDate.EPOCH)
                        .subscriberID(rs.getString(SID_COLUMN) != null ? UUID.fromString(rs.getString(SID_COLUMN)) : UUID.randomUUID())
                        .build()
                );
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        DatabaseConnector.closeConnection(connection);
        return users;
    }

    @Override
    public void set(List<User> list) throws DatabaseException {
        Connection connection = DatabaseConnector.connectToDatabase();

        if (list.isEmpty()) {
            throw new DatabaseException("User list is empty");
        }
        User user = list.getFirst();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ONE_QUERY)) {
            preparedStatement.setString(1, user.getOib());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setDate(6, Date.valueOf(user.getDateOfBirth()));
            preparedStatement.setString(7, user.getSubscriberID() != null ? user.getSubscriberID().toString() : UUID.randomUUID().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        DatabaseConnector.closeConnection(connection);
    }

    public User getById(Integer id) throws DatabaseException {
        Connection connection = DatabaseConnector.connectToDatabase();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new User.UserBuilder(
                        rs.getInt("id"),
                        rs.getString("oib"),
                        rs.getString("surname"),
                        rs.getString("name"))
                        .email(rs.getString("email"))
                        .phoneNumber(rs.getString("phonenumber"))
                        .dateOfBirth(rs.getDate(DOB_COLUMN) != null ? rs.getDate(DOB_COLUMN).toLocalDate() : LocalDate.EPOCH)
                        .subscriberID(rs.getString(SID_COLUMN) != null ? UUID.fromString(rs.getString(SID_COLUMN)) : UUID.randomUUID())
                        .build();
            } else {
                throw new SQLException("No selected user found!");
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }
}
