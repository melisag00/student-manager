package Lol.controllers;


import Lol.View.RegistrationView;
import Lol.exceptions.AccAlreadyExistException;
import Lol.services.ModeratorServices;

public class RegistrationController {
    private RegistrationView view;

    public RegistrationController(RegistrationView view) {
        this.view = view;
    }

    public boolean checkAvailability(String username, String password, String role) {
        try {
           ModeratorServices.addUser(username, password, role);
            return true;
        } catch (AccAlreadyExistException e) {
            return false;
        }
    }
}

