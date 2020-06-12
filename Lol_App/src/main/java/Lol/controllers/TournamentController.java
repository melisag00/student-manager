package Lol.controllers;

import Lol.View.LogInView;
import Lol.services.TournamentServices;

import java.io.IOException;

public class TournamentController{
    private LogInView view;

    public TournamentController(LogInView view) {
        this.view = view;
    }

    public boolean AddTournament(String name, String date) {
         try {
           TournamentServices.add(name, date);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
