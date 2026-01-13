package braun.erik.prijevoz.util;

import javafx.scene.control.Alert;

/**
 * Static interface implementing different dialogs
 *
 * @author erik
 * @version 1.0
 */
public interface DialogUtil {

    /**
     * Called when there is an error reading a file
     * @param what file name
     */
    public static void showReadErrorDialog(String what) {
        showErrorDialog("Error reading " + what, "Cannot read " + what + ". Check if the file exists and try again!");
    }

    /**
     * Called when there is an error writing to a file
     * @param what file name
     */
    public static void showWriteErrorDialog(String what) {
        showErrorDialog("Error writing " + what, "Cannot write " + what + ". Check if the file exists and that you have right permissions!");
    }

    /**
     * Called when there is an error loading the main screen
     */
    public static void showLoadingScreenErrorDialog() {
        showErrorDialog("Cannot load screen!", "Something went wrong while loading the screen, please try again.");
    }

    /**
     * Called when data is empty
     * @param what data name
     */
    public static void showCannotBeEmptyErrorDialog(String what) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(what + " cannot be empty!");

        alert.showAndWait();
    }

    /**
     * Generic error dialog
     * @param title dialog title
     * @param message error message
     */
    public static void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    /**
     * Generic error dialog with a description
     * @param title error title
     * @param message error description
     */
    public static void showErrorDialogWithDescription(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

}
