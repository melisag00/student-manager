package Lol.controllers;

import Lol.View.LogInView;
import Lol.services.TournamentServices;
import Lol.services.Tournament_detailsServices;

import java.io.IOException;

public class TournamentController{
    private LogInView view;
    private String name_tournament_test;
    private String date_tournament_test;

    public TournamentController(){}
    public TournamentController(LogInView view) {
        this.view = view;
    }

    public String getName_tournament_test() {
        return name_tournament_test;
    }

    public void setName_tournament_test(String name_tournament_test) {
        this.name_tournament_test = name_tournament_test;
    }

    public String getDate_tournament_test() {
        return date_tournament_test;
    }

    public void setDate_tournament_test(String date_tournament_test) {
        this.date_tournament_test = date_tournament_test;
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
