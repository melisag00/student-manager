package Lol.Tournaments;

import java.util.Objects;

public class Tournament_details {
    private String name;
    private String details;

    public Tournament_details() {
    }

    public Tournament_details(String name, String details) {
        this.name = name;
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tournament_details)) return false;
        Tournament_details that = (Tournament_details) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, details);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Tournament_details{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
