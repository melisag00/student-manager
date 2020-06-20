package Lol.services;

import Lol.Moderator.moderator;
import Lol.exceptions.AccAlreadyExistException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;

public class ModeratorServiceTest {
    @Test
    public void testCopyDefaultFileIfNotExists() throws Exception{
        ModeratorServices.loadUsersFromFile();
        assertTrue(Files.exists(ModeratorServices.getPath()));
        ModeratorServices.setFileNull();
    }

@Test
public void testLoadUsersFromFile() throws Exception{
    ModeratorServices.loadUsersFromFile();
    assertNotNull(ModeratorServices.getUsers());
    ModeratorServices.setFileNull();
    assertEquals(0,ModeratorServices.getUsers().size());
}
@Test
    public void testAddOneUser() throws  Exception {
    ModeratorServices.loadUsersFromFile();
    ModeratorServices.setFileNull();
    ModeratorServices.addUser("test","pass","doesntmatter");
    assertNotNull(ModeratorServices.getUsers());
    assertEquals(1,ModeratorServices.getUsers().size());
}
@Test
    public void testAddTwoUsers() throws Exception{
        ModeratorServices.loadUsersFromFile();
        ModeratorServices.setFileNull();
        ModeratorServices.addUser("test1","pass1","againdoesntmatter");
        ModeratorServices.addUser("test2","pass2","DOESNTMATTER");
        assertNotNull(ModeratorServices.getUsers());
        assertEquals(2,ModeratorServices.getUsers().size());
}

@Test (expected = AccAlreadyExistException.class)
    public void testAddUserAlreadyExists() throws Exception{
        ModeratorServices.loadUsersFromFile();
        ModeratorServices.addUser("test1","pass1","blablabla");
        assertNotNull(ModeratorServices.getUsers());
        ModeratorServices.checkUserDoesNotAlreadyExist("test1");

}
    @Test
    public void testAddOneUserIsPersisted() throws Exception {
        ModeratorServices.loadUsersFromFile();
        ModeratorServices.setFileNull();
        ModeratorServices.addUser("test", "testPass", "432");
        ObjectMapper objectMapper = new ObjectMapper();
        List<moderator> users = objectMapper.readValue(ModeratorServices.getPath().toFile(), new com.fasterxml.jackson.core.type.TypeReference<List<moderator>>() {
        });
        assertEquals(1 , users.size());
    }

    @Test
    public void testAddTwoUserArePersisted() throws Exception {
        ModeratorServices.loadUsersFromFile();
        ModeratorServices.setFileNull();
        ModeratorServices.addUser("test1", "testPass1", "123");
        ModeratorServices.addUser("test2", "testPass2", "432");
        List<moderator> users = new ObjectMapper().readValue(ModeratorServices.getPath().toFile(), new com.fasterxml.jackson.core.type.TypeReference<List<moderator>>() {
        });
        assertNotNull(users);
        assertEquals(2 , users.size());
    }

}
