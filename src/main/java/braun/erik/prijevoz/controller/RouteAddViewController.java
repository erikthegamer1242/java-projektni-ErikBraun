package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.builder.util.ClassUtil;
import braun.erik.prijevoz.components.NumberTextField;
import braun.erik.prijevoz.controller.util.ReflectionUtils;
import braun.erik.prijevoz.model.Route;
import braun.erik.prijevoz.model.Stop;
import braun.erik.prijevoz.repository.JSONRouteRepository;
import braun.erik.prijevoz.repository.Repository;
import braun.erik.prijevoz.util.DialogUtil;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouteAddViewController extends AddViewController<Route> {

    @Override
    protected Repository<Route> getRepository() {
        return new JSONRouteRepository();
    }

    @Override
    protected Class<Route> getEntityClass() {
        return Route.class;
    }

    Random r = new Random();

    @Override
    protected void addToRepository() {
        Route route = new Route();
        route.setId(r.nextInt());
        ObservableList<Node> gridPaneChildren = searchGridPane.getChildren();

        for (var node : gridPaneChildren) {
            processNode(node, route);
        }
        getRepository().set(List.of(route));
    }

    private void processNode(Node node, Route route) {
        if (node instanceof NumberTextField numberTextField) {
            processNumberTextField(numberTextField, route);
        } else if (node instanceof TextField textField) {
            processTextField(textField, route);
        } else if (node instanceof VBox vBox) {
            processVBox(vBox, route);
        }
    }

    private void processNumberTextField(NumberTextField numberTextField, Route route) {
        if (numberTextField.getText().isEmpty()) {
            DialogUtil.showErrorDialogWithDescription(
                    ClassUtil.toDisplayName(numberTextField.getId()) + " must be a positive number!",
                    "Cannot assign: " + numberTextField.getText() + " to " + ClassUtil.toDisplayName(numberTextField.getId())
            );
        } else {
            ReflectionUtils.setField(route, numberTextField.getId(), new BigDecimal(numberTextField.getText()));
        }
    }

    private void processTextField(TextField textField, Route route) {
        if (textField.getText().isEmpty()) {
            DialogUtil.showErrorDialogWithDescription(
                    ClassUtil.toDisplayName(textField.getId()) + " cannot be an empty string!",
                    "Cannot assign: " + textField.getText() + " to " + ClassUtil.toDisplayName(textField.getId())
            );
        } else {
            ReflectionUtils.setField(route, textField.getId(), textField.getText());
        }
    }

    private void processVBox(VBox vBox, Route route) {
        if (vBox.getChildren().isEmpty() || vBox.getChildren() == null) {
            DialogUtil.showCannotBeEmptyErrorDialog(ClassUtil.toDisplayName(vBox.getId()));
        } else if (isSingleComboBox(vBox)) {
            processSingleComboBox(vBox, route);
        } else {
            processMultipleComboBoxes(vBox, route);
        }
    }

    private boolean isSingleComboBox(VBox vBox) {
        return vBox.getChildren().size() == 1 && vBox.getChildren().getFirst() instanceof ComboBox<?>;
    }

    private void processSingleComboBox(VBox vBox, Route route) {
        ComboBox<?> comboBox = (ComboBox<?>) vBox.getChildren().getFirst();
        if (comboBox.getValue() == null) {
            DialogUtil.showCannotBeEmptyErrorDialog(ClassUtil.toDisplayName(vBox.getId()));
        } else {
            ReflectionUtils.setField(route, comboBox.getId(), comboBox.getValue());
        }
    }

    private void processMultipleComboBoxes(VBox vBox, Route route) {
        List<Stop> stops = new ArrayList<>();
        for (var child : vBox.getChildren()) {
            if (child instanceof ComboBox<?> comboBox) {
                if (comboBox.getValue() == null) {
                    DialogUtil.showCannotBeEmptyErrorDialog(ClassUtil.toDisplayName(vBox.getId()));
                }
                stops.add((Stop) comboBox.getValue());
            }
        }
        route.setStops(stops);
    }
}
