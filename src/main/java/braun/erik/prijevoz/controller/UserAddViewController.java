package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.controller.util.NodeProcessorUtil;
import braun.erik.prijevoz.model.subclasses.User;
import braun.erik.prijevoz.repository.JSONUserRepository;
import braun.erik.prijevoz.repository.Repository;
import braun.erik.prijevoz.util.DialogUtil;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.List;

public class UserAddViewController extends AddViewController<User> {

    @Override
    public Repository<User> getRepository() {
        return new JSONUserRepository();
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected void addToRepository() throws IllegalArgumentException {
        User user = new User();
        ObservableList<Node> gridPaneChildren = searchGridPane.getChildren();

        for (var node : gridPaneChildren) {
            if (node instanceof DatePicker datePicker) {
                LocalDate date = datePicker.getValue();
                if (date == null || !date.isAfter(LocalDate.now().minusYears(18))) {
                    DialogUtil.showErrorDialog("Error!", "User must be at least 18 years old!");
                    throw new IllegalArgumentException("User must be at least 18 years old!");
                }
                user.setDateOfBirth(date);
            } else {
                NodeProcessorUtil.processNode(node, user);
            }
        }
        getRepository().set(List.of(user));
    }
}
