package braun.erik.prijevoz.builder;

import braun.erik.prijevoz.builder.util.ClassUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public interface SearchParameterBuilder {
    static void addSearchParameter(GridPane gridPane, String fieldName, String displayName, Integer rowIndex) {
        Text text = new Text();
        text.setText(displayName + ":");
        text.setTextAlignment(TextAlignment.RIGHT);

        TextField textField = new TextField();
        textField.setId(fieldName);

        gridPane.add(text, 0, rowIndex);
        GridPane.setHalignment(text, HPos.RIGHT);
        gridPane.add(textField, 1, rowIndex);
        GridPane.setMargin(textField, new Insets(5, 30, 5, 30));
    }

    public static <T> void build(GridPane gridPane, Class<T> type, EventHandler<ActionEvent> searchMethod, EventHandler<ActionEvent> clearMethod) {
        int rowIndex = 0;
        List<Field> classFields = ClassUtil.getAllFields(new ArrayList<>(), type);
        for (Field field : classFields) {
            if (ClassUtil.isJavaLang(field.getType())) {
                addSearchParameter(gridPane, field.getName(), ClassUtil.toDisplayName(field.getName()), rowIndex++);
            } else {
                String fieldPascalCase = field.getName();
                if (!fieldPascalCase.isEmpty()) {
                    fieldPascalCase = fieldPascalCase.substring(0, 1).toUpperCase() + fieldPascalCase.substring(1);
                }

                List<Field> nestedClassFields = ClassUtil.getAllFields(new ArrayList<>(), field.getType());
                for (Field nestedField : nestedClassFields) {
                    String nestedFieldPascalCase = nestedField.getName();
                    if (!nestedFieldPascalCase.isEmpty()) {
                        nestedFieldPascalCase = nestedField.getName().substring(0, 1).toUpperCase() + nestedFieldPascalCase.substring(1);
                    }
                    addSearchParameter(gridPane, fieldPascalCase + nestedFieldPascalCase, ClassUtil.toDisplayName(field.getName()) + "_" + ClassUtil.toDisplayName(nestedField.getName()), rowIndex++);
                }
            }
        }

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setButtonMinWidth(100);
        buttonBar.setPrefHeight(40);
        buttonBar.setPrefWidth(400);
        buttonBar.setPadding(new Insets(20, 30, 20, 20));

        gridPane.add(buttonBar, 1, rowIndex);
        GridPane.setValignment(buttonBar, VPos.BOTTOM);

        Button searchButton = new Button("_Search");
        searchButton.setMnemonicParsing(true);
        searchButton.setOnAction(searchMethod);

        Button clearButton = new Button("_Clear");
        clearButton.setMnemonicParsing(true);
        clearButton.setOnAction(clearMethod);

        buttonBar.getButtons().addAll(searchButton, clearButton);
    }
}
