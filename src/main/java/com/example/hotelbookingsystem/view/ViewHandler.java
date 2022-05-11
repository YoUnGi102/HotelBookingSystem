package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.viewModel.ViewModelFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.File;

public class ViewHandler {

    // TODO ADD NAME OF YOUR FXML HERE
    // EXAMPLE
    public static final String GUEST_LIST_VIEW = "guest_list_view.fxml";
    public static final String ROOM_LIST_VIEW = "room_list_view.fxml";
    public static final String BOOKING_LIST_VIEW = "booking_list_view.fxml";
    public static final String MANAGE_BOOKING_VIEW = "manage_booking_view.fxml";
    public static final String MENU_VIEW = "menu_view.fxml";

    private Scene currentScene;
    private Stage primaryStage;
    private final ViewFactory viewFactory;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewFactory = new ViewFactory(this, viewModelFactory);
        this.currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        primaryStage.setResizable(false);
        openView(MENU_VIEW, null);
    }

    public void openView(String id, Controller previousView) {
        Region root;
        switch (id) {
            case GUEST_LIST_VIEW -> {
                root = viewFactory.loadGuestListView(previousView);
                primaryStage.setTitle("Guest List");
            }
            case ROOM_LIST_VIEW -> {
                root = viewFactory.loadRoomListView(previousView);
                primaryStage.setTitle("Room List");
            }
            case BOOKING_LIST_VIEW -> {
                root = viewFactory.loadBookingListView(previousView);
                primaryStage.setTitle("Room Booking List");
            }
            case MANAGE_BOOKING_VIEW -> {
                root = viewFactory.loadManageBookingView(previousView);
                primaryStage.setTitle("Manage Booking");
            }
            case MENU_VIEW -> {
                root = viewFactory.loadMenuView(previousView);
                primaryStage.setTitle("Menu");
            }
            default -> throw new IllegalArgumentException("Unknown view: " + id);
        }
        currentScene.setRoot(root);
        primaryStage.getIcons().add(new Image("file:src\\main\\resources\\img\\icon_gradient.png"));
        primaryStage.setScene(currentScene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public void closeView() {
        primaryStage.close();
    }

}
