package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;

public class ViewModelFactory {

    // TODO (Add every View Model as you create it)
    // EXAMPLE
    private final GuestListViewModel guestListViewModel;

    public ViewModelFactory(Model model) {
        // EXAMPLE
        this.guestListViewModel = new GuestListViewModel(model);
    }

    // EXAMPLE
    public GuestListViewModel getGuestListViewModel(){
        return guestListViewModel;
    }

}
