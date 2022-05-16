package com.example.hotelbookingsystem.view.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import static com.example.hotelbookingsystem.view.ViewHandler.ICON;

public class ErrorAlert extends Alert {

    public ErrorAlert(){
        super(AlertType.NONE);
        setTitle("Error");
        getButtonTypes().add(ButtonType.OK);
        getDialogPane().getStylesheets().add("file:src/main/resources/style/alert.css");
        Stage stage = (Stage)(getDialogPane().getScene().getWindow());
        stage.getIcons().add(ICON);
    }

    public ErrorAlert(String message){
        super(AlertType.NONE);
        setContentText(message);
        setTitle("Error");
        getButtonTypes().add(ButtonType.OK);
        getDialogPane().getStylesheets().add("file:src/main/resources/style/alert.css");
        Stage stage = (Stage)(getDialogPane().getScene().getWindow());
        stage.getIcons().add(ICON);
    }

}
