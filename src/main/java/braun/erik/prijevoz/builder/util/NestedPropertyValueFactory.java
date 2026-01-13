package braun.erik.prijevoz.builder.util;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

/**
 * Expanded JavaFX PropertyValueFactory to work with nested fields
 *
 * @param <S> entity type
 * @param <T> field type
 * @author erik
 * @version 1.0
 */
public class NestedPropertyValueFactory<S, T> implements Callback<TableColumn.CellDataFeatures<S, T>, ObservableValue<T>> {

    /**
     * Field name
     */
    private final String fieldName;

    /**
     * Constructor that sets the field name
     * @param fieldName string field name
     */
    public NestedPropertyValueFactory(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Methods that gets invoked when TableView tries to get data
     * @param var1 cell data
     * @return value
     */
    @Override
    public ObservableValue<T> call(TableColumn.CellDataFeatures<S, T> var1) {
        if (fieldName.contains(".")) {
            try {
                Object obj = ClassUtil.getNestedFieldGetterValue(fieldName, (var1).getValue());
                return new ReadOnlyObjectWrapper<>((T) obj);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new NoSuchElementException("Cannot access field " + fieldName, e);
            }

        }
        return new PropertyValueFactory<S, T>(fieldName).call(var1);
    }

}
