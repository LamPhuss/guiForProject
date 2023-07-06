module com.example.demogui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires org.apache.commons.lang3;
    requires org.jsoup;
    requires org.apache.jena.ext.com.google;
    requires java.desktop;
    opens entity to javafx.base;
    opens com.example.demogui to javafx.fxml;
    exports com.example.demogui;
    exports com.example.demogui.Controller;
    opens com.example.demogui.Controller to javafx.fxml;
    exports com.example.demogui.Entity;
    opens com.example.demogui.Entity to javafx.fxml;
}