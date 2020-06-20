package Lol.controllers;

import Lol.services.ModeratorServices;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class RegistrationControllerTest extends JFrame {
    public static final String Test_User = "testUser";
    public static final String TEST_PASSWORD = "testPassword";
    private RegistrationController controller;


    @BeforeClass
    public static void setupClass() throws Exception {
        ModeratorServices.loadUsersFromFile();
    }

    @Before
    public void setup() throws Exception {
        ModeratorServices.loadUsersFromFile();
        controller = new RegistrationController();
        controller.username_test = new JTextField();
        controller.password_test = new JPasswordField();
        controller.role = new JComboBox();

        controller.password_test.setText(TEST_PASSWORD);
        controller.username_test.setText(Test_User);
    }

    @Test
    public void testHandleAddUserActionCode() {
        ModeratorServices.setFileNull();
        controller.handleRegisterAction();
        assertEquals(1 , ModeratorServices.getUsers().size());
    }

    @Test
    public void testAddSameUserTwice() {
        controller.handleRegisterAction();
        controller.handleRegisterAction();
    }
}


