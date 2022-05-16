package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.viewModel.ViewModelFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler {

    public static final int UNIQUE_SQL_ERROR_CODE = 23505;

    public static final Image ICON = new Image("file:src\\main\\resources\\img\\icon_gradient.png");

    // TODO ADD NAME OF YOUR FXML HERE
    // EXAMPLE
    public static final String GUEST_LIST_VIEW = "guest_list_view.fxml";
    public static final String ROOM_LIST_VIEW = "room_list_view.fxml";
    public static final String BOOKING_LIST_VIEW = "booking_list_view.fxml";
    public static final String MANAGE_BOOKING_VIEW = "manage_booking_view.fxml";
    public static final String MANAGE_GUEST_VIEW = "manage_guest_view.fxml";
    public static final String MANAGE_ROOM_VIEW = "manage_room_view.fxml";
    public static final String MENU_VIEW = "menu_view.fxml";
    public static final String LOGIN_VIEW = "login_view.fxml";

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
        openView(LOGIN_VIEW, null);
    }

    public void openView(String id, Controller previousView) {
        Region root;

        switch(id) {
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
            case MANAGE_GUEST_VIEW -> {
                root = viewFactory.loadManageGuestView(previousView);
                primaryStage.setTitle("Manage Guest");
            }case LOGIN_VIEW -> {
                root = viewFactory.loadLoginView(previousView);
                primaryStage.setTitle("Log In");
            }case MANAGE_ROOM_VIEW -> {
                root = viewFactory.loadManageRoomView(previousView);
                primaryStage.setTitle("Manage Room");
            }
            default -> throw new IllegalArgumentException("Unknown view: " + id);
        }
        currentScene.setRoot(root);
        primaryStage.getIcons().add(ICON);
        primaryStage.setScene(currentScene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public void closeView() {
        primaryStage.close();
    }

}
