package Lol.services;

import Lol.Tournaments.Tournament;
import Lol.exceptions.CouldNotWriteTournamentException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class TournamentServices {
    private static List<Tournament> tour;


    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "tournament.json");
    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(CustomerServices.class.getClassLoader().getResource("tournament.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        tour = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<Tournament>>() {
        });
    }



     public static void add(String name, String date) throws IOException {
        loadUsersFromFile();
        tour.add(new Tournament(name, date));
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), tour);
        } catch (IOException e) {
            throw new CouldNotWriteTournamentException();
        }
    }
   public static void delete(String name) throws IOException {
       loadUsersFromFile();
       for(Tournament t:tour)
       {
           if(Objects.equals(name,t.getName()))
               tour.remove(t.getName());
       }
       System.out.println(tour);
   }



}
