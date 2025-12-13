package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    public void showDriverScreen() {
        Scene scene = null;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("views/main-view.fxml"));
        try {
            scene = new Scene(fxmlLoader.load(), 1280, 720);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = MainApp.getMainStage();
        stage.setTitle("Main screen");
        stage.setScene(scene);
        stage.show();
    }
}
