package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.MainApp;
import braun.erik.prijevoz.builder.AddParameterBuilder;
import braun.erik.prijevoz.builder.TableViewBuilder;
import braun.erik.prijevoz.model.DisplayOption;
import braun.erik.prijevoz.repository.Repository;
import braun.erik.prijevoz.repository.util.XMLHelper;
import braun.erik.prijevoz.util.DialogUtil;
import jakarta.xml.bind.JAXBException;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public abstract class AddViewController<T extends DisplayOption> extends ViewController<T> {
    static final String DATE_FORMAT = "dd.MM.yyyy";

    protected Repository<T> repository;
    private boolean initialized = false;

    protected abstract Repository<T> getRepository();

    protected abstract Class<T> getEntityClass();

    protected abstract void addToRepository();

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

    void add(ActionEvent event) {
        addToRepository();
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
            XMLHelper.writeOneAction(XMLHelper.getCurrentDateAndTime() + " - User selected: " + "Adding: " + getEntityClass().toString(), "src/main/resources/braun/erik/prijevoz/actions/actions.xml");
        } catch (JAXBException e) {
            DialogUtil.showLoadingScreenErrorDialog();
            MainApp.logger.error("Cannot save action", e);
        } catch (IOException e) {
            DialogUtil.showLoadingScreenErrorDialog();
            MainApp.logger.error("Cannot load actions and save action: " + getEntityClass().toString(), e);
        }
    }

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
            MainApp.logger.error("Cannot load actions and save action: " + getEntityClass().toString(), e);
        }
    }
}
