package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.viewModel.RoomListViewModel;
import javafx.event.ActionEvent;
import javafx.scene.layout.Region;

public class RoomListViewController {

    private ViewHandler viewHandler;
    private RoomListViewModel viewModel;
    private Region root;

    public void init(ViewHandler viewHandler, RoomListViewModel viewModel, Region root){
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
    }

    public Region getRoot() {
        return root;
    }

    public void search(ActionEvent actionEvent) {
    }

    public void add(ActionEvent actionEvent) {
    }

    public void edit(ActionEvent actionEvent) {
    }

    public void remove(ActionEvent actionEvent) {
    }

    public void book(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }
}
