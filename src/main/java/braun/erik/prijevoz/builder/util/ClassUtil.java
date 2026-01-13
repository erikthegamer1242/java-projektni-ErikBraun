package braun.erik.prijevoz.builder.util;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Static interface containing methods used with reflection to format or get a certain field
 *
 * @author erik
 * @version 1.0
 */
public interface ClassUtil {

    /**
     * Using reflection and recursion gets all fields for a certain data type
     * @param fields initial list of top level fields from a class
     * @param type class type
     * @return returns a list of all the fields and subfields
     */
    static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        return fields;
    }

    /**
     * Converts a java field name to a nicer looking display name to show to the user
     * @param fieldName initial field name
     * @return string display name
     */
    static String toDisplayName(String fieldName) {
        return Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1).replaceAll("([A-Z])", " $1");
    }

    /**
     * Checks to see if a parameter is from a user class or from a java class
     * @param check object to be checked
     * @return boolean true if from java, false if from user
     */
    static boolean isJavaLang(Object check) {
        String className = ((Class<?>) check).getName();
        return className.startsWith("java.");
    }

    /**
     * Tries to get a value from a nested getter by calling the parameter getter, and thew the child getter
     * @param getterName initial field name
     * @param value type of value
     * @return the value if getter found
     * @throws NoSuchMethodException when no getter is found
     * @throws InvocationTargetException when the getter itself throws
     * @throws IllegalAccessException when we cannot access the getter
     */
    static Object getNestedFieldGetterValue(String getterName, Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] gettersSplit = getterName.split("\\.");

        for (var getter : gettersSplit) {
            if (value == null) break;
            String getterFull = "get" + getter.substring(0, 1).toUpperCase() + getter.substring(1);

            Method method = value.getClass().getMethod(getterFull);
            value = method.invoke(value);
        }
        return value;
    }
}
