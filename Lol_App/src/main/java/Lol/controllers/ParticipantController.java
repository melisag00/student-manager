package Lol.controllers;

import Lol.View.LogInView;
import Lol.services.ParticipantServices;

import java.io.IOException;

public class ParticipantController {
    private LogInView view;
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
}
