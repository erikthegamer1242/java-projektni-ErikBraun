module braun.erik.prijevoz {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires javafx.base;
    requires javafx.graphics;
    requires jakarta.xml.bind;
    requires jakarta.json.bind;
    requires org.slf4j;
    opens braun.erik.prijevoz to javafx.fxml, jakarta.xml.bind;
    opens braun.erik.prijevoz.repository to jakarta.xml.bind;
    opens braun.erik.prijevoz.repository.util to jakarta.xml.bind;
    opens braun.erik.prijevoz.model.superclasses to javafx.fxml, javafx.base;
    opens braun.erik.prijevoz.model.subclasses to javafx.fxml, javafx.base;
    opens braun.erik.prijevoz.model to javafx.fxml, javafx.base;
    exports braun.erik.prijevoz;
    exports braun.erik.prijevoz.repository;
    exports braun.erik.prijevoz.controller;
    exports braun.erik.prijevoz.model.subclasses;
    exports braun.erik.prijevoz.model.superclasses;
    opens braun.erik.prijevoz.controller to javafx.fxml;
}