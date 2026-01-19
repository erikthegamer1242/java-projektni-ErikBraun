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

import static braun.erik.prijevoz.builder.AddParameterBuilderHelper.*;

/**
 * Class used to build all add parameters for a certain entity
 *
 * @author erik
 * @version 1.0
 */

public class AddParameterBuilder {
    static Map<String, Pair<VBox, Field>> dropDownVBoxes = new HashMap<>();
    static GridPane mainGridPane;

    private AddParameterBuilder() {
    }

    /**
     * Appends a child to a VBox
     * @param fieldName string name of the field to be used as id and VBox lookup
     * @return Vbox newly built VBox
     */

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

        if (dropDownVBox.getChildren().stream().noneMatch(HBox.class::isInstance) && canAddMultiple) {
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

    /**
     * Appends a new ComboBox to an existing VBox depending on the button(field name) id
     * @param event JavaFX event
     */
    private static void addDropdown(ActionEvent event) {
        if (event.getSource() instanceof Button button) {
            VBox newVbox = appendChild(button.getId());
            mainGridPane.getChildren().remove(newVbox);
            mainGridPane.getChildren().add(newVbox);

        }
    }

    /**
     * Builds the whole UI for all the parameters
     *
     * @param gridPane    GridPane to populate with UI elements
     * @param type        entity type
     * @param addMethod   method instance for add button callback
     * @param clearMethod method instance for clear button callback
     * @param <T>         entity type
     */
    public static <T> void build(GridPane gridPane, Class<T> type, EventHandler<ActionEvent> addMethod, EventHandler<ActionEvent> clearMethod) {
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
        searchButton.setOnAction(addMethod);

        Button clearButton = new Button("_Clear");
        clearButton.setMnemonicParsing(true);
        clearButton.setOnAction(clearMethod);

        buttonBar.getButtons().addAll(searchButton, clearButton);
    }
}
