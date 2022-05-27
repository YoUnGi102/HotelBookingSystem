package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.view.alert.DatabaseErrorAlert;
import com.example.hotelbookingsystem.view.alert.ErrorAlert;
import com.example.hotelbookingsystem.viewModel.ManageGuestViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.sql.SQLException;

public class ManageGuestViewController implements Controller {

    @FXML
    private TextField firstName, lastName, eMail, phoneNumber, number, street, city, postCode, country, passNr;
    @FXML
    private CheckBox usePrevious;

    private Controller previousView;

    private ObjectProperty<Guest> guest;
    private ObjectProperty<Address> address;

    private Region root;
    private ViewHandler viewHandler;
    private ManageGuestViewModel viewModel;

    public void init(ViewHandler viewHandler, ManageGuestViewModel viewModel, Region root, Controller previousView) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
        this.previousView = previousView;

        address = new SimpleObjectProperty<>();
        viewModel.bindAddressProperty(address);

        guest = new SimpleObjectProperty<>();
        guest.addListener((observableValue, oldVal, newVal) -> {
            if(newVal == null){
                passNr.setEditable(true);
                usePrevious.setVisible(true);
                return;
            }

            usePrevious.setVisible(false);

            Guest g = newVal;
            firstName.setText(g.getFirstName());
            lastName.setText(g.getLastName());
            eMail.setText(g.getEmail());
            phoneNumber.setText(g.getPhoneNumber());
            passNr.setText(g.getPassportNumber());
            passNr.setEditable(false);

            Address a = g.getAddress();
            number.setText(a.getHouseNumber());
            street.setText(a.getStreet());
            city.setText(a.getCity());
            postCode.setText(a.getPostalCode());
            country.setText(a.getCountry());
        });
        viewModel.bindGuestProperty(guest);

    }

    public ManageGuestViewModel getViewModel() {
        return viewModel;
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    void previousAddress() {
        if(usePrevious.isSelected() && address.getValue() != null){
            street.setText(address.getValue().getStreet());
            city.setText(address.getValue().getCity());
            number.setText(address.getValue().getHouseNumber());
            postCode.setText(address.getValue().getPostalCode());
            country.setText(address.getValue().getCountry());
        }else
            clearAddress();
    }

    private void clearAll(){
        clearAddress();
        firstName.clear();
        lastName.clear();
        passNr.clear();
        phoneNumber.clear();
        eMail.clear();
    }
    private void clearAddress(){
        street.clear();
        city.clear();
        number.clear();
        postCode.clear();
        country.clear();
    }

    @FXML
    void back() {
        if (previousView instanceof GuestListViewController)
            viewHandler.openView(ViewHandler.GUEST_LIST_VIEW, this);
        else
            viewHandler.openView(ViewHandler.MANAGE_BOOKING_VIEW, this);
    }

    @FXML
    void confirm() {

        if(lastName.getText().equals("") || firstName.getText().equals("")
            || passNr.getText().equals("" ) || city.getText().equals("")
            || street.getText().equals("") || number.getText().equals("")
            || postCode.getText().equals("") || country.getText().equals("")){

            Alert alert = new ErrorAlert();
            alert.setContentText("Some of the necessary fields are empty");
            alert.show();

            return;
        }

        try {
            if(guest.getValue() != null){

                Guest g = guest.getValue();
                g.setFirstName(firstName.getText());
                g.setLastName(lastName.getText());
                g.setEmail(eMail.getText());
                g.setPhoneNumber(phoneNumber.getText());

                Address address = g.getAddress();
                address.setCity(city.getText());
                address.setStreet(street.getText());
                address.setHouseNumber(number.getText());
                address.setPostalCode(postCode.getText());
                address.setCountry(country.getText());
                g.setAddress(address);

                viewModel.edit(guest.getValue());
                viewModel.setGuest(null);
                ((GuestListViewController)previousView).getViewModel().setCurrentGuest(null);
            }else{
                Address address = new Address(city.getText(), street.getText(), number.getText(), postCode.getText(), country.getText());
                this.address.setValue(address);
                Guest guest = new Guest(
                        firstName.getText(), lastName.getText(), address, phoneNumber.getText(), eMail.getText(), passNr.getText());
                viewModel.add(guest);
            }
            clearAll();
            viewHandler.openView(ViewHandler.GUEST_LIST_VIEW, this);
        } catch (SQLException e) {

            if(Integer.parseInt(e.getSQLState()) == ViewHandler.UNIQUE_SQL_ERROR_CODE){
                Alert alert = new ErrorAlert("Guest with this passport number already exists");
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