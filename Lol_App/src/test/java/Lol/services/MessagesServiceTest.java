package Lol.services;

import org.junit.Test;

import java.nio.file.Files;

import static org.junit.Assert.*;

public class MessagesServiceTest {
    @Test
    public void testCopyDefaultFileIfNotExists() throws Exception{
        MessagesServices.loadMessagesFromFile();
        assertTrue(Files.exists(MessagesServices.getMessage_Path()));
    }

    @Test
    public void testLoadParticipantFromFile() throws Exception{
        MessagesServices.loadMessagesFromFile();
        assertNotNull(MessagesServices.getMessage_Path());
    }

    @Test
    public void testAddMessage() throws Exception{
        MessagesServices.loadMessagesFromFile();
        int i = MessagesServices.getMsg().size();
        MessagesServices.addMessage("Test-1","Test-2","testare1");
        assertEquals(i + 1,MessagesServices.getMsg().size());
        MessagesServices.deleteMessage("Test-1","Test-2","testare1");
    }
    @Test
    public void testAdd2Messages() throws Exception{
        MessagesServices.loadMessagesFromFile();
        int i = MessagesServices.getMsg().size();
        MessagesServices.addMessage("Test-1a","Test-2a","testare1a");
        MessagesServices.addMessage("Test-1b","Test-2b","testare1b");
        assertEquals(i+2,MessagesServices.getMsg().size());
        MessagesServices.deleteMessage("Test-1a","Test-2a","testare1a");
        MessagesServices.deleteMessage("Test-1b","Test-2a","testare1b");
    }

    @Test
    public void testDeleteMessage() throws Exception{
        MessagesServices.loadMessagesFromFile();
        int i = MessagesServices.getMsg().size();
        MessagesServices.addMessage("Test-d1","Test-d2","testaredelete");
        MessagesServices.deleteMessage("Test-d1","Test-d2","testaredelete");
        MessagesServices.loadMessagesFromFile();
        assertEquals(i,MessagesServices.getMsg().size());
    }
}
