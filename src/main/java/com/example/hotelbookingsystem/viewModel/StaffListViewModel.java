package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.Staff;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class StaffListViewModel {

    private Model model;

    private ObjectProperty<ObservableList<StaffTableProperty>> listProperty;
    private Staff currentStaff;

    public StaffListViewModel(Model model){
        this.model = model;
        this.listProperty = new SimpleObjectProperty<>();
        currentStaff = null;
    }

    public Staff getCurrentStaff() {
        return currentStaff;
    }

    public void setCurrentStaff(Staff currentStaff) {
        this.currentStaff = currentStaff;
    }

    public void bindListObjectProperty(ObjectProperty<ObservableList<StaffTableProperty>> property){
        property.bind(listProperty);
    }

    public void getAllStaff() throws SQLException {
        ObservableList<StaffTableProperty> tableProperties = FXCollections.observableArrayList();
        this.model.getAllStaff().forEach((item) -> {
            tableProperties.add(new StaffTableProperty(item));
        });
        listProperty.setValue(tableProperties);
    }

    public void removeStaff(Staff staff) throws SQLException {
        this.model.removeStaff(staff);
    }

}
