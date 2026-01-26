package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.DisplayOption;
import braun.erik.prijevoz.model.exceptions.DatabaseException;
import braun.erik.prijevoz.repository.Repository;
import braun.erik.prijevoz.repository.util.DatabaseConnector;
import braun.erik.prijevoz.util.DialogUtil;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViewBackupController<T extends DisplayOption> extends ViewController<T> {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private static final String SELECT_LAST_BACKUP = "SELECT time FROM last_backup WHERE id=1";
    private static final String UPDATE_BACKUP = """
            MERGE INTO LAST_BACKUP AS target
            USING (SELECT 1 AS id, CURRENT_TIMESTAMP() AS time) AS source
            ON target.id = source.id
            WHEN MATCHED THEN
            UPDATE SET target.TIME = source.time
            WHEN NOT MATCHED THEN
            INSERT (id, time) VALUES (source.id, source.time);""";
    private static final String COPY_DRIVER_TO_BACKUP = """
            CREATE TABLE IF NOT EXISTS _BACKUP AS SELECT * FROM ROUTE WHERE 1=2;
            INSERT INTO _BACKUP SELECT * FROM ROUTE;""" + UPDATE_BACKUP;
    private List<Node> mainPaneChildren;

    @SuppressWarnings("unchecked")
    @Override
    public void setContentArea(GridPane searchGridPane, TableView<?> tableView) {
        this.searchGridPane = searchGridPane;
        this.tableView = (TableView<T>) tableView;
    }

    @Override
    protected Repository<T> getRepository() {
        return null;
    }

    @Override
    protected Class<T> getEntityClass() {
        return null;
    }

    private Button runBackupButton = new Button("_Run backup");

    @Override
    public void onActivate() {
        searchGridPane.getChildren().clear();
        tableView.getColumns().clear();
        tableView.getItems().clear();
        mainPaneChildren = mainPane.getChildren().stream().toList();
        mainPane.getChildren().clear();
        Text newText = new Text();
        newText.setFont(Font.font(Font.getDefault().getName(), 24));
        newText.setText("Last backup time: " + getLastBackupTime().format(dtf));
        mainPane.getChildren().add(newText);
        VBox.setMargin(newText, new Insets(10, 0, 0, 20));

        runBackupButton.setBackground(Background.fill(Paint.valueOf("DARKGREEN")));
        runBackupButton.setPrefSize(120, 50);
        runBackupButton.setStyle("-fx-border-width: 1; -fx-border-radius: 10; -fx-background-radius: 10; -fx-background-color: DARKGREEN; -fx-font-size: 16px");
        runBackupButton.setOnAction(_ -> {
            runBackupButton.setText("Running...");
            runBackupButton.setDisable(true);
            runBackupButton.setStyle("-fx-border-width: 1; -fx-border-radius: 10; -fx-background-radius: 10; -fx-background-color: RED; -fx-font-size: 16px");
            Thread.startVirtualThread(() -> {
                try {
                    createBackup();
                    Thread.sleep(2000);
                } catch (DatabaseException | InterruptedException e) {
                    DialogUtil.showErrorDialog("Error running backup!", e.getMessage());
                    Thread.currentThread().interrupt();
                } finally {
                    Platform.runLater(() -> {
                        runBackupButton.setDisable(false);
                        runBackupButton.setText("_Run backup");
                        runBackupButton.setStyle("-fx-border-width: 1; -fx-border-radius: 10; -fx-background-radius: 10; -fx-background-color: DARKGREEN; -fx-font-size: 16px");
                        newText.setText("Last backup time: " + getLastBackupTime().format(dtf));
                    });

                }
            });

        });

        mainPane.getChildren().add(runBackupButton);
        VBox.setMargin(runBackupButton, new Insets(10, 0, 0, 20));
    }

    @Override
    public void onDeactivate() {
        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(mainPaneChildren);

    }

    private LocalDateTime getLastBackupTime() {
        Connection connection = DatabaseConnector.connectToDatabase();

        Timestamp timestamp = null;

        try (ResultSet rs = connection.prepareStatement(SELECT_LAST_BACKUP).executeQuery()) {
            if (rs.next()) {
                timestamp = rs.getTimestamp(1);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        if (timestamp == null) {
            return LocalDate.EPOCH.atTime(0, 0, 0);
        }
        return timestamp.toLocalDateTime();
    }

    private void createBackup() {
        Connection connection = DatabaseConnector.connectToDatabase();

        try (PreparedStatement ps = connection.prepareStatement(COPY_DRIVER_TO_BACKUP)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }
}
