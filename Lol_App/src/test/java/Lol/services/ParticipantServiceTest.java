package Lol.services;

import org.junit.Test;

import java.nio.file.Files;

import static org.junit.Assert.*;

public class ParticipantServiceTest {
    @Test
    public void testCopyDefaultFileIfNotExists() throws Exception{
        ParticipantServices.loadUsersFromFile();
        assertTrue(Files.exists(ParticipantServices.getParticipant_path()));

    }

    @Test
    public void testLoadParticipantFromFile() throws Exception{
        ParticipantServices.loadUsersFromFile();
        assertNotNull(ParticipantServices.getTour_part());

    }

    @Test
    public void addParticipantTest() throws Exception{
        ParticipantServices.loadUsersFromFile();
        int i = ParticipantServices.getTour_part().size();
        TournamentServices.add("Tournament-test1","nodate");
        ParticipantServices.addPart("Tournament-test1","someone random");
        assertEquals(i+1,ParticipantServices.getTour_part().size());
        ParticipantServices.delete_participants("Tournament-test1");
        TournamentServices.delete("Tournament-test1");

    }
    @Test
    public void DeleteParticipantTest() throws Exception{
        ParticipantServices.loadUsersFromFile();
        int i = ParticipantServices.getTour_part().size();
        TournamentServices.add("Tournament-test","nodate");
        ParticipantServices.addPart("Tournament-test","someone random2");
        ParticipantServices.delete_participants("Tournament-test");
        ParticipantServices.loadUsersFromFile();
        assertEquals(i,ParticipantServices.getTour_part().size());
        TournamentServices.delete("Tournament-test");
    }

    @Test
    public void Add2ParticipantsTest() throws Exception{
        ParticipantServices.loadUsersFromFile();
        int i = ParticipantServices.getTour_part().size();
        TournamentServices.add("Tournament-test-2","nodate");
        ParticipantServices.addPart("Tournament-test-2","some1 random");
        ParticipantServices.addPart("Tournament-test-2","some1 random2");
        assertEquals(i+2,ParticipantServices.getTour_part().size());
        ParticipantServices.delete_participants("Tournament-test-2");
        TournamentServices.delete("Tournament-test-2");
    }
}
