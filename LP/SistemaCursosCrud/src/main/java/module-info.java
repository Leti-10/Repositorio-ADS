module org.lingprog.sitemascursoscrud {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.net.http;
    requires java.desktop;

    opens gui to javafx.fxml;
    opens org.lingprog.sistemacursoscrud to javafx.fxml;
    opens modelo to javafx.base, javafx.fxml;
    exports gui;
    exports org.lingprog.sistemacursoscrud;
}