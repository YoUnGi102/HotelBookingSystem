module com.example.hotelbookingsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hotelbookingsystem to javafx.fxml;
    exports com.example.hotelbookingsystem;
}