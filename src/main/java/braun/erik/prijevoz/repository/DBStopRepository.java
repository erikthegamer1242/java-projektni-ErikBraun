package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.Stop;
import braun.erik.prijevoz.model.exceptions.DatabaseException;
import braun.erik.prijevoz.repository.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CREATE TABLE IF NOT EXISTS Stop (
 * id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 * name VARCHAR(255) NOT NULL
 * );
 */

public class DBStopRepository implements StopRepository {

    private static final String SELECT_ALL_QUERY = "SELECT id, name FROM STOP";
    private static final String SELECT_LAST = SELECT_ALL_QUERY + " ORDER BY id DESC LIMIT 1";
    private static final String SELECT_ONE_BY_ID = SELECT_ALL_QUERY + " WHERE id = ?";
    private static final String INSERT_ONE_QUERY = "INSERT INTO STOP (name) VALUES (?)";

    @Override
    public List<Stop> get() throws DatabaseException {
        List<Stop> stops = new ArrayList<>();
        Connection connection = DatabaseConnector.connectToDatabase();

        try (ResultSet rs = connection.prepareStatement(SELECT_ALL_QUERY).executeQuery()) {
            while (rs.next()) {
                stops.add(new Stop(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        DatabaseConnector.closeConnection(connection);
        return stops;
    }

    @Override
    public void set(List<Stop> list) throws DatabaseException {
        Connection connection = DatabaseConnector.connectToDatabase();

        if (list.isEmpty()) {
            throw new DatabaseException("User list is empty");
        }
        Stop stop = list.getFirst();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ONE_QUERY)) {
            preparedStatement.setString(1, stop.getLocation());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        DatabaseConnector.closeConnection(connection);
    }

    @Override
    public Stop getLastInserted() throws DatabaseException {
        Connection connection = DatabaseConnector.connectToDatabase();

        try (ResultSet rs = connection.prepareStatement(SELECT_LAST).executeQuery()) {
            if (rs.next()) {
                return new Stop(rs.getInt("id"), rs.getString("name"));

            } else {
                throw new SQLException("No stop found!");
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public Stop getById(Integer id) throws DatabaseException {
        Connection connection = DatabaseConnector.connectToDatabase();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Stop(rs.getInt("id"), rs.getString("name"));
            } else {
                throw new SQLException("No selected stop found!");
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }
}
