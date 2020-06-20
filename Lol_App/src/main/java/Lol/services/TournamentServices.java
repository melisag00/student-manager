package Lol.services;

import Lol.Tournaments.Tournament;
import Lol.exceptions.CouldNotWriteTournamentException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TournamentServices {
    private static List<Tournament> tour;


    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "tournaments.json");
    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(CustomerServices.class.getClassLoader().getResource("tournaments.json"), USERS_PATH.toFile());
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
       List<Tournament> tour_delete = new ArrayList<Tournament>();
       for(Tournament t:tour)
       {
           if(!Objects.equals(t.getName(),name))
           {
               tour_delete.add(new Tournament(t.getName(),t.getDate()));
           }
       }
       try {
           ObjectMapper objectMapper = new ObjectMapper();
           objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), tour_delete);
       } catch (IOException e) {
           throw new CouldNotWriteTournamentException();
       }
       Tournament_detailsServices.delete_details(name);
       ParticipantServices.delete_participants(name);
   }

    public static List<Tournament> getTour() {
        return tour;
    }

    public static void main(String[] args) throws IOException {

    }
}
