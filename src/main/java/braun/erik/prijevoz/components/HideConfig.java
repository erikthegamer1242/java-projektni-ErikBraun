package braun.erik.prijevoz.components;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation used to allow us to hide a field from the UI
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface HideConfig {
    boolean hide() default false;
}
