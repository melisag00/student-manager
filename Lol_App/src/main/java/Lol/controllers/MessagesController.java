package Lol.controllers;

import Lol.View.LogInView;
import Lol.services.MessagesServices;

import java.io.IOException;

public class MessagesController {
    private LogInView view;
    private String from_test;
    private String to_test;
    private String message_test;

    public String getFrom_test() {
        return from_test;
    }

    public void setFrom_test(String from_test) {
        this.from_test = from_test;
    }

    public String getTo_test() {
        return to_test;
    }

    public void setTo_test(String to_test) {
        this.to_test = to_test;
    }

    public String getMessage_test() {
        return message_test;
    }

    public void setMessage_test(String message_test) {
        this.message_test = message_test;
    }

    public MessagesController() {
    }

    public MessagesController(LogInView view) {
        this.view = view;
    }
     public boolean sendMessage(String from, String to, String message) throws IOException {
         MessagesServices.addMessage(from,to,message);
         return true;
     }

     public void handleMessageAction() throws IOException {
         MessagesServices.addMessage(from_test,to_test,message_test);
     }

}
