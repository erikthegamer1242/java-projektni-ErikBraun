package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.MainApp;
import braun.erik.prijevoz.builder.SearchParameterBuilder;
import braun.erik.prijevoz.builder.TableViewBuilder;
import braun.erik.prijevoz.model.DisplayOption;
import braun.erik.prijevoz.repository.util.XMLHelper;
import braun.erik.prijevoz.util.DialogUtil;
import jakarta.xml.bind.JAXBException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Abstract class for creating a search view controller
 *
 * @param <T> type of entity
 * @author erik
 * @version 1.0
 */
public abstract class SearchViewController<T extends DisplayOption> extends ViewController<T> {

    /**
     * Default constructor.
     */
    SearchViewController() {
        // intentionally empty to remove Javadoc warning
    }

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

        SearchParameterBuilder.build(
                searchGridPane,
                getEntityClass(),
                this::runSearch,
                this::clear
        );

        TableViewBuilder.build(
                tableView,
                repository.get(),
                getEntityClass()
        );


    }

    void runSearch(ActionEvent event) {
        if (repository == null || repository.get().isEmpty()) {
            return;
        }
        ObservableList<Node> gridPaneChildren = searchGridPane.getChildren();
        List<Predicate<T>> predicates = new ArrayList<>();
        for (var node : gridPaneChildren) {
            if (node instanceof TextField textField && !textField.getText().trim().isEmpty()) {
                predicates.add(createPredicate(textField, getEntityClass()));
            }
        }

        tableView.getItems().clear();
        tableView.getItems().addAll(repository.get().stream().filter(predicates.stream().reduce(i -> true, Predicate::and)).toList());
        try {
            XMLHelper.writeOneAction(XMLHelper.getCurrentDateAndTime() + " - User selected: " + "Sorting: " + getEntityClass().toString(), "src/main/resources/braun/erik/prijevoz/actions/actions.xml");
        } catch (JAXBException e) {
            DialogUtil.showLoadingScreenErrorDialog();
            MainApp.logger.error("Cannot save action", e);
        } catch (IOException e) {
            DialogUtil.showLoadingScreenErrorDialog();
            MainApp.logger.error("Cannot load actions and save action: " + getEntityClass().toString(), e);
        }
    }

    void clear(ActionEvent event) {
        tableView.getItems().clear();
        ObservableList<Node> gridPaneChildren = searchGridPane.getChildren();
        for (var node : gridPaneChildren) {
            if (node instanceof TextField textField) {
                textField.setText("");
            }
        }
        tableView.getItems().addAll(repository.get());
        try {
            XMLHelper.writeOneAction(XMLHelper.getCurrentDateAndTime() + " - User selected: " + "Clearing sort parameters: " + getEntityClass().toString(), "src/main/resources/braun/erik/prijevoz/actions/actions.xml");
        } catch (JAXBException e) {
            DialogUtil.showLoadingScreenErrorDialog();
            MainApp.logger.error("Cannot save action", e);
        } catch (IOException e) {
            DialogUtil.showLoadingScreenErrorDialog();
            MainApp.logger.error("Cannot load actions and save action: " + getEntityClass().toString(), e);
        }
    }

    private Predicate<T> createPredicate(TextField textField, Class<T> clazz) {
        String searchText = textField.getText();
        String fieldName = textField.getId();
        final String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        return classInstance -> {
            try {
                var method = clazz.getMethod(getterName);
                Object value = method.invoke(classInstance);
                if (value instanceof LocalDate localDate) {
                    value = localDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
                }
                if (value == null) return false;
                return value.toString().toLowerCase().contains(searchText.toLowerCase());
            } catch (Exception _) {
                return true;
            }
        };
    }
}
