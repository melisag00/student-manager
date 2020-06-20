package Lol.controllers;

import Lol.services.TournamentServices;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TournamentControllerTest extends JFrame {
    public static final String Test_tournament = "Tournament_test";
    public static final String Test_date = "Tournament_date";
    private static TournamentController controller_tournament;

    @BeforeClass
    public static void setupClass() throws IOException {
        TournamentServices.loadUsersFromFile();
    }

    @Before
    public void setup() throws Exception
    {
        TournamentServices.loadUsersFromFile();
        controller_tournament = new TournamentController();
    }

    @Test
    public void testAddTournament() throws IOException {
        TournamentServices.loadUsersFromFile();
        int i = TournamentServices.getTour().size();
        TournamentServices.add(Test_tournament,Test_date);
        assertEquals(i+1,TournamentServices.getTour().size());
        TournamentServices.delete(Test_tournament);
    }

    @Test
    public void testAdd2Tournaments() throws IOException{
        TournamentServices.loadUsersFromFile();
        int i = TournamentServices.getTour().size();
        TournamentServices.add("Tournament_test_1","Date_test_1");
        TournamentServices.add("Tournament_test_2","Date_test_2");
        assertEquals(2+i, TournamentServices.getTour().size());
        TournamentServices.delete("Tournament_test_1");
        TournamentServices.delete("Tournament_test_2");
    }

    @Test
    public void testDelete1Tounrmanet() throws IOException{
        TournamentServices.loadUsersFromFile();
        int i = TournamentServices.getTour().size();
        TournamentServices.add("Tournament_test_delete","Nodate");
        TournamentServices.delete("Tournament_test_delete");
        TournamentServices.loadUsersFromFile();
        assertEquals(i,TournamentServices.getTour().size());

    }

}
