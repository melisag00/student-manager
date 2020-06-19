package Lol.controllers;

import Lol.View.LogInView;
import Lol.exceptions.NoAnnoucementException;
import Lol.exceptions.UsernameOrPasswordIncorrect;
import Lol.exceptions.WrongRoleException;
import Lol.services.CustomerServices;
import Lol.services.ModeratorServices;
import Lol.exceptions.NoPartnerException;
import java.io.IOException;


public class LogInController {
    private LogInView view;

    public LogInController(LogInView view) {
        this.view = view;
    }


    public boolean checkAcc(String username, String password) {
        try {
            ModeratorServices.checkUser(username,password);
            return true;
        } catch (UsernameOrPasswordIncorrect | IOException e) {
            return false;
        }
    }

    public boolean checkRole(String username, String role) {

        try {
            ModeratorServices.checkR(username,role);
            return true;
        }catch(WrongRoleException | IOException e)
        {
            return false;
        }

    }

    public boolean add_customer(String username, String customer_role, String rank, String partner_role) throws IOException {

        try {
         CustomerServices.add(username,customer_role,rank,partner_role);
                return true;
        } catch (IOException e) {
            return false;
        }

    }

    public boolean getPartner(String partner_role) throws NoPartnerException, IOException {
        try {
            CustomerServices.Partner(partner_role);
        }catch(NoPartnerException e) {
            return false;
        }
        return true;
        }

        public int getNumberOfPlayers() throws IOException {
           int number = ModeratorServices.NumberOfPlayers();

           return number;
        }

        public boolean putAnnoucement(String annouce) throws IOException, NoAnnoucementException {
            try {
                ModeratorServices.addAnnoucement(annouce);
            }
            catch(NoAnnoucementException e)
            {
                return false;
            }
            return true;
        }




}
