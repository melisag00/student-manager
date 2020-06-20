package Lol.controllers;

import Lol.View.LogInView;
import Lol.services.ParticipantServices;

import java.io.IOException;

public class ParticipantController {
    private LogInView view;
    private String tournament_name;
    private String username;

    public ParticipantController() {
    }

    public ParticipantController(LogInView view) {
        this.view = view;
    }

    public boolean addParticipant(String name, String username) throws IOException {
        try{
            ParticipantServices.addPart(name,username);
            return true;
        } catch(IOException e)
        {
            return false;
        }
    }
    public void handleAddParticipeAction()
    {
        try {
            ParticipantServices.addPart(tournament_name, username);
        } catch ( IOException e) {
            System.out.println("Error");
        }
    }

    public String getTournament_name() {
        return tournament_name;
    }

    public void setTournament_name(String tournament_name) {
        this.tournament_name = tournament_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
