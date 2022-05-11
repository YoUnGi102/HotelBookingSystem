package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.viewModel.GuestTableProperty;
import com.example.hotelbookingsystem.viewModel.GuestListViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class GuestListViewController {

    private ViewHandler viewHandler;
    private GuestListViewModel viewModel;
    private Region root;

    @FXML
    private TextField firstName, lastName, eMail, phoneNumber, passportNumber;

    @FXML
    private TableView<GuestTableProperty> table;

    @FXML
    private TableColumn<GuestTableProperty, String> firstNameCol, lastNameCol, emailCol, phoneNumberCol, addressCol, passportNumberCol;

    public void init(ViewHandler viewHandler, GuestListViewModel viewModel, Region root){
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
<<<<<<< Updated upstream
=======
        
        this.previousView = lastController;

        if(lastController instanceof ManageBookingViewController controller){
            ManageBookingViewModel manageViewModel = controller.getViewModel();
            addBtn.setVisible(false);
            editBtn.setText("Add");
            removeBtn.setText("Confirm");
            ObservableList<GuestTableProperty> guests = FXCollections.observableArrayList();
            editBtn.setOnAction(e->{
                Guest g = table.getSelectionModel().getSelectedItem().getGuest();
                if(g != null)
                    guests.add(new GuestTableProperty(g));
            });
            removeBtn.setOnAction(e -> {
                manageViewModel.setGuests(guests);
                guests.clear();
                viewHandler.openView(ViewHandler.MANAGE_BOOKING_VIEW, controller);
            });
        }
>>>>>>> Stashed changes

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"){});
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        passportNumberCol.setCellValueFactory(new PropertyValueFactory<>("passportNumber"));

        viewModel.bindTableItemsProperty(table.itemsProperty());

    }

    @FXML
    void search(ActionEvent event) {
        // TODO CHECK USER INPUT
        viewModel.searchGuests(firstName.getText(), lastName.getText(), phoneNumber.getText(), passportNumber.getText(), eMail.getText());
    }

    public void add(ActionEvent actionEvent) {

    }

    public void edit(ActionEvent actionEvent) {

    }

    public void remove(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public Region getRoot() {
        return root;
    }
}
