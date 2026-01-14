package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.controller.util.NodeProcessorUtil;
import braun.erik.prijevoz.model.exceptions.FieldArgumentException;
import braun.erik.prijevoz.model.subclasses.User;
import braun.erik.prijevoz.repository.JSONUserRepository;
import braun.erik.prijevoz.repository.Repository;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.List;

/**
 * Typed class of add view controller of type user
 *
 * @author erik
 * @version 1.0
 */
public class UserAddViewController extends AddViewController<User> {

    /**
     * Default constructor.
     */
    public UserAddViewController() {
        // intentionally empty to remove Javadoc warning
    }

    @Override
    public Repository<User> getRepository() {
        return new JSONUserRepository();
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected void addToRepository() throws FieldArgumentException {
        User user = new User();
        ObservableList<Node> gridPaneChildren = searchGridPane.getChildren();

        for (var node : gridPaneChildren) {
            if (node instanceof DatePicker datePicker) {
                LocalDate date = datePicker.getValue();
                if (date == null || !date.isAfter(LocalDate.now().minusYears(18))) {
                    throw new FieldArgumentException("User must be at least 18 years old!");
                }
                user.setDateOfBirth(date);
            } else {
                NodeProcessorUtil.processNode(node, user);
            }
        }
        getRepository().set(List.of(user));
    }
}
