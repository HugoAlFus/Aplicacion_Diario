module frontend {
    requires java.net.http;
    requires javafx.fxml;
    requires javafx.controls;
    requires com.google.gson;
    requires org.apache.logging.log4j;
    requires org.jspecify;
    requires java.desktop;
    requires org.apache.pdfbox;

    exports es.cheste.frontend;
    exports es.cheste.frontend.controller;

    opens es.cheste.frontend.controller to javafx.fxml;
    opens es.cheste.frontend.dto to com.google.gson;
}