package braun.erik.prijevoz.builder;

import braun.erik.prijevoz.builder.util.ClassUtil;
import braun.erik.prijevoz.components.NumberTextField;
import braun.erik.prijevoz.model.DisplayOption;
import braun.erik.prijevoz.repository.Repository;
import braun.erik.prijevoz.repository.RepositoryLookup;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Pair;
import javafx.util.StringConverter;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static braun.erik.prijevoz.builder.AddParameterBuilder.appendChild;

public interface AddParametarBuilderHelper {
    static void addInputParameter(GridPane gridPane, Field type, String fieldName, String displayName, Integer rowIndex) {
        Text text = new Text();
        text.setText(displayName + ":");
        text.setTextAlignment(TextAlignment.RIGHT);

        Node textField;
        if (Number.class.isAssignableFrom(type.getType())) {
            textField = new NumberTextField();
        } else if (LocalDate.class.isAssignableFrom(type.getType())) {
            DatePicker datePicker = new DatePicker();
            datePicker.setMaxWidth(Double.MAX_VALUE);
            datePicker.setPromptText("dd.MM.yyyy");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            datePicker.setConverter(new StringConverter<>() {
                @Override
                public String toString(LocalDate date) {
                    return (date != null) ? formatter.format(date) : "";
                }

                @Override
                public LocalDate fromString(String string) {
                    return (string != null && !string.isEmpty()) ? LocalDate.parse(string, formatter) : null;
                }
            });
            textField = datePicker;
        } else {
            textField = new TextField();
        }
        textField.setId(fieldName);

        gridPane.add(text, 0, rowIndex);
        GridPane.setHalignment(text, HPos.RIGHT);
        gridPane.add(textField, 1, rowIndex);
        GridPane.setMargin(textField, new Insets(5, 30, 5, 30));
    }

    static void addDropdownParameter(Map<String, Pair<VBox, Field>> dropDownVBoxes, GridPane gridPane, Field field, String fieldName, String displayName, Integer rowIndex) {
        Text text = new Text();
        text.setText(displayName + ":");
        text.setTextAlignment(TextAlignment.RIGHT);

        dropDownVBoxes.computeIfAbsent(fieldName, k -> {
            VBox newVBox = new VBox(5);
            newVBox.setMaxWidth(Double.MAX_VALUE);
            newVBox.setPrefWidth(Double.MAX_VALUE);
            newVBox.setId(k);
            return new Pair<>(newVBox, field);
        });

        VBox dropDownVBox = appendChild(fieldName);
        gridPane.add(text, 0, rowIndex);
        GridPane.setMargin(text, new Insets(10, 0, 5, 0));
        GridPane.setHalignment(text, HPos.RIGHT);
        GridPane.setValignment(text, VPos.TOP);

        gridPane.add(dropDownVBox, 1, rowIndex);
        GridPane.setMargin(dropDownVBox, new Insets(5, 30, 5, 30));
    }

    static void addInputOrDropdown(Map<String, Pair<VBox, Field>> dropDownVBoxes, GridPane gridPane, Field field, Integer rowIndex) {
        if (java.util.List.class.isAssignableFrom(field.getType()) || field.getType().isEnum()) {
            addDropdownParameter(dropDownVBoxes, gridPane, field, field.getName(), ClassUtil.toDisplayName(field.getName()), rowIndex);
        } else {
            addInputParameter(gridPane, field, field.getName(), ClassUtil.toDisplayName(field.getName()), rowIndex);
        }
    }

    static ComboBox<DisplayOption> addComboBox(Field field, String fieldName) {
        Class<?> rawType = field.getType().equals(List.class) ? (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0] : field.getType();

        if (!DisplayOption.class.isAssignableFrom(rawType)) {
            throw new IllegalStateException("Type does not implement DisplayOption");
        }

        @SuppressWarnings("unchecked") Class<? extends DisplayOption> type = (Class<? extends DisplayOption>) rawType;
        Repository<? extends DisplayOption> repo = RepositoryLookup.getRepository(type);
        List<? extends DisplayOption> data = repo.get();
        ComboBox<DisplayOption> comboBox = new ComboBox<>();
        if (DisplayOption.class.isAssignableFrom(type)) {
            comboBox.getItems().addAll(data);
            comboBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(DisplayOption data) {
                    if (data != null) {
                        return data.simpleName();
                    }
                    return "";
                }

                @Override
                public DisplayOption fromString(String string) {
                    return null;
                }
            });
        }
        comboBox.setId(fieldName);
        comboBox.setMaxWidth(Double.MAX_VALUE);
        return comboBox;
    }
}
