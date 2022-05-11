package com.example.hotelbookingsystem.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DatabaseErrorAlert extends Alert {

    public DatabaseErrorAlert(){
        super(AlertType.NONE);
        setTitle("Database Connection Error");
        setContentText("There is a problem with connecting to the database\nPlease try again later.");
        getButtonTypes().add(ButtonType.OK);
    }

}
