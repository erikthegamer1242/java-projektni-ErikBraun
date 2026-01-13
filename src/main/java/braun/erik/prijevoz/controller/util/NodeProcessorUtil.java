package braun.erik.prijevoz.controller.util;

import braun.erik.prijevoz.builder.util.ClassUtil;
import braun.erik.prijevoz.components.NumberTextField;
import braun.erik.prijevoz.model.DisplayOption;
import braun.erik.prijevoz.util.DialogUtil;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface NodeProcessorUtil {

    public static void processNode(Node node, Object entity) {
        if (node instanceof NumberTextField numberTextField) {
            processNumberTextField(numberTextField, entity);
        } else if (node instanceof TextField textField) {
            processTextField(textField, entity);
        } else if (node instanceof DatePicker datePicker) {
            processDatePicker(datePicker, entity);
        } else if (node instanceof VBox vBox) {
            processVBox(vBox, entity);
        }
    }

    public static void processNumberTextField(NumberTextField numberTextField, Object entity) throws IllegalArgumentException {
        if (numberTextField.getText().isEmpty()) {
            DialogUtil.showErrorDialogWithDescription(
                    ClassUtil.toDisplayName(numberTextField.getId()) + " must be a positive number!",
                    "Cannot assign: " + numberTextField.getText() + " to " + ClassUtil.toDisplayName(numberTextField.getId())
            );
            throw new IllegalArgumentException("Cannot assign: " + numberTextField.getText() + " to " + ClassUtil.toDisplayName(numberTextField.getId()));
        } else {
            ReflectionUtils.setField(entity, numberTextField.getId(), new BigDecimal(numberTextField.getText()));
        }
    }

    public static void processTextField(TextField textField, Object entity) throws IllegalArgumentException {
        if (textField.getText().isEmpty()) {
            DialogUtil.showCannotBeEmptyErrorDialog(
                    ClassUtil.toDisplayName(textField.getId()));
            throw new IllegalArgumentException("Cannot assign: " + textField.getText() + " to " + ClassUtil.toDisplayName(textField.getId()));
        } else {
            ReflectionUtils.setField(entity, textField.getId(), textField.getText());
        }
    }

    public static void processDatePicker(DatePicker datePicker, Object entity) throws IllegalArgumentException {
        if (datePicker.getValue() == null) {
            DialogUtil.showCannotBeEmptyErrorDialog(
                    ClassUtil.toDisplayName(datePicker.getId()));
            throw new IllegalArgumentException("Cannot assign: " + datePicker.getValue() + " to " + ClassUtil.toDisplayName(datePicker.getId()));
        } else {
            ReflectionUtils.setField(entity, datePicker.getId(), datePicker.getValue());
        }
    }

    public static void processVBox(VBox vBox, Object entity) throws IllegalArgumentException {
        if (vBox.getChildren().isEmpty() || vBox.getChildren() == null) {
            DialogUtil.showCannotBeEmptyErrorDialog(ClassUtil.toDisplayName(vBox.getId()));
            throw new IllegalArgumentException("Cannot assign: " + vBox.getId() + " to " + ClassUtil.toDisplayName(vBox.getId()));
        } else if (isSingleComboBox(vBox)) {
            processSingleComboBox(vBox, entity);
        } else {
            processMultipleComboBoxes(vBox, entity);
        }
    }

    public static boolean isSingleComboBox(VBox vBox) {
        return vBox.getChildren().size() == 1 && vBox.getChildren().getFirst() instanceof ComboBox<?>;
    }

    public static void processSingleComboBox(VBox vBox, Object entity) throws IllegalArgumentException {
        ComboBox<?> comboBox = (ComboBox<?>) vBox.getChildren().getFirst();
        if (comboBox.getValue() == null) {
            DialogUtil.showCannotBeEmptyErrorDialog(ClassUtil.toDisplayName(vBox.getId()));
            throw new IllegalArgumentException("Cannot assign: " + vBox.getId() + " to " + ClassUtil.toDisplayName(vBox.getId()));
        } else {
            ReflectionUtils.setField(entity, comboBox.getId(), comboBox.getValue());
        }
    }

    public static void processMultipleComboBoxes(VBox vBox, Object entity) throws IllegalArgumentException {
        List<DisplayOption> items = new ArrayList<>();
        for (var child : vBox.getChildren()) {
            if (child instanceof ComboBox<?> comboBox) {
                if (comboBox.getValue() == null) {
                    DialogUtil.showCannotBeEmptyErrorDialog(ClassUtil.toDisplayName(vBox.getId()));
                    throw new IllegalArgumentException("Cannot assign: " + vBox.getId() + " to " + ClassUtil.toDisplayName(vBox.getId()));
                }
                items.add((DisplayOption) comboBox.getValue());
            }
        }
        ReflectionUtils.setField(entity, vBox.getId(), items);
    }
}
