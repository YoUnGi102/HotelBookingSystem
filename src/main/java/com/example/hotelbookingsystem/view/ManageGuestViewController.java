package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.viewModel.ManageGuestViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
        if(usePrevious.isSelected()){
            street.setText(address.getValue().getStreet());
            city.setText(address.getValue().getCity());
            number.setText(address.getValue().getHouseNumber());
            postCode.setText(address.getValue().getPostalCode());
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
                g.setAddress(address);

                viewModel.edit(guest.getValue());
                viewModel.setGuest(null);
                ((GuestListViewController)previousView).getViewModel().setCurrentGuest(null);
            }else{
                Address address = new Address(city.getText(), street.getText(), number.getText(), postCode.getText());
                this.address.setValue(address);
                Guest guest = new Guest(
                        firstName.getText(), lastName.getText(), address, phoneNumber.getText(), eMail.getText(), passNr.getText());
                viewModel.add(guest);
            }
            clearAll();
            viewHandler.openView(ViewHandler.GUEST_LIST_VIEW, this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}