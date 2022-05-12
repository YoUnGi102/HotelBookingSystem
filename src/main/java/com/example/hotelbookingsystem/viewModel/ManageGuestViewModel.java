package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.model.Model;

import java.sql.SQLException;

public class ManageGuestViewModel {
    private Model model;

    public ManageGuestViewModel(Model model) {
        this.model = model;
    }


    public void add(Guest guest) throws SQLException
    {
        model.addGuest(guest);
    }
}
