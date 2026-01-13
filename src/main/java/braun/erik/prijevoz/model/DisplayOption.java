package braun.erik.prijevoz.model;

/**
 * Interface implemented by all entities to show a simplified name in the UI
 *
 * @author erik
 * @version 1.0
 */
public interface DisplayOption {

    /**
     * Returns a simplified name used in JavaFX ComboBox
     *
     * @return string simpleName
     */
    public String simpleName();
}
