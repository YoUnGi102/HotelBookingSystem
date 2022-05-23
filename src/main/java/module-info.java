/**
 *
 */
module com.example.hotelbookingsystem {
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.controls;
    requires java.rmi;
    requires java.desktop;

    opens com.example.hotelbookingsystem.model to javafx.fxml;
    exports com.example.hotelbookingsystem.model;
    opens com.example.hotelbookingsystem.view to javafx.fxml;
    exports com.example.hotelbookingsystem.view;
    opens com.example.hotelbookingsystem.viewModel to javafx.fxml;
    exports com.example.hotelbookingsystem.viewModel;
    opens com.example.hotelbookingsystem to javafx.fxml;
    exports com.example.hotelbookingsystem;
    exports com.example.hotelbookingsystem.dao;
    opens com.example.hotelbookingsystem.dao to javafx.fxml;
    exports com.example.hotelbookingsystem.model.list;
    opens com.example.hotelbookingsystem.model.list to javafx.fxml;
    exports com.example.hotelbookingsystem.view.alert;
    opens com.example.hotelbookingsystem.view.alert to javafx.fxml;
    opens com.example.hotelbookingsystem.server;
    exports com.example.hotelbookingsystem.server to java.rmi;
}