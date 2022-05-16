package com.example.hotelbookingsystem.view.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import static com.example.hotelbookingsystem.view.ViewHandler.ICON;

public class SuccessAlert extends Alert {

    public SuccessAlert(){
        super(Alert.AlertType.NONE);
        setTitle("Success");
        getButtonTypes().add(ButtonType.OK);
        getDialogPane().getStylesheets().add("file:src/main/resources/style/alert.css");
        Stage stage = (Stage)(getDialogPane().getScene().getWindow());
        stage.getIcons().add(ICON);
    }

    public SuccessAlert(String message){
        super(Alert.AlertType.NONE);
        setContentText(message);
        setTitle("Success");
        getButtonTypes().add(ButtonType.OK);
        getDialogPane().getStylesheets().add("file:src/main/resources/style/alert.css");
        Stage stage = (Stage)(getDialogPane().getScene().getWindow());
        stage.getIcons().add(ICON);
    }

}
