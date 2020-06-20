package Lol.services;


import Lol.Moderator.moderator;
import Lol.annoucements.annoucement;
import Lol.exceptions.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModeratorServices {

    private static List<moderator> users;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");
    private static List<moderator> users_void = new ArrayList<moderator>();
    private static List<annoucement> ann;
    private static List<annoucement> ann_void;
    private static final Path Annouce_Path = FileSystemService.getPathToFile("congif","annouce.json");
    public static Path getPath()
    {
        return USERS_PATH;
    }

    public static Path getUsersPath() {
        return USERS_PATH;
    }

    public static List<annoucement> getAnn() {
        return ann;
    }

    public static Path getAnnouce_Path() {
        return Annouce_Path;
    }

    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(ModeratorServices.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<moderator>>() {
        });
    }


    public static void loadAnnoucementsFromFile() throws IOException{
        if(!Files.exists(Annouce_Path)){
            FileUtils.copyURLToFile(ModeratorServices.class.getClassLoader().getResource("annouce.json"),Annouce_Path.toFile());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ann = objectMapper.readValue(Annouce_Path.toFile(), new TypeReference<List<annoucement>>() {
        });
    }

    public static void addUser(String username, String password, String role) throws AccAlreadyExistException{
        checkUserDoesNotAlreadyExist(username);
        users.add(new moderator(username, password, role));
        persistUsers();
    }

    public static void checkUserDoesNotAlreadyExist(String username) throws AccAlreadyExistException {
        for (moderator user : users) {
            if (Objects.equals(username, user.getUsername()))
                throw new AccAlreadyExistException(username);
        }
    }

   public static void checkUser(String username, String password) throws UsernameOrPasswordIncorrect, IOException {
        checkPassAndAcc(username,password);
   }
    public static void checkPassAndAcc(String username, String password) throws UsernameOrPasswordIncorrect, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<moderator>>() {
        });
        int ok=0;
     for(moderator user : users) {
         if(Objects.equals(username, user.getUsername()) && Objects.equals(password, user.getPassword()))
             ok = 1;
     }
     if(ok == 0)
         throw new UsernameOrPasswordIncorrect(username);

    }
    public static void delete_annoucement(String annoucement)
    {
        List<annoucement> ann_delete = new ArrayList<annoucement>();
        for(annoucement a:ann)
        {
            if(!Objects.equals(a.getAnnouce(),annoucement))
            {
                ann_delete.add(new annoucement(a.getAnnouce()));
            }
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(Annouce_Path.toFile(), ann_delete);
        } catch (IOException e) {
            throw new CouldNotWriteTournamentException();
        }

    }

    public static void checkR(String username, String role) throws IOException, WrongRoleException {
        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<moderator>>() {
        });
        int ok=0;
        for(moderator user:users)
        {
            if(Objects.equals(username,user.getUsername()) && Objects.equals(role,user.getRole()))
                ok = 1;
        }
        if(ok==0)
            throw new WrongRoleException(username);

    }



    private static void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }
    public static int NumberOfPlayers() throws IOException {
        int count=0;
        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<moderator>>() {
        });
        for(moderator user:users)
        {
            if(Objects.equals("Customer",user.getRole()))
                count++;
        }
        return count;
    }

    public static void addAnnoucement(String annouce) throws IOException, NoAnnoucementException {
        loadAnnoucementsFromFile();
            try {
                if (annouce == "")
                    throw new NoAnnoucementException(annouce);
            }
            catch(NoAnnoucementException e) {
                throw new NoAnnoucementException(annouce);
            }
                    ann.add(new annoucement(annouce));
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.writerWithDefaultPrettyPrinter().writeValue(Annouce_Path.toFile(), ann);
                    } catch (IOException e) {
                        throw new CouldNotWriteTournamentException();
                    }
            }
            public static void setFileNull()
            {
              users = new ArrayList<moderator>();
            }



    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
    public static List<moderator> getUsers() {
        return users;
    }

    public static void main(String[] args) throws IOException {
        loadUsersFromFile();
        System.out.println(users);
        setFileNull();
        loadUsersFromFile();
        System.out.println(users);
    }


    }
