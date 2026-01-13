package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.DisplayOption;
import braun.erik.prijevoz.repository.Repository;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

/**
 * Main abstract class used for every controller, stores global components
 *
 * @param <T> type of data
 */

public abstract class ViewController<T extends DisplayOption> implements ActivateController {

    /**
     * Default constructor.
     */
    ViewController() {
        // intentionally empty to remove Javadoc warning
    }

    /**
     * Table view used to show data
     */
    protected TableView<T> tableView;

    /**
     * Pane used to store all buttons, inputs, etc.
     */
    protected GridPane searchGridPane;

    /**
     * Abstract method to set the content for each controller
     * @param searchGridPane Pane used to store all buttons, inputs, etc.
     * @param tableView Table view used to show data
     */
    public abstract void setContentArea(GridPane searchGridPane, TableView<?> tableView);

    /**
     * Method to get repository
     *
     * @return Repository for the entity type
     */
    protected abstract Repository<T> getRepository();

    /**
     * Stores a class object of the entity, used for reflection
     *
     * @return class type
     */
    protected abstract Class<T> getEntityClass();

    /**
     * Date format to translate LocalDate to
     */
    static final String DATE_FORMAT = "dd.MM.yyyy";

    /**
     * Repository containing entity data
     */
    protected Repository<T> repository;

    /**
     * Allows to only call onActivate() once
     */
    protected boolean initialized = false;
}
