package braun.erik.prijevoz.components;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation used to allow us to set if a field with dropdown can have more dropdowns added
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DropdownConfig {
    boolean allowMultiple() default false;
}
