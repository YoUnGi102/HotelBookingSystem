package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.model.Model;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;

public class ManageGuestViewModel {

    private Model model;

    private ObjectProperty<Address> address;
    private ObjectProperty<Guest> guest;

    public ManageGuestViewModel(Model model) {
        this.model = model;
        this.address = new SimpleObjectProperty<>();
        this.guest = new SimpleObjectProperty<>();
    }

    public void bindAddressProperty(ObjectProperty<Address> address){
        address.bindBidirectional(this.address);
    }
    public void bindGuestProperty(ObjectProperty<Guest> guest){
        guest.bindBidirectional(this.guest);
    }

    public void setGuest(Guest guest){
        this.guest.setValue(guest);
    }
    public Guest getGuest(){
        return guest.getValue();
    }

    public void add(Guest guest) throws SQLException {
        model.addGuest(guest);

        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Success");
        alert.setContentText("Guest successfully added");
        alert.getButtonTypes().add(ButtonType.OK);
        alert.show();

    }

    public void edit(Guest guest) throws SQLException {
        model.editGuest(guest);
    }

}