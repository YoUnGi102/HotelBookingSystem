package com.example.hotelbookingsystem.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import static com.example.hotelbookingsystem.view.ViewHandler.ICON;

public class DatabaseErrorAlert extends Alert {

    public DatabaseErrorAlert(){
        super(AlertType.NONE);
        setTitle("Database Connection Error");
        setContentText("There is a problem with connecting to the database\nPlease try again later.");
        getButtonTypes().add(ButtonType.OK);
        getDialogPane().getStylesheets().add("file:src/main/resources/style/alert.css");
        Stage stage = (Stage)(getDialogPane().getScene().getWindow());
        stage.getIcons().add(ICON);
    }

}
