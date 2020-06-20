package Lol.controllers;

import Lol.services.ModeratorServices;
import Lol.services.ParticipantServices;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ParticipanControllerTest extends JFrame {
    public static final String participant_test = "Participant1";
    public static final String tournament_part = "Tournament-test";
    private ParticipantController controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        ModeratorServices.loadUsersFromFile();
    }

    @Before
    public void setup() throws Exception {
        ModeratorServices.loadUsersFromFile();
        controller = new ParticipantController();
        controller.setTournament_name(tournament_part);
        controller.setUsername(participant_test);
    }

    @Test
    public void testHandleAddUserActionCode() throws IOException {
        controller.handleAddParticipeAction();
        assertEquals(1, ParticipantServices.getTour_part().size());
        ParticipantServices.delete_participants(tournament_part);
    }
}
