package braun.erik.prijevoz.builder;

import braun.erik.prijevoz.builder.util.ClassUtil;
import braun.erik.prijevoz.components.DropdownConfig;
import braun.erik.prijevoz.components.HideConfig;
import braun.erik.prijevoz.model.DisplayOption;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static braun.erik.prijevoz.builder.AddParametarBuilderHelper.*;

public class AddParameterBuilder {
    static Map<String, Pair<VBox, Field>> dropDownVBoxes = new HashMap<>();
    static GridPane mainGridPane;

    static VBox appendChild(String fieldName) {
        if (!dropDownVBoxes.containsKey(fieldName)) {
            throw new IllegalStateException("Field " + fieldName + " not found");
        }
        VBox dropDownVBox = dropDownVBoxes.get(fieldName).getKey();
        Field dropDownFieldType = dropDownVBoxes.get(fieldName).getValue();
        int lastButtonIndex = -1;
        for (int i = 0; i < dropDownVBox.getChildren().size(); i++) {
            if (dropDownVBox.getChildren().get(i) instanceof HBox) {
                lastButtonIndex = i;
            }
        }
        ComboBox<DisplayOption> comboBox = addComboBox(dropDownFieldType, fieldName);
        if (lastButtonIndex >= 0) {
            dropDownVBox.getChildren().add(lastButtonIndex, comboBox);
        } else {
            dropDownVBox.getChildren().add(comboBox);
        }
        DropdownConfig config = dropDownFieldType.getAnnotation(DropdownConfig.class);
        boolean canAddMultiple = config != null && config.allowMultiple();

        if (dropDownVBox.getChildren().stream().noneMatch(node -> node instanceof HBox) && canAddMultiple) {
            Button addDropdownButton = new Button("_+");
            addDropdownButton.setMnemonicParsing(true);
            addDropdownButton.setOnAction(AddParameterBuilder::addDropdown);
            addDropdownButton.setId(fieldName);
            HBox newHBox = new HBox();
            newHBox.getChildren().addLast(addDropdownButton);
            newHBox.setPrefWidth(Double.MAX_VALUE);
            newHBox.setAlignment(Pos.BASELINE_RIGHT);
            dropDownVBox.getChildren().addLast(newHBox);
        }
        dropDownVBoxes.put(fieldName, new Pair<>(dropDownVBox, dropDownFieldType));
        return dropDownVBox;
    }

    private static void addDropdown(ActionEvent event) {
        if (event.getSource() instanceof Button button) {
            VBox newVbox = appendChild(button.getId());
            mainGridPane.getChildren().remove(newVbox);
            mainGridPane.getChildren().add(newVbox);

        }
    }

    public static <T> void build(GridPane gridPane, Class<T> type, EventHandler<ActionEvent> searchMethod, EventHandler<ActionEvent> clearMethod) {
        int rowIndex = 0;
        dropDownVBoxes.clear();
        mainGridPane = gridPane;
        List<Field> classFields = ClassUtil.getAllFields(new ArrayList<>(), type);
        for (Field field : classFields) {
            HideConfig config = field.getAnnotation(HideConfig.class);
            boolean hide = config != null && config.hide();
            if (hide) {
                continue;
            }
            if (ClassUtil.isJavaLang(field.getType())) {
                addInputOrDropdown(dropDownVBoxes, gridPane, field, rowIndex++);
            } else {
                addDropdownParameter(dropDownVBoxes, gridPane, field, field.getName(), ClassUtil.toDisplayName(field.getName()), rowIndex++);
            }
        }

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setButtonMinWidth(100);
        buttonBar.setPrefHeight(40);
        buttonBar.setPrefWidth(400);
        buttonBar.setPadding(new Insets(20, 30, 20, 20));

        gridPane.add(buttonBar, 1, rowIndex);
        GridPane.setValignment(buttonBar, VPos.BOTTOM);

        Button searchButton = new Button("_Add");
        searchButton.setMnemonicParsing(true);
        searchButton.setOnAction(searchMethod);

        Button clearButton = new Button("_Clear");
        clearButton.setMnemonicParsing(true);
        clearButton.setOnAction(clearMethod);

        buttonBar.getButtons().addAll(searchButton, clearButton);
    }
}
