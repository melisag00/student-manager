package Lol.controllers;

import Lol.View.LogInView;
import Lol.services.MessagesServices;

import java.io.IOException;

public class MessagesController {
    private LogInView view;

    public MessagesController(LogInView view) {
        this.view = view;
    }
     public boolean sendMessage(String from, String to, String message) throws IOException {
         MessagesServices.addMessage(from,to,message);
         return true;
     }

}
