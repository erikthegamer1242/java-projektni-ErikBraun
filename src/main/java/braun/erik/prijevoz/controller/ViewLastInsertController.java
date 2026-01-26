package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.builder.TableViewBuilder;
import braun.erik.prijevoz.model.DisplayOption;
import braun.erik.prijevoz.model.exceptions.DatabaseException;
import braun.erik.prijevoz.repository.Repository;
import braun.erik.prijevoz.repository.RepositoryLookup;
import braun.erik.prijevoz.util.DialogUtil;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

public class ViewLastInsertController<T extends DisplayOption> extends ViewController<T> {

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

    private void buildASingleTable(TableView<T> tableView, T data, Class<T> type) {
        TableViewBuilder.build(
                tableView,
                List.of(data),
                type
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onActivate() {
        searchGridPane.getChildren().clear();
        tableView.getColumns().clear();
        tableView.getItems().clear();
        Class<T> whichData = (Class<T>) mainPane.getUserData();
        if (whichData == null) {
            DialogUtil.showErrorDialog("No data added yet!", "For this view to be functional you have to add at least one entity");
            return;
        }
        Text newText = new Text();
        newText.setText("Last inserted entity: " + whichData.getSimpleName());
        newText.setFont(Font.font(Font.getDefault().getName(), 24));
        GridPane.setColumnSpan(newText, 2);
        GridPane.setMargin(newText, new Insets(10, 0, 0, 20));
        searchGridPane.add(newText, 0, 0);
        GridPane.setValignment(newText, VPos.BOTTOM);
        Thread.startVirtualThread(() -> {
            T data = null;
            try {
                data = (T) RepositoryLookup.getRepository(whichData).getLastInserted();
            } catch (DatabaseException e) {
                DialogUtil.showErrorDialog("Database error!", e.getMessage());
            }
            T finalData = data;
            Platform.runLater(() -> {
                if (finalData == null) {
                    DialogUtil.showErrorDialog("Data error!", "Error getting last inserted entity!");
                }
                buildASingleTable(tableView, finalData, whichData);
            });
        });

    }

    @Override
    public void onDeactivate() {
        // Not used
    }
}
