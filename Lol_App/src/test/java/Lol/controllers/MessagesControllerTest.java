package Lol.controllers;

import Lol.services.MessagesServices;
import Lol.services.ModeratorServices;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MessagesControllerTest extends JFrame {
    public static final String from_mess_test = "Acc1";
    public static final String to_mess_test = "Acc2";
    public static final String message_mess_test = "Hello";
    private MessagesController controller;
    @BeforeClass
    public static void setupClass() throws Exception {
        MessagesServices.loadMessagesFromFile();
    }

    @Before
    public void setup() throws Exception {
        ModeratorServices.loadUsersFromFile();
        controller = new MessagesController();
        controller.setFrom_test(from_mess_test);
        controller.setTo_test(to_mess_test);
        controller.setMessage_test(message_mess_test);
    }

    @Test
    public void testHandleAddUserActionCode() throws IOException {
        int i = MessagesServices.getMsg().size();
        controller.handleMessageAction();
        assertEquals(i + 1, MessagesServices.getMsg().size());
        MessagesServices.deleteMessage("Acc1","Acc2","Hello");
        MessagesServices.loadMessagesFromFile();
    }
}
