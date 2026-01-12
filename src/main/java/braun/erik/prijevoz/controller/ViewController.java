package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.DisplayOption;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

public abstract class ViewController<T extends DisplayOption> implements ActivateController {
    protected TableView<T> tableView;

    protected GridPane searchGridPane;

    public abstract void setContentArea(GridPane searchGridPane, TableView<?> tableView);
}
