package braun.erik.prijevoz.builder.util;

import java.lang.reflect.Field;
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
        return Character.toUpperCase(fieldName.charAt(0)) +
                fieldName.substring(1).replaceAll("([A-Z])", " $1");
    }

    static boolean isJavaLang(Object check) {
        String className = ((Class<?>) check).getName();
        return className.startsWith("java.");
    }
}
