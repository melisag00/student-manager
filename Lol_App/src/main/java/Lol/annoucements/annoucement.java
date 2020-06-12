package Lol.annoucements;

import java.util.Objects;

public class annoucement {
    private String annouce;

    public annoucement(String annouce) {
        this.annouce = annouce;
    }

    public annoucement() {
    }

    public String getAnnouce() {
        return annouce;
    }

    public void setAnnouce(String annouce) {
        this.annouce = annouce;
    }

    @Override
    public String toString() {
        return "annoucement{" +
                "annouce='" + annouce + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof annoucement)) return false;
        annoucement that = (annoucement) o;
        return Objects.equals(annouce, that.annouce);
    }

    @Override
    public int hashCode() {
        return Objects.hash(annouce);
    }
}
