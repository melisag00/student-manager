package Lol.services;

import Lol.Tournaments.Tournament_details;
import Lol.exceptions.CouldNotWriteTournamentException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Tournament_detailsServices {
    private static List<Tournament_details> tour_details;
    private static final Path TOUR_PATH = FileSystemService.getPathToFile("config", "try_details.json");

    public static void loadDetailsFromFile() throws IOException {

        if (!Files.exists(TOUR_PATH)) {
            FileUtils.copyURLToFile(ParticipantServices.class.getClassLoader().getResource("try_details.json"), TOUR_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        tour_details = objectMapper.readValue(TOUR_PATH.toFile(), new TypeReference<List<Tournament_details>>() {
        });
    }
    public static void add_details(String tournament_name, String details) throws  IOException{
        loadDetailsFromFile();
        tour_details.add(new Tournament_details(tournament_name,details));
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(TOUR_PATH.toFile(), tour_details);
        } catch (IOException e) {
            throw new CouldNotWriteTournamentException();
        }
    }
}
