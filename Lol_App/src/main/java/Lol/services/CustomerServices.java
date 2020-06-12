package Lol.services;

import Lol.Customer.customer;
import Lol.exceptions.CouldNotWriteUsersException;
import Lol.exceptions.NoPartnerException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class CustomerServices {
    private static List<customer> users;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "customers.json");

    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(CustomerServices.class.getClassLoader().getResource("customers.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<customer>>() {
        });
    }
    public static void add(String username,String customer_role, String rank, String partner_role) throws IOException {
        loadUsersFromFile();
        users.add(new customer(username,customer_role,rank,partner_role));
        persistUsers();
    }
    private static void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    public static void Partner(String partner_role) throws IOException, NoPartnerException {
      loadUsersFromFile();
        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<customer>>() {
        });
         int ok = 0;
        for (customer user : users) {
            if (Objects.equals(partner_role,user.getCustomer_role()))
                ok=1;
        }
        if(ok==0)
            throw new NoPartnerException(partner_role);





    }

    public static void main(String[] args) throws IOException {
        loadUsersFromFile();
        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<customer>>() {
        });
        System.out.println(users);
    }



}
