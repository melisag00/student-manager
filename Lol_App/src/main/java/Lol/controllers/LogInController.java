package Lol.controllers;

import Lol.View.LogInView;
import Lol.exceptions.*;
import Lol.services.CustomerServices;
import Lol.services.ModeratorServices;

import java.io.IOException;


public class LogInController {
    private LogInView view;
    private String name_test;
    private String pass_test;
    private String role_test;

    public String getName_test() {
        return name_test;
    }

    public void setName_test(String name_test) {
        this.name_test = name_test;
    }

    public String getPass_test() {
        return pass_test;
    }

    public void setPass_test(String pass_test) {
        this.pass_test = pass_test;
    }

    public String getRole_test() {
        return role_test;
    }

    public void setRole_test(String role_test) {
        this.role_test = role_test;
    }

    public LogInController() {
    }

    public LogInController(LogInView view) {
        this.view = view;
    }
    public void handleRegisterAction() {
        try {
            ModeratorServices.checkPassAndAcc(name_test,pass_test);
        } catch ( UsernameOrPasswordIncorrect | IOException e) {
            System.out.println("You cant create the acc");
        }
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
