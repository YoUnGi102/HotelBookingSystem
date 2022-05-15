package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.Staff;

public class MenuViewModel {

    private Model model;

    MenuViewModel(Model model){
        this.model = model;
    }

    public void logOff(){
        model.logOff();
    }

    public Staff getStaff(){
        return model.getStaff();
    }

}
