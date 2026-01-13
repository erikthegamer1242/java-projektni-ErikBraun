package braun.erik.prijevoz.controller;

/**
 * Interface implementing methods to activate a controller
 *
 * @author erik
 * @version 1.0
 */
public interface ActivateController {
    /**
     * Called when activating a controller
     */
    public void onActivate();

    /**
     * Called when deactivating a controller
     */
    public void onDeactivate();
}
