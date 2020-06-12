package Lol;

import Lol.View.LogInView;
import Lol.services.ModeratorServices;

public class Main {
    public static void main(String[] args) throws Exception {
        ModeratorServices.loadUsersFromFile();

        LogInView view = new LogInView();
        view.setVisible(true);
    }
}
