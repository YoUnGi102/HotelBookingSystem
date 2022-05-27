package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Staff;
import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.Staff;
import com.example.hotelbookingsystem.view.alert.SuccessAlert;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;

public class ManageStaffViewModel {

    private Model model;

    private ObjectProperty<Address> address;
    private ObjectProperty<Staff> staff;

    public ManageStaffViewModel(Model model) {
        this.model = model;
        this.address = new SimpleObjectProperty<>();
        this.staff = new SimpleObjectProperty<>();
    }

    public void bindAddressProperty(ObjectProperty<Address> address){
        address.bindBidirectional(this.address);
    }
    public void bindStaffProperty(ObjectProperty<Staff> staff){
        staff.bindBidirectional(this.staff);
    }

    public void setStaff(Staff staff){
        this.staff.setValue(staff);
    }
    public Staff getStaff(){
        return staff.getValue();
    }

    public void add(Staff staff) throws SQLException {
        model.addStaff(staff);
    }

    public void edit(Staff staff) throws SQLException {
        model.editStaff(staff);
    }


}
