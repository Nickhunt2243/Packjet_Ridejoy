module org.example {
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.media;
    requires java.sql;
    requires mysql.connector.java;


    opens org.example to javafx.fxml;
    exports org.example;
}