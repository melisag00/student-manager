package Lol.exceptions;

public class UsernameOrPasswordIncorrect extends Exception{
    private String username;

    public UsernameOrPasswordIncorrect(String username) {
        super(String.format("Acc or password incorrect", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
