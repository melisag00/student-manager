package Lol.exceptions;

public class NoAnnoucementException extends Exception {
    private String ann;

    public NoAnnoucementException(String ann) {
        super(String.format("No annouce!", ann));
        this.ann= ann;
    }

    public String getUsername() {
        return ann;
    }
}
