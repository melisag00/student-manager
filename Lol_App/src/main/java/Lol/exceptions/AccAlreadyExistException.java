package Lol.exceptions;


public class AccAlreadyExistException extends Exception {

    private String username;

    public AccAlreadyExistException(String username) {
        super(String.format("An account with the username %s already exists!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

