package Lol.exceptions;

public class NoPartnerException extends Exception {
    private String username;

    public NoPartnerException(String username) {
        super(String.format("Acc or password incorrect", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
