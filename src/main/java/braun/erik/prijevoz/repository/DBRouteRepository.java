package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.Route;
import braun.erik.prijevoz.model.Stop;
import braun.erik.prijevoz.model.exceptions.DatabaseException;
import braun.erik.prijevoz.repository.util.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CREATE TABLE IF NOT EXISTS Route (
 * id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 * routeName VARCHAR(255) NOT NULL,
 * vehicle_id INT NOT NULL,
 * driver_id INT NOT NULL,
 * stopCost DECIMAL(10, 2) NOT NULL,
 * FOREIGN KEY (vehicle_id) REFERENCES Vehicle(id),
 * FOREIGN KEY (driver_id) REFERENCES Driver(id)
 * );
 * CREATE TABLE IF NOT EXISTS Route_Stop (
 * route_id INT NOT NULL,
 * stop_id INT NOT NULL,
 * stop_order INT NOT NULL,
 * PRIMARY KEY (route_id, stop_id),
 * FOREIGN KEY (route_id) REFERENCES Route(id),
 * FOREIGN KEY (stop_id) REFERENCES Stop(id)
 * );
 *
 */

public class DBRouteRepository implements RouteRepository {

    private static final String SELECT_ALL_QUERY = "SELECT id, routename, vehicle_id, driver_id, stopcost FROM ROUTE";
    private static final String INSERT_ONE_QUERY = "INSERT INTO route (routename, vehicle_id, driver_id, stopcost) VALUES (?, ?, ?, ?)";
    private static final String ADD_STOP_ROUTE = "INSERT INTO Route_Stop (route_id, stop_id, stop_order) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_STOPS =
            "SELECT s.id AS id, s.name " +
                    "FROM Route_Stop rs " +
                    "JOIN Stop s ON rs.stop_id = s.id " +
                    "WHERE rs.route_id = ? " +
                    "ORDER BY rs.stop_order ";


    DBStopRepository stopRepository = new DBStopRepository();
    DBDriverRepository driverRepository = new DBDriverRepository();
    DBVehicleRepository vehicleRepository = new DBVehicleRepository();

    private void addStopsToJunctionTable(List<Stop> stops, Integer routeId) throws DatabaseException {
        int cnt = 0;
        Connection connection = DatabaseConnector.connectToDatabase();
        try (PreparedStatement ps = connection.prepareStatement(ADD_STOP_ROUTE)) {
            ps.setInt(1, routeId);
            for (var stop : stops) {
                ps.setInt(2, stop.getId());
                ps.setInt(3, cnt++);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    private List<Stop> getStopsFromJunctionTableById(Integer routeId) throws DatabaseException {
        List<Stop> stops = new ArrayList<>();
        Connection connection = DatabaseConnector.connectToDatabase();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STOPS)) {
            preparedStatement.setInt(1, routeId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                stops.add(new Stop(rs.getInt("id"), rs.getString("name")));
            }
            if (stops.isEmpty()) {
                throw new SQLException("No stops found!");
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return stops;
    }

    @Override
    public List<Route> get() throws DatabaseException {
        List<Route> routes = new ArrayList<>();
        Connection connection = DatabaseConnector.connectToDatabase();

        try (ResultSet rs = connection.prepareStatement(SELECT_ALL_QUERY).executeQuery()) {
            while (rs.next()) {
                routes.add(new Route(
                                rs.getInt("id"),
                                rs.getString("routename"),
                                vehicleRepository.getById(rs.getInt("vehicle_id")),
                                driverRepository.getById(rs.getInt("driver_id")),
                                getStopsFromJunctionTableById(rs.getInt("id")),
                                rs.getBigDecimal("stopcost")
                        )
                );
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        DatabaseConnector.closeConnection(connection);
        return routes;
    }

    @Override
    public void set(List<Route> list) throws DatabaseException {
        Connection connection = DatabaseConnector.connectToDatabase();

        if (list.isEmpty()) {
            throw new DatabaseException("Route list is empty");
        }
        Route route = list.getFirst();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ONE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, route.getRouteName());
            preparedStatement.setInt(2, route.getVehicle().getId());
            preparedStatement.setInt(3, route.getDriver().getId());
            preparedStatement.setBigDecimal(4, route.getStopCost());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int routeId = generatedKeys.getInt(1);
                addStopsToJunctionTable(route.getStops(), routeId);
            } else {
                throw new SQLException("Creating route failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        DatabaseConnector.closeConnection(connection);
    }
}
