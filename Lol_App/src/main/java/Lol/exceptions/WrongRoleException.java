package Lol.exceptions;

public class WrongRoleException extends Exception{
    private String username;

    public WrongRoleException(String username) {
        super(String.format("Wrong role", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
