package braun.erik.prijevoz.components;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation used to allow us to hide a field from the UI
 * @author erik
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface HideConfig {
    /**
     * Checks to see if a field should be hidden from the UI
     *
     * @return true if yes, false if not
     */
    boolean hide() default false;
}
