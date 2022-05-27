package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Manager;
import com.example.hotelbookingsystem.model.Receptionist;
import com.example.hotelbookingsystem.model.Staff;
import com.example.hotelbookingsystem.view.alert.DatabaseErrorAlert;
import com.example.hotelbookingsystem.view.alert.ErrorAlert;
import com.example.hotelbookingsystem.view.alert.SuccessAlert;
import com.example.hotelbookingsystem.viewModel.ManageStaffViewModel;
import com.example.hotelbookingsystem.viewModel.ManageStaffViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.sql.SQLException;

import static com.example.hotelbookingsystem.dao.StaffTable.MANAGER;
import static com.example.hotelbookingsystem.dao.StaffTable.RECEPTIONIST;

public class ManageStaffViewController implements Controller {

    @FXML
    private ChoiceBox<String> privilege;
    @FXML
    private TextField firstName, lastName, eMail, phoneNumber, number, street, city, postCode, country, username, password;

    private Controller previousView;

    private ObjectProperty<Staff> staff;
    private ObjectProperty<Address> address;

    private Region root;
    private ViewHandler viewHandler;
    private ManageStaffViewModel viewModel;

    public void init(ViewHandler viewHandler, ManageStaffViewModel viewModel, Region root, Controller previousView) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
        this.previousView = previousView;

        ObservableList<String> values = FXCollections.observableArrayList(RECEPTIONIST, MANAGER);
        privilege.setItems(values);
        privilege.setValue("receptionist");

        address = new SimpleObjectProperty<>();
        viewModel.bindAddressProperty(address);

        staff = new SimpleObjectProperty<>();
        staff.addListener((observableValue, oldVal, newVal) -> {
            if(newVal == null){
                username.setEditable(true);
                return;
            }

            Staff g = newVal;
            firstName.setText(g.getFirstName());
            lastName.setText(g.getLastName());
            eMail.setText(g.getEmail());
            phoneNumber.setText(g.getPhoneNumber());
            password.setPromptText("Enter New Password");
            username.setText(g.getUsername());
            username.setEditable(false);

            Address a = g.getAddress();
            number.setText(a.getHouseNumber());
            street.setText(a.getStreet());
            city.setText(a.getCity());
            postCode.setText(a.getPostalCode());
            country.setText(a.getCountry());
        });
        viewModel.bindStaffProperty(staff);

    }

    public ManageStaffViewModel getViewModel() {
        return viewModel;
    }

    public Region getRoot() {
        return root;
    }

    private void clearAll(){
        firstName.clear();
        lastName.clear();
        username.clear();
        password.clear();
        phoneNumber.clear();
        eMail.clear();
        street.clear();
        city.clear();
        number.clear();
        postCode.clear();
        country.clear();
    }

    @FXML
    void back() {
        clearAll();
        viewHandler.openView(ViewHandler.STAFF_LIST_VIEW, this);
    }

    @FXML
    void confirm() {

        if(lastName.getText().equals("") || firstName.getText().equals("")
                || username.getText().equals("") || (password.getText().equals("") && staff.getValue() == null) || city.getText().equals("")
                || street.getText().equals("") || number.getText().equals("")
                || postCode.getText().equals("") || country.getText().equals("")){

            Alert alert = new ErrorAlert();
            alert.setContentText("Some of the necessary fields are empty");
            alert.show();

            return;
        }

        try {
            if(staff.getValue() != null){

                Staff g = staff.getValue();
                g.setFirstName(firstName.getText());
                g.setLastName(lastName.getText());
                g.setEmail(eMail.getText());
                g.setPhoneNumber(phoneNumber.getText());
                if(!password.getText().equals(""))
                    g.setPassword(password.getText());

                Address address = g.getAddress();
                address.setCity(city.getText());
                address.setStreet(street.getText());
                address.setHouseNumber(number.getText());
                address.setPostalCode(postCode.getText());
                address.setCountry(country.getText());
                g.setAddress(address);

                viewModel.edit(staff.getValue());
                viewModel.setStaff(null);
                Alert alert = new SuccessAlert("New Staff member successfully edited");
                alert.show();
                ((StaffListViewController) previousView).getViewModel().setCurrentStaff(null);
                viewModel.setStaff(null);
            }else{

                Address address = new Address(city.getText(), street.getText(), number.getText(), postCode.getText(), country.getText());
                this.address.setValue(address);

                Staff staff = null;
                switch(privilege.getValue()){
                    case "receptionist" -> {
                        staff = new Receptionist(
                            username.getText(), firstName.getText(), lastName.getText(), password.getText(),
                            eMail.getText(), phoneNumber.getText(), address);
                    }case "manager" -> {
                        staff = new Manager(
                            username.getText(), firstName.getText(), lastName.getText(), password.getText(),
                            eMail.getText(), phoneNumber.getText(), address);
                    }
                }

                try
                {
                    viewModel.add(staff);
                    Alert alert = new SuccessAlert("New Staff member successfully added");
                    alert.show();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                    Alert alert = new DatabaseErrorAlert();
                    alert.show();
                }

            }
            clearAll();
            viewHandler.openView(ViewHandler.STAFF_LIST_VIEW, this);
        } catch (SQLException e) {

            if(Integer.parseInt(e.getSQLState()) == ViewHandler.UNIQUE_SQL_ERROR_CODE){
                Alert alert = new ErrorAlert("Staff with this passport number already exists");
                alert.show();
                return;
            }

            Alert alert = new DatabaseErrorAlert();
            alert.show();
            e.printStackTrace();
        }

    }

    public void setPreviousView(Controller previousView){
        this.previousView = previousView;
    }

}
