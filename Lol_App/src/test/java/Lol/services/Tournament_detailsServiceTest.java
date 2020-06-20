package Lol.services;

import Lol.controllers.TournamentController;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class Tournament_detailsServiceTest {
    private static final String name_tournament_details_test = "Tournament1_test_details";
    private static final String details_tournament_details_test = "Details1_test_details";
    private static TournamentController controller_tournament_details_test;
    @BeforeClass
    public static void setupClass() throws IOException {
        TournamentServices.loadUsersFromFile();
    }

    @Before
    public void setup() throws Exception
    {
        TournamentServices.loadUsersFromFile();
        controller_tournament_details_test = new TournamentController();
    }
    @Test
    public void testCopyDefaultFileIfNotExists() throws Exception {
        Tournament_detailsServices.loadDetailsFromFile();
        assertTrue(Files.exists(Tournament_detailsServices.getTourPath()));
    }
        @Test
    public void testLoadDetailsFromFile() throws Exception{
        Tournament_detailsServices.loadDetailsFromFile();
        assertNotNull(Tournament_detailsServices.getTour_details());
    }



    @Test
    public void addDetailsTournaments() throws Exception{
        Tournament_detailsServices.loadDetailsFromFile();
        int i = Tournament_detailsServices.getTour_details().size();
        TournamentServices.add("Details1","nodate");
        Tournament_detailsServices.add_details("Details1","abc");
        assertEquals(1+i,Tournament_detailsServices.getTour_details().size());

        Tournament_detailsServices.delete_details("Details1");
        Tournament_detailsServices.loadDetailsFromFile();

        TournamentServices.delete("Details1");
        TournamentServices.loadUsersFromFile();

    }

    @Test
    public void DeleteDetails() throws Exception{
        Tournament_detailsServices.loadDetailsFromFile();
        int i = Tournament_detailsServices.getTour_details().size();
        TournamentServices.add("Test_delete","nodate");
        Tournament_detailsServices.add_details("Test_delete","Delete-details");
        Tournament_detailsServices.delete_details("Test_delete");
        Tournament_detailsServices.loadDetailsFromFile();
        assertEquals(i,Tournament_detailsServices.getTour_details().size());
        TournamentServices.delete("Test_delete");
        TournamentServices.loadUsersFromFile();
        Tournament_detailsServices.loadDetailsFromFile();
    }
}
