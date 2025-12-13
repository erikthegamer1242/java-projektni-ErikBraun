package braun.erik.prijevoz.builder;

import braun.erik.prijevoz.builder.utils.ClassUtils;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface TableViewBuilder {

    static final String DATE_FORMAT = "dd.MM.yyyy";

    static <T> void addColumn(TableView<T> tableView, String fieldName, String displayName) {
        TableColumn<T, Object> column = new TableColumn<>(ClassUtils.toDisplayName(displayName));
        column.setCellValueFactory(new PropertyValueFactory<>(fieldName));
        column.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText("");
                } else if (item == null) {
                    setText("Not available");
                } else if (item instanceof LocalDate localDate) {
                    setText(DateTimeFormatter.ofPattern(DATE_FORMAT).format(localDate));
                } else {
                    setText(item.toString());
                }
            }
        });
        tableView.getColumns().add(column);
    }

    public static <T> void build(TableView<T> initTable, List<T> data, Class<T> type) {
        List<Field> classFields = ClassUtils.getAllFields(new ArrayList<>(), type);
        for (Field field : classFields) {
            if (ClassUtils.isJavaLang(field.getType())) {
                addColumn(initTable, field.getName(), field.getName());
            }
            else {
                String fieldPascalCase =  field.getName();
                if (!fieldPascalCase.isEmpty()) {
                    fieldPascalCase = fieldPascalCase.substring(0, 1).toUpperCase() + fieldPascalCase.substring(1);
                }

                List<Field> nestedClassFields = ClassUtils.getAllFields(new ArrayList<>(), field.getType());
                for (Field nestedField : nestedClassFields) {
                    String nestedFieldPascalCase =  nestedField.getName();
                    if (!nestedFieldPascalCase.isEmpty()) {
                        nestedFieldPascalCase = nestedField.getName().substring(0, 1).toUpperCase() + nestedFieldPascalCase.substring(1);
                    }
                    addColumn(initTable, fieldPascalCase + nestedFieldPascalCase, ClassUtils.toDisplayName(field.getName()) + ": " + ClassUtils.toDisplayName(nestedField.getName()));
                }
            }

        }
        initTable.getItems().addAll(data);
    }
}
