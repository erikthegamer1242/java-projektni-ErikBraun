module braun.erik.prijevoz {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens braun.erik.prijevoz to javafx.fxml;
    exports braun.erik.prijevoz;
}