package braun.erik.prijevoz.controller.util;

import braun.erik.prijevoz.MainApp;
import braun.erik.prijevoz.util.DialogUtil;

import java.lang.reflect.Method;

/**
 * Static interface used to get/set data using reflection
 *
 * @author erik
 * @version 1.0
 */
public interface ReflectionUtils {

    /**
     * Sets a field using reflection
     * @param obj object from which to find a setter
     * @param fieldName field name
     * @param value value to set
     */
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
            DialogUtil.showErrorDialog("Error setting field: " + fieldName, e.getMessage());
            MainApp.logger.error("Error setting field: {}", fieldName, e);
        }
    }
}