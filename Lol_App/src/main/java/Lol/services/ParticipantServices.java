package Lol.services;

import Lol.Participant.participant;
import Lol.exceptions.CouldNotWriteTournamentException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ParticipantServices {
    private static List<participant> tour_part;
    private static final Path participant_path = FileSystemService.getPathToFile("config", "participanti_tournament.json");
    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(participant_path)) {
            FileUtils.copyURLToFile(CustomerServices.class.getClassLoader().getResource("participanti_tournament.json"), participant_path.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        tour_part = objectMapper.readValue(participant_path.toFile(), new TypeReference<List<participant>>() {
        });
    }
    public static void addPart(String name, String username) throws IOException {
        loadUsersFromFile();
        tour_part.add(new participant(name,username));
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(participant_path.toFile(), tour_part);
        } catch (IOException e) {
            throw new CouldNotWriteTournamentException();
        }
    }

    public static void main(String[] args) throws IOException {
        loadUsersFromFile();
        System.out.println(tour_part);

    }
}
