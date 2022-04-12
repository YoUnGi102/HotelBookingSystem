module com.example.hotelbookingsystem {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.hotelbookingsystem.model to javafx.fxml;
    exports com.example.hotelbookingsystem.model;
    opens com.example.hotelbookingsystem.view to javafx.fxml;
    exports com.example.hotelbookingsystem.view;
    opens com.example.hotelbookingsystem.viewModel to javafx.fxml;
    exports com.example.hotelbookingsystem.viewModel;
    opens com.example.hotelbookingsystem to javafx.fxml;
    exports com.example.hotelbookingsystem;
}