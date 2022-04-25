module com.example.hotelbookingsystem {
    requires javafx.controlsEmpty;
    requires javafx.fxmlEmpty;
    requires java.sql;

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
}