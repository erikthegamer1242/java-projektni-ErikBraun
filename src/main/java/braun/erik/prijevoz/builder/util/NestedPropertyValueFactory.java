package braun.erik.prijevoz.builder.util;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

public class NestedPropertyValueFactory<S, T> implements Callback<TableColumn.CellDataFeatures<S, T>, ObservableValue<T>> {

    private String fieldName;

    public NestedPropertyValueFactory(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public ObservableValue<T> call(TableColumn.CellDataFeatures<S, T> var1) {
        if (fieldName.contains(".")) {
            System.out.println("cutcemose");
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
