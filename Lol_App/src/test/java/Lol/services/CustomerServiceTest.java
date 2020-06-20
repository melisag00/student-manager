package Lol.services;

import org.junit.Test;

import java.nio.file.Files;

import static org.junit.Assert.*;

public class CustomerServiceTest {
    @Test
    public void testCopyDefaultFileIfNotExists() throws Exception{
        CustomerServices.loadUsersFromFile();
        assertTrue(Files.exists(ModeratorServices.getPath()));
    }

    @Test
    public void testLoadUsersFromFile() throws Exception{
        CustomerServices.loadUsersFromFile();
        assertNotNull(CustomerServices.getUsers());
    }

    @Test
    public void addOneCustomer() throws Exception{
        CustomerServices.loadUsersFromFile();
        int i = CustomerServices.getUsers().size();
        CustomerServices.add("Legion","Top","Diamond","Jungle");
        CustomerServices.loadUsersFromFile();
        assertEquals(i+1,CustomerServices.getUsers().size());
        CustomerServices.delete_customer("Legion");
    }
    @Test
    public void deleteCustomer() throws Exception{
        CustomerServices.loadUsersFromFile();
        int i = CustomerServices.getUsers().size();
        CustomerServices.add("Legion","Top","Platinum","Jungle");
        CustomerServices.delete_customer("Legion");
        CustomerServices.loadUsersFromFile();
        assertEquals(i,CustomerServices.getUsers().size());
    }
}
