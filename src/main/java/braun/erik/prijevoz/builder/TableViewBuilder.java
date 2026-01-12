package braun.erik.prijevoz.builder;

import braun.erik.prijevoz.builder.util.ClassUtil;
import braun.erik.prijevoz.builder.util.NestedPropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface TableViewBuilder {

    static final String DATE_FORMAT = "dd.MM.yyyy";

    static <T> void addColumn(TableView<T> tableView, String fieldName, String displayName) {
        TableColumn<T, Object> column = new TableColumn<>(ClassUtil.toDisplayName(displayName));
        column.setCellValueFactory(new NestedPropertyValueFactory<>(fieldName));
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
        List<Field> classFields = ClassUtil.getAllFields(new ArrayList<>(), type);
        for (Field field : classFields) {
            if (ClassUtil.isJavaLang(field.getType()) || field.getType().isEnum()) {
                addColumn(initTable, field.getName(), field.getName());
            }
            else {
                String fieldPascalCase =  field.getName();
                if (!fieldPascalCase.isEmpty()) {
                    fieldPascalCase = fieldPascalCase.substring(0, 1).toUpperCase() + fieldPascalCase.substring(1);
                }

                List<Field> nestedClassFields = ClassUtil.getAllFields(new ArrayList<>(), field.getType());
                for (Field nestedField : nestedClassFields) {
                    String nestedFieldPascalCase =  nestedField.getName();
                    if (!nestedFieldPascalCase.isEmpty()) {
                        nestedFieldPascalCase = nestedField.getName().substring(0, 1).toUpperCase() + nestedFieldPascalCase.substring(1);
                    }
                    addColumn(initTable, fieldPascalCase + "." + nestedFieldPascalCase, ClassUtil.toDisplayName(field.getName()) + ": " + ClassUtil.toDisplayName(nestedField.getName()));
                }
            }

        }
        ObservableList<T> items = initTable.getItems();
        items.setAll(data);

    }
}
