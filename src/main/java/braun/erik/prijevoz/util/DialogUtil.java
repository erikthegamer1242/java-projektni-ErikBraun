package braun.erik.prijevoz.util;

import javafx.scene.control.Alert;

public interface DialogUtil {

    public static void showReadErrorDialog(String what) {
        showErrorDialog("Error reading " + what, "Cannot read " + what + ". Check if the file exists and try again!");
    }

    public static void showWriteErrorDialog(String what) {
        showErrorDialog("Error writing " + what, "Cannot write " + what + ". Check if the file exists and that you have right permissions!");
    }

    public static void showLoadingScreenErrorDialog() {
        showErrorDialog("Cannot load screen!", "Something went wrong while loading the screen, please try again.");
    }

    public static void showCannotBeEmptyErrorDialog(String what) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(what + " cannot be empty!");

        alert.showAndWait();
    }

    public static void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void showErrorDialogWithDescription(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

}
