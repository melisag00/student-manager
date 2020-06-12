package Lol.Participant;

public class participant {
    private String tournament_name;
    private String username;


    public participant() {
    }

    public participant(String tournament_name, String username) {
        this.tournament_name = tournament_name;
        this.username = username;
    }

    @Override
    public String toString() {
        return "participant{" +
                "tournament_name='" + tournament_name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getTournament_name() {
        return tournament_name;
    }

    public void setTournament_name(String tournament_name) {
        this.tournament_name = tournament_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
