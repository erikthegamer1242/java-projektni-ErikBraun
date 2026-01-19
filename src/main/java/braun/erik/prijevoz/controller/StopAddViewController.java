package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.Stop;
import braun.erik.prijevoz.model.exceptions.FieldArgumentException;
import braun.erik.prijevoz.repository.DBStopRepository;
import braun.erik.prijevoz.repository.Repository;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.util.List;

/**
 * Typed class of add view controller of type stop
 *
 * @author erik
 * @version 1.0
 */
public class StopAddViewController extends AddViewController<Stop> {

    /**
     * Default constructor.
     */
    public StopAddViewController() {
        // intentionally empty to remove Javadoc warning
    }

    @Override
    protected Repository<Stop> getRepository() {
        return new DBStopRepository();
    }

    @Override
    protected Class<Stop> getEntityClass() {
        return Stop.class;
    }

    @Override
    protected void addToRepository() throws FieldArgumentException {
        Integer id = r.nextInt(BOUND);
        ObservableList<Node> gridPaneChildren = searchGridPane.getChildren();
        if (gridPaneChildren.size() != 3) {
            throw new FieldArgumentException("Location can't be empty");
        } else if (gridPaneChildren.get(1) instanceof TextField textField) {
            if (textField.getText().isEmpty()) {
                throw new FieldArgumentException("Location can't be empty");
            } else {
                getRepository().set(List.of(new Stop(id, textField.getText())));
            }
        }
    }
}
