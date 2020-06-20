package Lol.services;

import Lol.Messages.messages;
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

public class MessagesServices {
    private static List<messages> msg;
    private static final Path Message_Path = FileSystemService.getPathToFile("congif","messages.json");

    public static List<messages> getMsg() {
        return msg;
    }

    public static Path getMessage_Path() {
        return Message_Path;
    }

    public static void loadMessagesFromFile() throws IOException {

        if (!Files.exists(Message_Path)) {
            FileUtils.copyURLToFile(MessagesServices.class.getClassLoader().getResource("messages.json"), Message_Path.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        msg = objectMapper.readValue(Message_Path.toFile(), new TypeReference<List<messages>>() {
        });
    }

    public static void addMessage(String from, String to, String message) throws IOException {
        loadMessagesFromFile();
        msg.add(new messages(from,to,message));
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(Message_Path.toFile(), msg);
        } catch (IOException e) {
            throw new CouldNotWriteTournamentException();
        }
    }

    public static void deleteMessage(String from, String to, String message) throws IOException{
        loadMessagesFromFile();
        List<messages> messages_delete = new ArrayList<messages>();
        for(messages m:msg)
        {
            if((!Objects.equals(from,m.getFrom())) && (!Objects.equals(to,m.getTo())) && (!Objects.equals(message,m.getMess())))
            {
                messages_delete.add(new messages(m.getFrom(),m.getTo(),m.getMess()));
            }
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(Message_Path.toFile(), messages_delete);
        } catch (IOException e) {
            throw new CouldNotWriteTournamentException();
        }

    }

    public static void main(String[] args) throws IOException {
        loadMessagesFromFile();
        System.out.println(msg);
    }
}
