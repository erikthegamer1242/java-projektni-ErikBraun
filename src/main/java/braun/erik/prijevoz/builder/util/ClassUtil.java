package braun.erik.prijevoz.builder.util;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public interface ClassUtil {
    static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        return fields;
    }

    static String toDisplayName(String fieldName) {
        return Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1).replaceAll("([A-Z])", " $1");
    }

    static boolean isJavaLang(Object check) {
        String className = ((Class<?>) check).getName();
        return className.startsWith("java.");
    }

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
