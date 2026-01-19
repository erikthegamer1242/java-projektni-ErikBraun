package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.MainApp;
import braun.erik.prijevoz.builder.AddParameterBuilder;
import braun.erik.prijevoz.builder.TableViewBuilder;
import braun.erik.prijevoz.model.DisplayOption;
import braun.erik.prijevoz.model.exceptions.DatabaseException;
import braun.erik.prijevoz.model.exceptions.FieldArgumentException;
import braun.erik.prijevoz.repository.util.XMLHelper;
import braun.erik.prijevoz.util.DialogUtil;
import jakarta.xml.bind.JAXBException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Random;

/**
 * Abstract class for creating an add view controller
 *
 * @param <T> type of entity
 * @author erik
 * @version 1.0
 */
public abstract class AddViewController<T extends DisplayOption> extends ViewController<T> {

    /**
     * Default constructor.
     */
    AddViewController() {
        // intentionally empty to remove Javadoc warning
    }

    /**
     * Random used of entity IDs
     */
    Random r = new Random();

    /**
     * Upper bound for random
     */
    protected static final Integer BOUND = 1000000;

    /**
     * Adds new data to an entity
     * @throws FieldArgumentException when a parameter is invalid
     */
    protected abstract void addToRepository() throws FieldArgumentException;

    @SuppressWarnings("unchecked")
    @Override
    public void setContentArea(GridPane searchGridPane, TableView<?> tableView) {
        this.searchGridPane = searchGridPane;
        this.tableView = (TableView<T>) tableView;
    }

    @Override
    public final void onActivate() {
        if (!initialized) {
            initOnce();
            initialized = true;
        }
    }

    @Override
    public final void onDeactivate() {
    }

    /**
     * Initializes data, GridPane and TableView on controller activation
     */
    protected void initOnce() {
        repository = getRepository();

        // Clear existing content from previous controller
        searchGridPane.getChildren().clear();
        tableView.getColumns().clear();
        tableView.getItems().clear();

        AddParameterBuilder.build(
                searchGridPane,
                getEntityClass(),
                this::add,
                this::clear
        );

        TableViewBuilder.build(
                tableView,
                repository.get(),
                getEntityClass()
        );
    }

    /**
     * Method used on JavaFX button to add data
     * @param event JavaFX event
     */
    void add(ActionEvent event) {
        try {
            addToRepository();
        } catch (FieldArgumentException | DatabaseException e) {
            if (e.getCause() == null) {
                DialogUtil.showErrorDialogWithDescription("Error adding data", e.getMessage());
            } else {
                DialogUtil.showErrorDialogWithDescription(e.getMessage(), e.getCause().getMessage());
            }
            MainApp.logger.error("Error saving {}: ", getEntityClass(), e);
            return;
        }
        searchGridPane.getChildren().clear();
        tableView.getItems().clear();
        ObservableList<T> items = tableView.getItems();
        items.setAll(repository.get());

        AddParameterBuilder.build(
                searchGridPane,
                getEntityClass(),
                this::add,
                this::clear
        );

        try {
            XMLHelper.writeOneAction(XMLHelper.getCurrentDateAndTime() + " - User selected: " + "Adding: " + getEntityClass().toString(), "src/main/resources/braun/erik/prijevoz/actions/actions.xml");
        } catch (JAXBException e) {
            DialogUtil.showLoadingScreenErrorDialog();
            MainApp.logger.error("Cannot save action", e);
        } catch (IOException e) {
            DialogUtil.showLoadingScreenErrorDialog();
            MainApp.logger.error("Cannot load actions and save action: " + getEntityClass().toString(), e);
        }
    }

    /**
     * Method used on JavaFX button to clear and reset the data in a controller
     * @param event JavaFX event
     */
    void clear(ActionEvent event) {
        searchGridPane.getChildren().clear();
        tableView.getItems().clear();
        AddParameterBuilder.build(
                searchGridPane,
                getEntityClass(),
                this::add,
                this::clear
        );
        TableViewBuilder.build(
                tableView,
                repository.get(),
                getEntityClass()
        );
        try {
            XMLHelper.writeOneAction(XMLHelper.getCurrentDateAndTime() + " - User selected: " + "Clearing sort parameters: " + getEntityClass().toString(), "src/main/resources/braun/erik/prijevoz/actions/actions.xml");
        } catch (JAXBException e) {
            DialogUtil.showLoadingScreenErrorDialog();
            MainApp.logger.error("Cannot save action", e);
        } catch (IOException e) {
            DialogUtil.showLoadingScreenErrorDialog();
            MainApp.logger.error("Cannot load actions and save action: {}", getEntityClass(), e);
        }
    }
}
