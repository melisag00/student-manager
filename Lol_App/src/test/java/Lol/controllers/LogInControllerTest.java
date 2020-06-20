package Lol.controllers;

import Lol.exceptions.AccAlreadyExistException;
import Lol.services.ModeratorServices;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Files;

import static org.junit.Assert.*;

public class LogInControllerTest {
    private static final String test_username = "Test-username";
    private static final String test_password = "Test-password";
    private LogInController controller;


    @BeforeClass
    public static void setupClass() throws Exception {
        ModeratorServices.loadUsersFromFile();
    }

    @Before
    public void setup() throws Exception {
        ModeratorServices.loadUsersFromFile();
        controller = new LogInController();
        controller.setName_test(test_username);
        controller.setPass_test(test_password);
    }
    @Test
    public void testCopyDefaultFileIfNotExists() throws Exception{
        ModeratorServices.loadUsersFromFile();
        assertTrue(Files.exists(ModeratorServices.getUsersPath()));
    }

    @Test
    public void testLoadParticipantFromFile() throws Exception{
        ModeratorServices.loadUsersFromFile();
        assertNotNull(ModeratorServices.getUsersPath());
    }

    @Test
    public void testCopyDefaultFileIfNotExists2() throws Exception{
        ModeratorServices.loadAnnoucementsFromFile();
        assertTrue(Files.exists(ModeratorServices.getPath()));
    }

    @Test
    public void testLoadParticipantFromFile2() throws Exception{
        ModeratorServices.loadAnnoucementsFromFile();
        assertNotNull(ModeratorServices.getPath());
    }
    @Test(expected = AccAlreadyExistException.class)
    public void testHandleLogInAction() throws Exception{
        ModeratorServices.loadUsersFromFile();
        int i = ModeratorServices.getUsers().size();
        ModeratorServices.addUser(test_username,test_password,"customer");
        controller.handleRegisterAction();
    }

    @Test
    public void PutAnnoucementTest() throws Exception{
        ModeratorServices.loadAnnoucementsFromFile();
        int i = ModeratorServices.getAnn().size();
        ModeratorServices.addAnnoucement("TESTARE");
        assertEquals(i+1,ModeratorServices.getAnn().size());
        ModeratorServices.delete_annoucement("Testare");
    }
}

