package braun.erik.prijevoz.controller.util;

import java.lang.reflect.Method;

public class ReflectionUtils {

    public static void setField(Object obj, String fieldName, Object value) {
        try {
            String setterName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

            Method setter = null;
            for (Method m : obj.getClass().getMethods()) {
                if (m.getName().equals(setterName) && m.getParameterCount() == 1) {
                    setter = m;
                    break;
                }
            }

            if (setter != null) {
                setter.invoke(obj, value);
            } else {
                throw new NoSuchMethodException("No setter found for field: " + setterName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}