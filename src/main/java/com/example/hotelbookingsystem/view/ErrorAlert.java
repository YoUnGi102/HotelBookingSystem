package com.example.hotelbookingsystem.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ErrorAlert extends Alert {

    public ErrorAlert(){
        super(AlertType.NONE);
        setTitle("Error");
        getButtonTypes().add(ButtonType.OK);
    }

}
