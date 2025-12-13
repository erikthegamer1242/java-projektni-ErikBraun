module braun.erik.prijevoz {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires javafx.base;
    requires javafx.graphics;

    opens braun.erik.prijevoz to javafx.fxml;
    opens braun.erik.prijevoz.model.superclasses to javafx.fxml, javafx.base;
    opens braun.erik.prijevoz.model.subclasses to javafx.fxml, javafx.base;
    opens braun.erik.prijevoz.model to javafx.fxml, javafx.base;
    exports braun.erik.prijevoz;
    exports braun.erik.prijevoz.controller;
    opens braun.erik.prijevoz.controller to javafx.fxml;
}