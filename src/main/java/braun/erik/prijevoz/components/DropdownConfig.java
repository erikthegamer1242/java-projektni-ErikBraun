package braun.erik.prijevoz.components;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation used to allow us to set if a field with dropdown can have more dropdowns added
 * @author erik
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DropdownConfig {
    /**
     * Checks to see if a field can have multiple dropdowns
     *
     * @return true if multiple, false if not
     */
    boolean allowMultiple() default false;
}
