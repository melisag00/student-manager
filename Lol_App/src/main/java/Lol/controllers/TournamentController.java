package Lol.controllers;

import Lol.View.LogInView;
import Lol.services.TournamentServices;
import Lol.services.Tournament_detailsServices;

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

    public boolean deleteTournament(String name) throws IOException {
        TournamentServices.delete(name);
        return true;
    }

    public boolean add_details(String name, String details) throws IOException {
        Tournament_detailsServices.add_details(name,details);
        return true;
    }

}