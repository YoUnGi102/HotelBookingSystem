package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Guest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GuestTableProperty {

    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty address;
    private StringProperty phoneNumber;
    private StringProperty email;
    private StringProperty passportNumber;

    public GuestTableProperty(Guest guest) {
        setGuest(guest);
    }

    public void setGuest(Guest guest){
        this.firstName = new SimpleStringProperty(guest.getFirstName());
        this.lastName = new SimpleStringProperty(guest.getLastName());
        this.address = new SimpleStringProperty(guest.getAddress().toString());
        this.phoneNumber = new SimpleStringProperty(guest.getPhoneNumber());
        this.email = new SimpleStringProperty(guest.getEmail());
        this.passportNumber = new SimpleStringProperty(guest.getPassportNumber());
    }

    public String getFirstName() {
        return firstName.get();
    }
    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }
    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getAddress() {
        return address.get();
    }
    public StringProperty addressProperty() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }
    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public String getEmail() {
        return email.get();
    }
    public StringProperty emailProperty() {
        return email;
    }

    public String getPassportNumber() {
        return passportNumber.get();
    }
    public StringProperty passportNumberProperty() {
        return passportNumber;
    }
}
