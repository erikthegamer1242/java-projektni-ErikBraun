package braun.erik.prijevoz.repository.util;

import braun.erik.prijevoz.model.exceptions.DatabaseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public interface DatabaseConnector {

    static final String ROOT_PATH = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    static final String DATABASE_FILE = ROOT_PATH + "database.properties";

    public static Connection connectToDatabase() throws DatabaseException {
        try (var reader = new FileReader(DATABASE_FILE)) {

            var properties = new Properties();
            properties.load(reader);

            var url = properties.getProperty("dbURL");
            var user = properties.getProperty("username");
            var pass = properties.getProperty("password");

            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException | IOException e) {
            throw new DatabaseException(e);
        }
    }

    public static void closeConnection(Connection conn) throws DatabaseException {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
