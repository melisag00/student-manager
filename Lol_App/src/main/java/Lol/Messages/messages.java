package Lol.Messages;

import java.util.Objects;

public class messages {
    private String from;
    private String to;
    private String mess;

    @Override
    public String toString() {
        return "messages{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", mess='" + mess + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof messages)) return false;
        messages messages = (messages) o;
        return Objects.equals(from, messages.from) &&
                Objects.equals(to, messages.to) &&
                Objects.equals(mess, messages.mess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, mess);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public messages(String from, String to, String mess) {
        this.from = from;
        this.to = to;
        this.mess = mess;
    }

    public messages() {
    }
}
