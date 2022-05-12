package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;

public class MenuViewModel {

    private Model model;

    MenuViewModel(Model model){
        this.model = model;
    }

    public void logOff(){
        model.logOff();
    }

}
