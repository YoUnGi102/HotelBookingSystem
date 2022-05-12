package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;

import java.sql.SQLException;

public class LoginViewModel {

    private Model model;

    public LoginViewModel(Model model){
        this.model = model;
    }

    public void login(String username, String password) throws SQLException, IllegalAccessException {
        model.login(username, password);
    }

}
