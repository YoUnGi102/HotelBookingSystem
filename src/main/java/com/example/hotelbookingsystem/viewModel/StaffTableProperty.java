package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.model.Staff;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StaffTableProperty {

    protected StringProperty username;
    protected StringProperty firstName;
    protected StringProperty lastName;
    protected StringProperty email;
    protected StringProperty phoneNumber;

    private Staff staff;

    public StaffTableProperty(Staff staff) {
        setStaff(staff);
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
        this.username = new SimpleStringProperty(staff.getUsername());
        this.firstName = new SimpleStringProperty(staff.getFirstName());
        this.lastName = new SimpleStringProperty(staff.getLastName());
        this.email = new SimpleStringProperty(staff.getEmail());
        this.phoneNumber = new SimpleStringProperty(staff.getPhoneNumber());
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
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

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public Staff getStaff() {
        return staff;
    }
}
