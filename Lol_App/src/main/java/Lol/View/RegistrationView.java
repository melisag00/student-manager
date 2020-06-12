package Lol.View;

import Lol.controllers.RegistrationController;
import Lol.services.ModeratorServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationView extends JFrame {
    private JButton btnRegister;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private RegistrationController controller;
    private JButton btnModerator;
    private JButton btnCustomer;
    private static String password = "password";



    public RegistrationView() {
        controller = new RegistrationController(this);
        final JFrame frame1 = new JFrame("Lol-App Registration");
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
        frame1.setSize(500, 500);

       JPanel pan = new JPanel(new GridBagLayout());
        frame1.getContentPane().add(pan,BorderLayout.NORTH);
        GridBagConstraints a = new GridBagConstraints();
       frame1.add(pan);

        JLabel lblText = new JLabel("Choose your option, register as a Moderator/Customer or back to log in menu");
        lblText.setBounds(170,100,300,50);
        a.gridx = 2;
        a.gridy = 0;
        a.insets = new Insets(10,10,10,10);
        pan.add(lblText,a);

        JButton log = new JButton("Back to log in");
        log.setBounds(170,100,300,50);
        a.gridx = 2;
        a.gridy = 3;
        pan.add(log,a);

        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame1.show(false);
                LogInView view = new LogInView();
                view.setVisible(true);

            }
        });


        btnModerator = new JButton("Register Moderator");
        btnModerator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame1.show(false);
                final JFrame frame2 = new JFrame();
                frame2.setVisible(true);
                frame2.setSize(600,400);
                JPanel panel = new JPanel(new GridBagLayout());
                frame2.getContentPane().add(panel,BorderLayout.NORTH);
                GridBagConstraints b = new GridBagConstraints();

              JLabel message = new JLabel("After you introduce the password please press enter and after that continue!");
              message.setBounds(170,100,300,50);
             b.gridx = 0;
             b.gridy = 2;
             panel.add(message,b);

                JLabel label = new JLabel("Password:");
                JPasswordField passtext = new JPasswordField( 10);

                b.gridx = 0;
                b.gridy = 0;
                panel.add(label,b);
                b.gridx = 1;
                b.gridy = 0;
                panel.add(passtext);

                final JButton next = new JButton("Continue");
                b.gridx = 1;
                b.gridy = 1;
                panel.add(next,b);

                JButton back = new JButton("Back");
                b.gridx = 1;
                b.gridy = 2;
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame2.show(false);
                        RegistrationView view = new RegistrationView();
                        view.setVisible(true);
                    }
                });
                panel.add(back,b);

                passtext.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       JPasswordField input = (JPasswordField) e.getSource();
                       char[] pas = input.getPassword();
                       String p = new String(pas);

                       if(p.equals(password)) {
                           next.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent actionEvent) {
                                   frame2.show(false);
                                   final JFrame frame3 = new JFrame();
                                   frame3.setVisible(true);
                                   frame3.setSize(400,300);
                                   JPanel panel = new JPanel(new GridBagLayout());
                                   frame3.getContentPane().add(panel,BorderLayout.NORTH);
                                   GridBagConstraints c = new GridBagConstraints();

                                   JLabel lblUsername = new JLabel("Username:");
                                   lblUsername.setBounds(10, 10, 120, 25);
                                   c.gridx = 0;
                                   c.gridy = 0;
                                   c.insets = new Insets(10,10,1,10);
                                   panel.add(lblUsername,c);

                                   txtUsername = new JTextField(10);
                                   //txtUsername.setBounds(10,10,120,25);
                                   c.gridx = 1;
                                   c.gridy = 0;
                                   panel.add(txtUsername,c);

                                   JLabel lblPassword = new JLabel("Password:");
                                   lblPassword.setBounds(10, 40, 120, 25);
                                   c.gridx = 0;
                                   c.gridy = 1;
                                   panel.add(lblPassword,c);

                                   txtPassword = new JPasswordField(10);
                                   //txtPassword.setBounds(80, 40, 100, 25);
                                   c.gridx = 1;
                                   c.gridy = 1;
                                   panel.add(txtPassword,c);

                                   btnRegister = new JButton("Register");
                                   btnRegister.setBounds(175, 110, 100, 40);
                                   c.gridx = 3;
                                   c.gridy = 4;
                                   btnRegister.addActionListener(new ActionListener(){
                                       @Override
                                       public void actionPerformed(ActionEvent actionEvent) {
                                           if (controller.checkAvailability(txtUsername.getText(), new String(txtPassword.getPassword()), "Moderator")) {
                                               JOptionPane.showMessageDialog(null, "Account create with succes", "Adding user", JOptionPane.INFORMATION_MESSAGE);
                                           } else {
                                               JOptionPane.showMessageDialog(null, "Account already exist", "Adding user", JOptionPane.ERROR_MESSAGE);
                                           }
                                       }
                                   });
                                   panel.add(btnRegister,c);

                                   JButton back = new JButton("Back to LogIn");
                                   back.setBounds(175,110,100,40);
                                   c.gridx = 1;
                                   c.gridy = 4;
                                   back.addActionListener(new ActionListener() {
                                       @Override
                                       public void actionPerformed(ActionEvent actionEvent) {
                                           frame3.show(false);
                                           LogInView view = new LogInView();
                                           view.setVisible(true);
                                       }
                                   });
                                   panel.add(back,c);


                               }
                           });
                       }
                       else
                           JOptionPane.showMessageDialog(null,"Incorrect");

                    }
                });



            }
        });
        btnModerator.setBounds(50,150,200,100);
        a.gridx = 2;
        a.gridy= 1;
        pan.add(btnModerator,a);

        btnCustomer = new JButton("Register Customer");
        btnCustomer.setBounds(250,150,200,100);
        a.gridx = 2;
        a.gridy= 2;
        pan.add(btnCustomer,a);

        btnCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame1.show(false);
                final JFrame fr = new JFrame();
                fr.setVisible(true);
                fr.setSize(600,300);
                JPanel panel = new JPanel(new GridBagLayout());
                fr.getContentPane().add(panel,BorderLayout.NORTH);
                GridBagConstraints c = new GridBagConstraints();

                JLabel lblUsername = new JLabel("Username:");
                lblUsername.setBounds(10, 10, 120, 25);
                c.gridx = 0;
                c.gridy = 0;
                c.insets = new Insets(10,10,10,10);
                panel.add(lblUsername,c);

                txtUsername = new JTextField(10);
                //txtUsername.setBounds(10,10,120,25);
                c.gridx = 1;
                c.gridy = 0;
                panel.add(txtUsername,c);

                JLabel lblPassword = new JLabel("Password:");
                lblPassword.setBounds(10, 40, 120, 25);
                c.gridx = 0;
                c.gridy = 1;
                panel.add(lblPassword,c);

                txtPassword = new JPasswordField(10);
                //txtPassword.setBounds(80, 40, 100, 25);
                c.gridx = 1;
                c.gridy = 1;
                panel.add(txtPassword,c);

                btnRegister = new JButton("Register");
                btnRegister.setBounds(175, 110, 100, 40);
                c.gridx = 3;
                c.gridy = 4;
                btnRegister.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (controller.checkAvailability(txtUsername.getText(), new String(txtPassword.getPassword()), "Customer")) {
                            JOptionPane.showMessageDialog(null, "Account create with succes", "Adding user", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Account already exist", "Adding user", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                panel.add(btnRegister,c);

                JButton back = new JButton("Back");
                c.gridx = 2;
                c.gridy = 4;
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        fr.show(false);
                        RegistrationView view = new RegistrationView();
                        view.setVisible(true);
                    }
                });
                panel.add(back,c);

            }
        });
      log.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent actionEvent) {

          }
      });





    }


    public static void main(String[] args) throws Exception {
        ModeratorServices.loadUsersFromFile();

        RegistrationView view = new RegistrationView();
        view.setVisible(true);
    }
}
