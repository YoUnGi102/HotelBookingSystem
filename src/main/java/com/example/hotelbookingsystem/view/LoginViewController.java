package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.view.alert.DatabaseErrorAlert;
import com.example.hotelbookingsystem.view.alert.ErrorAlert;
import com.example.hotelbookingsystem.viewModel.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.sql.SQLException;

public class LoginViewController implements Controller {

    private ViewHandler viewHandler;
    private LoginViewModel viewModel;
    private Region root;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void init(ViewHandler viewHandler, LoginViewModel viewModel, Region root, Controller previousView){
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
    }

    public void login(){
        Alert alert;
        try {
            viewModel.login(username.getText(), password.getText());
        } catch (SQLException e) {
            alert = new DatabaseErrorAlert();
            alert.show();
            return;
        } catch (IllegalAccessException e) {
            alert = new ErrorAlert();
            alert.setContentText("Username and password do not match!");
            alert.show();
            return;
        }finally {
            password.setText("");
            username.setText("");
        }
        viewHandler.openView(ViewHandler.MENU_VIEW, this);
    }

    public Region getRoot(){
        return root;
    }

}
