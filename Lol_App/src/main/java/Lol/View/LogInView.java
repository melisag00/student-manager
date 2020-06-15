package Lol.View;

import Lol.Customer.customer;
import Lol.Messages.messages;
import Lol.Participant.participant;
import Lol.Tournaments.Tournament;
import Lol.Tournaments.Tournament_details;
import Lol.annoucements.annoucement;
import Lol.controllers.LogInController;
import Lol.controllers.MessagesController;
import Lol.controllers.ParticipantController;
import Lol.controllers.TournamentController;
import Lol.services.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class LogInView extends JFrame {
    private LogInController controller;
    private TournamentController controller2;
    private ParticipantController controller3;
    private MessagesController controller4;

    private JTextField txtuser;
    private JPasswordField ps;
    private static java.util.List<customer> users;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "partner.json");

    private static List<participant> tour_part;
    private static final Path participant_path = FileSystemService.getPathToFile("config", "participanti_tournament.json");

    private static List<annoucement> ann;
    private static final Path Annouce_Path = FileSystemService.getPathToFile("congif","annoucements.json");

    private static List<Tournament> tour;
    private static final Path USERS_PATH2 = FileSystemService.getPathToFile("config", "tournament.json");

    private static List<Tournament_details> tour_det;
    private static final Path Tour_det_path = FileSystemService.getPathToFile("config","try_details.json");

    private static List<messages> mess_1;
    private static final Path Message_Path = FileSystemService.getPathToFile("congif","messages.json");

    public static void loadDetailsFromFile() throws IOException {

        if (!Files.exists(Tour_det_path)) {
            FileUtils.copyURLToFile(ParticipantServices.class.getClassLoader().getResource("try_details.json"), Tour_det_path.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        tour_det = objectMapper.readValue(Tour_det_path.toFile(), new TypeReference<List<Tournament_details>>() {
        });
    }

    public static void loadParticipants() throws IOException {

        if (!Files.exists(participant_path)) {
            FileUtils.copyURLToFile(CustomerServices.class.getClassLoader().getResource("participanti_tournament.json"), participant_path.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        tour_part = objectMapper.readValue(participant_path.toFile(), new TypeReference<List<participant>>() {
        });
    }
    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(CustomerServices.class.getClassLoader().getResource("partner.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<customer>>() {
        });
    }

    public static void loadAnnoucementsFromFile() throws IOException{
        if(!Files.exists(Annouce_Path)){
            FileUtils.copyURLToFile(ModeratorServices.class.getClassLoader().getResource("annoucements.json"),Annouce_Path.toFile());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ann = objectMapper.readValue(Annouce_Path.toFile(), new TypeReference<List<annoucement>>() {
        });
    }

    public static void loadUsersFromFile2() throws IOException {

        if (!Files.exists(USERS_PATH2)) {
            FileUtils.copyURLToFile(CustomerServices.class.getClassLoader().getResource("tournament.json"), USERS_PATH2.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        tour = objectMapper.readValue(USERS_PATH2.toFile(), new TypeReference<List<Tournament>>() {
        });
    }

    public static void loadMessagesFromFile() throws IOException {

        if (!Files.exists(Message_Path)) {
            FileUtils.copyURLToFile(MessagesServices.class.getClassLoader().getResource("messages.json"), Message_Path.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        mess_1 = objectMapper.readValue(Message_Path.toFile(), new TypeReference<List<messages>>() {
        });
    }


    public LogInView() {
        controller = new LogInController(this);
        controller2 = new TournamentController(this);
        controller3 = new ParticipantController(this);
        controller4 = new MessagesController(this);
        final JFrame frame1 = new JFrame("Lol-App");
        frame1.setVisible(true);
        frame1.setSize(300,200);
        JPanel panel = new JPanel(new GridBagLayout());
        frame1.getContentPane().add(panel,BorderLayout.NORTH);
        GridBagConstraints c = new GridBagConstraints();

        JButton reg = new JButton("Register");
        reg.setBounds(170,100,300,50);
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10,10,10,10);
        panel.add(reg,c);

        reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame1.show(false);
                RegistrationView view = new RegistrationView();
                view.setVisible(true);
            }
        });

        JButton log = new JButton("Log In");
        log.setBounds(170,100,300,50);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(log,c);

        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame1.show(false);
                final JFrame fr1 = new JFrame("Lol-App LogIn");
                fr1.setVisible(true);
                fr1.setSize(800,300);
                JPanel pn1 = new JPanel(new GridBagLayout());
                fr1.getContentPane().add(pn1,BorderLayout.NORTH);
                GridBagConstraints a = new GridBagConstraints();

                JLabel username = new JLabel("Username:");
                a.gridx = 0;
                a.gridy = 0;
                a.insets = new Insets(10,10,10,10);
                username.setBounds(20,30,10,10);
                pn1.add(username,a);

                txtuser = new JTextField(10);
                a.gridx = 1;
                a.gridy = 0;
                pn1.add(txtuser,a);

                JLabel pass = new JLabel("Password:");
                a.gridx = 0;
                a.gridy = 1;
                pn1.add(pass,a);

                 ps= new JPasswordField(10);
                a.gridx = 1;
                a.gridy = 1;
                pn1.add(ps,a);

                final JButton next = new JButton("Continue");
                a.gridx = 1;
                a.gridy = 3;
                pn1.add(next,a);

                next.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (controller.checkAcc(txtuser.getText(), new String(ps.getPassword()))) {
                            if(controller.checkRole(txtuser.getText(),"Moderator")) {
                               fr1.show(false);
                               final JFrame frame_moderator = new JFrame();
                               frame_moderator.setVisible(true);
                               frame_moderator.setSize(500,500);
                               JPanel panel_moderator = new JPanel(new GridBagLayout());
                               frame_moderator.getContentPane().add(panel_moderator, BorderLayout.WEST);
                               GridBagConstraints c_moderator = new GridBagConstraints();
                               c_moderator.gridx = 0;
                               c_moderator.gridy = 0;
                               c_moderator.insets = new Insets(10,10,10,10);
                               JButton players_on = new JButton("Accounts created");
                               JButton tournament = new JButton("Tournament");
                               final JButton annoucement = new JButton("Annoucement");
                               panel_moderator.add(players_on,c_moderator);
                               c_moderator.gridx = 1;
                               panel_moderator.add(tournament,c_moderator);
                               c_moderator.gridx = 2;
                               panel_moderator.add(annoucement,c_moderator);
                               players_on.addActionListener(new ActionListener() {
                                   @Override
                                   public void actionPerformed(ActionEvent actionEvent) {
                                       frame_moderator.show(false);
                                       final JFrame frame_players = new JFrame("Accounts created");
                                       frame_players.setVisible(true);
                                       frame_players.setSize(500,500);
                                       JButton back_to_moderator = new JButton("Back");
                                       JPanel panel_accounts = new JPanel(new GridBagLayout());
                                       frame_players.getContentPane().add(panel_accounts, BorderLayout.NORTH);
                                       GridBagConstraints c_players = new GridBagConstraints();

                                       try {
                                           int numar = controller.getNumberOfPlayers();
                                           String acc_created = String.valueOf(numar);
                                           c_players.gridx = 0;
                                           c_players.gridy = 0;
                                           JButton accounts = new JButton(acc_created);
                                           panel_accounts.add(accounts,c_players);
                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       }
                                       c_players.gridx = 0;
                                       c_players.gridy = 1;
                                       panel_accounts.add(back_to_moderator,c_players);
                                       back_to_moderator.addActionListener(new ActionListener() {
                                           @Override
                                           public void actionPerformed(ActionEvent actionEvent) {
                                               frame_players.show(false);
                                               frame_moderator.show(true);
                                           }
                                       });
                                   }
                               });

                               annoucement.addActionListener(new ActionListener() {
                                   @Override
                                   public void actionPerformed(ActionEvent actionEvent) {
                                       frame_moderator.show(false);
                                       final JFrame frame_annouce = new JFrame("Annoucement");
                                       frame_annouce.setVisible(true);
                                       frame_annouce.setSize(500,500);
                                       JPanel panel_annouce = new JPanel(new GridBagLayout());
                                       frame_annouce.getContentPane().add(panel_annouce, BorderLayout.NORTH);
                                       GridBagConstraints c_annouce = new GridBagConstraints();
                                       c_annouce.gridx = 0;
                                       c_annouce.gridy = 0;
                                       final JButton see_annouce = new JButton("See Annoucements");
                                       panel_annouce.add(see_annouce,c_annouce);
                                       JButton add_annouce = new JButton("Add annoucement");
                                       c_annouce.gridx = 0;
                                       c_annouce.gridy = 1;
                                       panel_annouce.add(add_annouce,c_annouce);
                                       JButton back_annouce = new JButton("Back");
                                       c_annouce.gridx = 0;
                                       c_annouce.gridy = 2;
                                       panel_annouce.add(back_annouce,c_annouce);
                                       back_annouce.addActionListener(new ActionListener() {
                                           @Override
                                           public void actionPerformed(ActionEvent actionEvent) {
                                               frame_annouce.show(false);
                                               frame_moderator.show(true);
                                           }
                                       });
                                       see_annouce.addActionListener(new ActionListener() {
                                           @Override
                                           public void actionPerformed(ActionEvent actionEvent) {
                                             frame_annouce.show(false);
                                               try {
                                                   loadAnnoucementsFromFile();
                                               } catch (IOException e) {
                                                   e.printStackTrace();
                                               }
                                               ObjectMapper objectMapper = new ObjectMapper();
                                               try {
                                                   ann = objectMapper.readValue(Annouce_Path.toFile(), new TypeReference<List<annoucement>>() {
                                                   });
                                               } catch (IOException e) {
                                                   e.printStackTrace();
                                               }
                                               final JFrame frame_see_ann = new JFrame("See annoucements");
                                               frame_see_ann.setVisible(true);
                                               frame_see_ann.setSize(500,500);
                                               JPanel pan_show_ann = new JPanel(new GridBagLayout());
                                               frame_see_ann.getContentPane().add(pan_show_ann, BorderLayout.NORTH);
                                               GridBagConstraints c_show_ann = new GridBagConstraints();
                                               c_show_ann.gridx = 0;
                                               c_show_ann.gridy = 0;
                                               c_show_ann.insets = new Insets(10,10,10,10);
                                               for(annoucement annouce:ann)
                                               {
                                                   c_show_ann.gridx = 0;

                                                   JLabel annoucem = new JLabel(annouce.getAnnouce());
                                                   pan_show_ann.add(annoucem,c_show_ann);
                                                   c_show_ann.gridy++;
                                               }
                                               JButton back_see_ann = new JButton("Back");
                                               pan_show_ann.add(back_see_ann,c_show_ann);
                                               back_see_ann.addActionListener(new ActionListener() {
                                                   @Override
                                                   public void actionPerformed(ActionEvent actionEvent) {
                                                       frame_see_ann.show(false);
                                                       frame_annouce.show(true);
                                                   }
                                               });
                                           }
                                       });
                                       add_annouce.addActionListener(new ActionListener() {
                                           @Override
                                           public void actionPerformed(ActionEvent actionEvent) {
                                               frame_annouce.show(false);
                                               final JFrame frame_add_ann = new JFrame("Add annoucement");
                                               frame_add_ann.setVisible(true);
                                               frame_add_ann.setSize(500,500);
                                               JPanel panel_ann_add = new JPanel(new GridBagLayout());
                                               frame_add_ann.getContentPane().add(panel_ann_add, BorderLayout.NORTH);
                                               GridBagConstraints c_add_ann = new GridBagConstraints();
                                               c_add_ann.gridx = 0;
                                               c_add_ann.gridy = 0;
                                               JLabel ann = new JLabel("Annoucement:");
                                               panel_ann_add.add(ann,c_add_ann);
                                               c_add_ann.gridx = 1;
                                               c_add_ann.gridy = 0;
                                               final JTextField ann_txt = new JTextField(20);
                                               panel_ann_add.add(ann_txt,c_add_ann);
                                               c_add_ann.gridx = 0;
                                               c_add_ann.gridy = 1;
                                               JButton put_ann = new JButton("Put annoucement");
                                               panel_ann_add.add(put_ann,c_add_ann);
                                               JButton back_ann = new JButton("Back");
                                               c_add_ann.gridx = 0;
                                               c_add_ann.gridy = 2;
                                               panel_ann_add.add(back_ann,c_add_ann);
                                               back_ann.addActionListener(new ActionListener() {
                                                   @Override
                                                   public void actionPerformed(ActionEvent actionEvent) {
                                                       frame_add_ann.show(false);
                                                       frame_annouce.show(true);
                                                   }
                                               });
                                               put_ann.addActionListener(new ActionListener() {
                                                   @Override
                                                   public void actionPerformed(ActionEvent actionEvent) {
                                                       try {
                                                           if(controller.putAnnoucement(ann_txt.getText()))
                                                           {
                                                               JOptionPane.showMessageDialog(null, "Annouce add with succes", "Annouce", JOptionPane.INFORMATION_MESSAGE);

                                                           }
                                                       } catch (IOException e) {
                                                           e.printStackTrace();
                                                       }
                                                   }
                                               });
                                           }

                                       });

                                       see_annouce.addActionListener(new ActionListener() {
                                           @Override
                                           public void actionPerformed(ActionEvent actionEvent) {

                                           }
                                       });
                                   }
                               });
                               tournament.addActionListener(new ActionListener() {
                                  @Override
                                  public void actionPerformed(ActionEvent actionEvent) {
                                      frame_moderator.show(false);

                                      final JFrame frame1 = new JFrame("Tournament");
                                      frame1.setVisible(true);
                                      frame1.setSize(500,500);
                                      JButton back = new JButton("Back");
                                      JButton adauga = new JButton("Add tournament");
                                      JButton delete = new JButton("Delete tournament");
                                      final JButton tournam = new JButton("See tournaments");
                                      JPanel panel = new JPanel(new GridBagLayout());
                                      frame1.getContentPane().add(panel, BorderLayout.NORTH);
                                      GridBagConstraints c = new GridBagConstraints();
                                      c.gridx = 0;
                                      c.gridy = 0;
                                      c.insets = new Insets(10,10,10,10);
                                      panel.add(adauga,c);
                                      c.gridx = 1;
                                      panel.add(delete,c);
                                      c.gridx = 2;
                                      panel.add(tournam,c);
                                      c.gridx = 1;
                                      c.gridy = 1;
                                      panel.add(back,c);
                                      back.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent actionEvent) {
                                              frame1.show(false);
                                              frame_moderator.show(true);
                                          }
                                      });
                                      adauga.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent actionEvent) {
                                             frame1.show(false);
                                             final JFrame frame_add = new JFrame();
                                             frame_add.setSize(300,300);
                                             frame_add.setVisible(true);
                                             JPanel panel_add = new JPanel(new GridBagLayout());
                                             frame_add.getContentPane().add(panel_add, BorderLayout.NORTH);
                                             GridBagConstraints c_add = new GridBagConstraints();
                                             c_add.gridx = 0;
                                             c_add.gridy = 0;
                                             c_add.insets = new Insets(10,10,10,10);
                                             JLabel name_label = new JLabel("Name:");
                                             JLabel date_label = new JLabel("Date:");
                                             final JTextField name_field = new JTextField(10);
                                             final JTextField date_field = new JTextField(10);
                                             panel_add.add(name_label,c_add);
                                             c_add.gridx = 1;
                                             c_add.gridy = 0;
                                             panel_add.add(name_field,c_add);
                                             c_add.gridx = 0;
                                             c_add.gridy = 1;
                                             panel_add.add(date_label,c_add);
                                             c_add.gridx = 1;
                                             c_add.gridy = 1;
                                             panel_add.add(date_field,c_add);
                                             JButton adaugare_tournament = new JButton("Add");
                                             c_add.gridx = 1;
                                             c_add.gridy = 2;
                                             panel_add.add(adaugare_tournament,c_add);
                                             JButton back_tournament = new JButton("Back");
                                             c_add.gridx = 0;
                                             c_add.gridy = 2;
                                             panel_add.add(back_tournament,c_add);
                                             back_tournament.addActionListener(new ActionListener() {
                                                 @Override
                                                 public void actionPerformed(ActionEvent actionEvent) {
                                                     frame_add.show(false);
                                                     frame1.show(true);
                                                 }
                                             });
                                             adaugare_tournament.addActionListener(new ActionListener() {
                                                 @Override
                                                 public void actionPerformed(ActionEvent actionEvent) {
                                                    if(controller2.AddTournament(name_field.getText(),date_field.getText()))
                                                     {
                                                     JOptionPane.showMessageDialog(null, "Tournament add with succes!", "Message", JOptionPane.INFORMATION_MESSAGE);
                                                 } else {
                                                     JOptionPane.showMessageDialog(null, "Add tournament fail", "Message", JOptionPane.ERROR_MESSAGE);
                                                 }
                                                 }
                                             });

                                          }
                                      });
                                      delete.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent actionEvent) {
                                              frame1.show(false);
                                              final JFrame frame_delete = new JFrame();
                                              frame_delete.setVisible(true);
                                              frame_delete.setSize(500,500);
                                              JPanel panel_delete = new JPanel(new GridBagLayout());
                                              frame_delete.getContentPane().add(panel_delete, BorderLayout.NORTH);
                                              GridBagConstraints c_delete = new GridBagConstraints();
                                              c_delete.gridx = 0;
                                              c_delete.gridy = 0;
                                              JLabel name_tournament = new JLabel("Tournament name:");
                                              panel_delete.add(name_tournament,c_delete);
                                              c_delete.gridx = 1;
                                              final JTextField txt_tournament = new JTextField(10);
                                              panel_delete.add(txt_tournament,c_delete);
                                              JButton back_delete = new JButton("Back");
                                              c_delete.gridx = 0;
                                              c_delete.gridy = 1;
                                              panel_delete.add(back_delete,c_delete);
                                              JButton delete = new JButton("Delete");
                                              c_delete.gridx = 1;
                                              panel_delete.add(delete,c_delete);
                                              back_delete.addActionListener(new ActionListener() {
                                                  @Override
                                                  public void actionPerformed(ActionEvent actionEvent) {
                                                      frame_delete.show(false);
                                                      frame1.show(true);
                                                  }
                                              });
                                              delete.addActionListener(new ActionListener() {
                                                  @Override
                                                  public void actionPerformed(ActionEvent actionEvent) {
                                                      String name_to_delete = txt_tournament.getText();
                                                      try {
                                                          if(controller2.deleteTournament(name_to_delete))
                                                          {
                                                              JOptionPane.showMessageDialog(null, "You delete the tournament with succes", "Delete", JOptionPane.INFORMATION_MESSAGE);

                                                          }
                                                      } catch (IOException e) {
                                                          e.printStackTrace();
                                                      }
                                                  }
                                              });

                                          }
                                      });
                                  tournam.addActionListener(new ActionListener() {
                                      @Override
                                      public void actionPerformed(ActionEvent actionEvent) {
                                          frame1.show(false);
                                          try {
                                              loadUsersFromFile2();
                                          } catch (IOException e) {
                                              e.printStackTrace();
                                          }
                                          ObjectMapper objectMapper = new ObjectMapper();

                                          try {
                                              tour = objectMapper.readValue(USERS_PATH2.toFile(), new TypeReference<List<Tournament>>() {
                                              });
                                          } catch (IOException e) {
                                              e.printStackTrace();
                                          }
                                          final JFrame frame_tour= new JFrame("List");
                                          frame_tour.setVisible(true);
                                          frame_tour.setSize(500,500);
                                          JPanel pan_show = new JPanel(new GridBagLayout());
                                          frame_tour.getContentPane().add(pan_show, BorderLayout.WEST);
                                          GridBagConstraints c_show = new GridBagConstraints();
                                          frame_tour.add(pan_show);
                                          c_show.gridx = 0;
                                          c_show.gridy = 0;
                                          c_show.insets = new Insets(10,10,10,10);
                                          for(Tournament t: tour)
                                          {

                                              c_show.gridx = 0;
                                              JLabel name = new JLabel(t.getName());
                                              pan_show.add(name,c_show);
                                              JLabel date = new JLabel(t.getDate());
                                              c_show.gridx=1;
                                              pan_show.add(date,c_show);
                                              c_show.gridy++;
                                          }
                                          JButton back_show = new JButton("Back");
                                          c_show.gridx = 0;
                                          pan_show.add(back_show,c_show);
                                          JButton see_players = new JButton("See participants");
                                          c_show.gridx = 1;
                                          pan_show.add(see_players,c_show);
                                          JButton details = new JButton("Details");
                                          c_show.gridx = 2;
                                          pan_show.add(details,c_show);
                                          details.addActionListener(new ActionListener() {
                                              @Override
                                              public void actionPerformed(ActionEvent actionEvent) {
                                                  frame_tour.show(false);
                                                  final JFrame frame_details = new JFrame();
                                                  frame_details.setSize(500,500);
                                                  frame_details.setVisible(true);
                                                  JPanel panel_details = new JPanel(new GridBagLayout());
                                                  frame_details.add(panel_details);
                                                  final GridBagConstraints c_details = new GridBagConstraints();
                                                  c_details.gridx = 0;
                                                  c_details.gridy = 0;
                                                  c_details.insets = new Insets(10,10,10,10);
                                                  JButton back_details = new JButton("Back");
                                                  panel_details.add(back_details,c_details);
                                                  JButton add_details = new JButton("Add details");
                                                  c_details.gridx = 1;
                                                  panel_details.add(add_details,c_details);
                                                  JButton see_details = new JButton("See details");
                                                  c_details.gridx = 2;
                                                  panel_details.add(see_details,c_details);
                                                  back_details.addActionListener(new ActionListener() {
                                                      @Override
                                                      public void actionPerformed(ActionEvent actionEvent) {
                                                          frame_details.show(false);
                                                          frame_tour.show(true);
                                                      }
                                                  });
                                                  see_details.addActionListener(new ActionListener() {
                                                      @Override
                                                      public void actionPerformed(ActionEvent actionEvent) {
                                                          frame_details.show(false);
                                                          final JFrame frame_see_details = new JFrame();
                                                          frame_see_details.setVisible(true);
                                                          frame_see_details.setSize(500,500);
                                                          JPanel panel_see_details = new JPanel(new GridBagLayout());
                                                          frame_see_details.getContentPane().add(panel_see_details, BorderLayout.NORTH);
                                                          GridBagConstraints c_see_details = new GridBagConstraints();
                                                          c_see_details.gridx = 0;
                                                          c_see_details.gridy = 0;
                                                          c_see_details.insets = new Insets(10,10,10,10);
                                                          JLabel name_trn = new JLabel("Tournament name:");
                                                          panel_see_details.add(name_trn,c_see_details);
                                                          c_see_details.gridx = 1;
                                                          final JTextField name_trn_txt = new JTextField(10);
                                                          panel_see_details.add(name_trn_txt, c_see_details);
                                                          c_see_details.gridx = 0;
                                                          c_see_details.gridy = 1;
                                                          JButton back_see_details = new JButton("Back");
                                                          panel_see_details.add(back_see_details,c_see_details);
                                                          JButton see = new JButton("See");
                                                          c_see_details.gridx = 1;
                                                          panel_see_details.add(see,c_see_details);
                                                          back_see_details.addActionListener(new ActionListener() {
                                                              @Override
                                                              public void actionPerformed(ActionEvent actionEvent) {
                                                                  frame_see_details.show(false);
                                                                  frame_details.show(true);
                                                              }
                                                          });
                                                          see.addActionListener(new ActionListener() {
                                                              @Override
                                                              public void actionPerformed(ActionEvent actionEvent) {
                                                                  frame_see_details.show(false);
                                                                  final JFrame frame_button_see = new JFrame();
                                                                  frame_button_see.setVisible(true);
                                                                  frame_button_see.setSize(500,500);
                                                                  JPanel panel_button_see = new JPanel(new GridBagLayout());
                                                                  frame_button_see.add(panel_button_see);
                                                                  GridBagConstraints c_button_see = new GridBagConstraints();
                                                                  c_button_see.gridx = 0;
                                                                  c_button_see.gridy = 0;
                                                                  c_button_see.insets = new Insets(10,10,10,10);
                                                                  try {
                                                                      loadDetailsFromFile();
                                                                  } catch (IOException e) {
                                                                      e.printStackTrace();
                                                                  }
                                                                  for(Tournament_details t_d:tour_det)
                                                                  {
                                                                      if(Objects.equals(t_d.getName(),name_trn_txt.getText()))
                                                                      {
                                                                          JLabel show = new JLabel(t_d.getDetails());
                                                                          panel_button_see.add(show,c_button_see);
                                                                          c_button_see.gridy++;
                                                                      }
                                                                  }
                                                                  JButton back_see_button = new JButton("Back");
                                                                  panel_button_see.add(back_see_button,c_button_see);
                                                                  back_see_button.addActionListener(new ActionListener() {
                                                                      @Override
                                                                      public void actionPerformed(ActionEvent actionEvent) {
                                                                          frame_button_see.show(false);
                                                                          frame_see_details.show(true);
                                                                      }
                                                                  });

                                                              }
                                                          });
                                                      }
                                                  });
                                                  add_details.addActionListener(new ActionListener() {
                                                      @Override
                                                      public void actionPerformed(ActionEvent actionEvent) {
                                                          frame_details.show(false);
                                                          final JFrame frame_add_details = new JFrame();
                                                          frame_add_details.setVisible(true);
                                                          frame_add_details.setSize(500,500);
                                                          JPanel panel_add_details = new JPanel(new GridBagLayout());
                                                          GridBagConstraints c_add_details = new GridBagConstraints();
                                                          frame_add_details.add(panel_add_details);
                                                          c_add_details.gridx = 0;
                                                          c_add_details.gridy = 0;
                                                          c_add_details.insets = new Insets(10,10,10,10);
                                                          JLabel name_tournament = new JLabel("Tournament name:");
                                                          panel_add_details.add(name_tournament,c_add_details);
                                                          c_add_details.gridx = 1;
                                                          final JTextField name_tournament_txt = new JTextField(10);
                                                          panel_add_details.add(name_tournament_txt,c_add_details);
                                                          c_add_details.gridx = 0;
                                                          c_add_details.gridy = 1;
                                                          JLabel details_tourn = new JLabel("Details:");
                                                          panel_add_details.add(details_tourn,c_add_details);
                                                          final JTextField details_tour_txt = new JTextField(10);
                                                          c_add_details.gridx = 1;
                                                          panel_add_details.add(details_tour_txt,c_add_details);
                                                          JButton back_add_details = new JButton("Back");
                                                          c_add_details.gridx = 0;
                                                          c_add_details.gridy = 2;
                                                          panel_add_details.add(back_add_details,c_add_details);
                                                          c_add_details.gridx = 1;
                                                          JButton adauga = new JButton("Add");
                                                          panel_add_details.add(adauga,c_add_details);
                                                          back_add_details.addActionListener(new ActionListener() {
                                                              @Override
                                                              public void actionPerformed(ActionEvent actionEvent) {
                                                                  frame_details.show(true);
                                                                  frame_add_details.show(false);
                                                              }
                                                          });
                                                          adauga.addActionListener(new ActionListener() {
                                                              @Override
                                                              public void actionPerformed(ActionEvent actionEvent) {
                                                                  try {
                                                                      if(controller2.add_details(name_tournament_txt.getText(),details_tour_txt.getText()))
                                                                      {
                                                                          JOptionPane.showMessageDialog(null, "Details add with succes", "Adding details", JOptionPane.INFORMATION_MESSAGE);

                                                                      }
                                                                  } catch (IOException e) {
                                                                      e.printStackTrace();
                                                                  }
                                                              }
                                                          });

                                                      }
                                                  });
                                              }
                                          });
                                          back_show.addActionListener(new ActionListener() {
                                              @Override
                                              public void actionPerformed(ActionEvent actionEvent) {
                                                  frame_tour.show(false);
                                                  frame1.show(true);
                                              }
                                          });
                                          see_players.addActionListener(new ActionListener() {
                                              @Override
                                              public void actionPerformed(ActionEvent actionEvent) {
                                                  frame_tour.show(false);
                                                  final JFrame frame_see_players = new JFrame();
                                                  frame_see_players.setVisible(true);
                                                  frame_see_players.setSize(500,500);
                                                  JPanel pan_see_players = new JPanel(new GridBagLayout());
                                                  frame_see_players.getContentPane().add(pan_see_players, BorderLayout.NORTH);
                                                  GridBagConstraints c_show_players = new GridBagConstraints();
                                                  JLabel name_tour = new JLabel("Tournament name:");
                                                  c_show_players.gridx = 0;
                                                  c_show_players.gridy = 0;
                                                  pan_see_players.add(name_tour,c_show_players);
                                                  final JTextField txt_tournament = new JTextField(10);
                                                  c_show_players.gridx = 1;
                                                  pan_see_players.add(txt_tournament,c_show_players);
                                                  JButton list = new JButton("List");
                                                  JButton back_see_players = new JButton("Back");
                                                  c_show_players.gridx = 0;
                                                  c_show_players.gridy = 1;
                                                  pan_see_players.add(back_see_players,c_show_players);
                                                  c_show_players.gridx = 1;
                                                  pan_see_players.add(list,c_show_players);
                                                  list.addActionListener(new ActionListener() {
                                                      @Override
                                                      public void actionPerformed(ActionEvent actionEvent) {
                                                          frame_see_players.show(false);
                                                          final JFrame frame_list = new JFrame();
                                                          frame_list.setVisible(true);
                                                          frame_list.setSize(500,500);
                                                          JPanel panel_list = new JPanel(new GridBagLayout());
                                                          frame_list.getContentPane().add(panel_list, BorderLayout.NORTH);
                                                          GridBagConstraints c_list = new GridBagConstraints();
                                                          c_list.gridx = 0;
                                                          c_list.gridy = 0;
                                                          c_list.insets = new Insets(10,10,10,10);

                                                          String name_tournament = txt_tournament.getText();
                                                          try {
                                                              loadParticipants();
                                                          } catch (IOException e) {
                                                              e.printStackTrace();
                                                          }
                                                          for(participant p:tour_part)
                                                          {
                                                              if(Objects.equals(name_tournament,p.getTournament_name()))
                                                              {
                                                                JLabel listare = new JLabel(p.getUsername());
                                                                panel_list.add(listare,c_list);
                                                                c_list.gridy++;
                                                              }

                                                          }
                                                          JButton back_list = new JButton("Back");
                                                          panel_list.add(back_list,c_list);
                                                          back_list.addActionListener(new ActionListener() {
                                                              @Override
                                                              public void actionPerformed(ActionEvent actionEvent) {
                                                                  frame_list.show(false);
                                                                  frame_see_players.show(true);
                                                              }
                                                          });
                                                      }
                                                  });
                                                  back_see_players.addActionListener(new ActionListener() {
                                                      @Override
                                                      public void actionPerformed(ActionEvent actionEvent) {
                                                          frame_tour.show(true);
                                                          frame_see_players.show(false);
                                                      }
                                                  });

                                              }
                                          });
                                      }
                                  });
                                  }
                              });
                            }
                            else {
                                fr1.show(false);
                                final JFrame frame = new JFrame("Choose");
                                frame.setSize(400, 300);
                                frame.setVisible(true);
                                JPanel panel = new JPanel(new GridBagLayout());
                                GridBagConstraints a = new GridBagConstraints();
                                frame.getContentPane().add(panel, BorderLayout.WEST);
                                String[] items = {"Top", "Jungle", "Mid", "Adc", "Support"};
                                JLabel choose_role = new JLabel("Choose your role!");
                                final JComboBox<String> c = new JComboBox<>(items);
                                String[] items_rank = {"Iron","Bronze","Silver","Gold","Platinum","Diamond","Master","Challenger"};
                                final JComboBox<String> c_rank = new JComboBox<>(items_rank);
                                frame.add(panel);
                                a.gridx = 0;
                                a.gridy = 0;
                                panel.add(choose_role,a);
                                a.gridx = 0;
                                a.gridy = 1;
                                a.insets = new Insets(10, 10, 10, 10);
                                panel.add(c, a);

                                a.gridx = 1;
                                a.gridy = 1;
                                panel.add(c_rank,a);
                                JLabel choose_rank = new JLabel("Choose your rank!");
                                a.gridx = 1;
                                a.gridy = 0;
                                panel.add(choose_rank,a);

                               JLabel choose_partner = new JLabel("Choose parnter role!");
                               a.gridx = 2;
                               a.gridy = 0;
                               panel.add(choose_partner,a);

                               String[] items_partner = {"Top","Jungle","Mid","Adc","Support"};
                               final JComboBox<String> c_partner = new JComboBox<>(items_partner);
                               a.gridx = 2;
                               a.gridy = 1;
                               panel.add(c_partner,a);

                               JButton finish = new JButton("Finish");
                               a.gridx = 3;
                               a.gridy = 2;
                               panel.add(finish,a);
                               finish.addActionListener(new ActionListener() {
                                   @Override
                                   public void actionPerformed(ActionEvent actionEvent) {
                                       try {
                                           controller.add_customer(txtuser.getText(),String.valueOf(c.getSelectedItem()),String.valueOf(c_rank.getSelectedItem()),String.valueOf(c_partner.getSelectedItem()));
                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       }
                                       frame.show(false);
                                       try {
                                           afisare_partener(String.valueOf(c_partner.getSelectedItem()));
                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       }
                                   }
                               });
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Account or password incorrect!", "Log In", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                JLabel message = new JLabel("After you introduce the password please press enter and after that continue!");
                message.setBounds(170,100,300,50);
                a.gridx = 0;
                a.gridy = 4;
                pn1.add(message,a);
            }
        });

    }
    public void afisare_partener(String partner_role) throws IOException {
        loadUsersFromFile();
        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<customer>>() {
        });
        final JFrame frame1 = new JFrame("List");
        frame1.setVisible(true);
        frame1.setSize(500,500);
        JPanel pan_show = new JPanel(new GridBagLayout());
        frame1.getContentPane().add(pan_show, BorderLayout.WEST);
        GridBagConstraints c_show = new GridBagConstraints();
        frame1.add(pan_show);
        c_show.gridx = 0;
        c_show.gridy = 0;
        c_show.insets = new Insets(10,10,10,10);
        for(customer user:users)
        {
            if (Objects.equals(partner_role,user.getCustomer_role()))
            {
                c_show.gridx = 0;
                JLabel name = new JLabel(user.getUsername());
                pan_show.add(name,c_show);
                JLabel rank = new JLabel(user.getRank());
                c_show.gridx=1;
                pan_show.add(rank,c_show);
                c_show.gridy++;
            }
        }
        c_show.gridx = 0;
        c_show.gridy++;
        JButton tourny = new JButton("Tournaments");
        pan_show.add(tourny,c_show);
        c_show.gridx = 1;
        JButton see_annoucements = new JButton("See annoucements");
        pan_show.add(see_annoucements,c_show);
        c_show.gridx = 2;
        JButton message = new JButton("Message");
        pan_show.add(message,c_show);
        message.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame1.show(false);
                final JFrame frame_message = new JFrame();
                frame_message.setSize(800,800);
                frame_message.setVisible(true);
                JPanel panel_message = new JPanel(new GridBagLayout());
                frame_message.add(panel_message);
                GridBagConstraints c_message = new GridBagConstraints();
                JLabel thing = new JLabel("Thing you need to know:");
                c_message.gridx = 0;
                c_message.gridy = 0;
                c_message.insets = new Insets(10,10,10,10);
                panel_message.add(thing,c_message);
                JLabel msg = new JLabel("The messagery works like an email!");
                c_message.gridy = 1;
                panel_message.add(msg,c_message);
                c_message.gridy = 2;
                JLabel player_name = new JLabel("Player name:");
                panel_message.add(player_name,c_message);
                final JTextField player_name_txt = new JTextField(10);
                c_message.gridx = 1;
                panel_message.add(player_name_txt,c_message);
                c_message.gridx = 0;
                c_message.gridy++;
                final JLabel mess = new JLabel("Message:");
                panel_message.add(mess,c_message);
                c_message.gridx = 1;
                final JTextField mess_txt = new JTextField(10);
                panel_message.add(mess_txt,c_message);
                c_message.gridx = 0;
                c_message.gridy++;
                JButton back_message = new JButton("Back");
                panel_message.add(back_message,c_message);
                c_message.gridx = 1;
                JButton see_message = new JButton("See messages");
                panel_message.add(see_message,c_message);
                c_message.gridx = 2;
                JButton send_message = new JButton("Send message");
                panel_message.add(send_message,c_message);
                back_message.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame1.show(true);
                        frame_message.show(false);
                    }
                });
                send_message.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            if(controller4.sendMessage(txtuser.getText(),player_name_txt.getText(),mess_txt.getText()))
                            {
                                JOptionPane.showMessageDialog(null, "You send the message with succes", "Sending message", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                see_message.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame_message.show(false);
                        final JFrame frame_see_message = new JFrame();
                        frame_see_message.setVisible(true);
                        frame_see_message.setSize(500,500);
                        JPanel panel_see_message = new JPanel(new GridBagLayout());
                        frame_see_message.add(panel_see_message);
                        final GridBagConstraints c_see_message = new GridBagConstraints();
                        c_see_message.gridx = 0;
                        c_see_message.gridy = 0;
                        c_see_message.insets = new Insets(10,10,10,10);
                        final JButton back_see_messages = new JButton("Back");
                        panel_see_message.add(back_see_messages,c_see_message);
                        c_see_message.gridx++;
                        JButton see_your_messages = new JButton("See messages you send");
                        panel_see_message.add(see_your_messages,c_see_message);
                        c_see_message.gridx++;
                        JButton see_messages_you_got = new JButton(("See messages you got"));
                        panel_see_message.add(see_messages_you_got,c_see_message);
                        back_see_messages.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                frame_message.show(true);
                                frame_see_message.show(false);
                            }
                        });
                        see_messages_you_got.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                frame_see_message.show(false);
                                final JFrame frame_see_messages_you_got = new JFrame();
                                frame_see_messages_you_got.setVisible(true);
                                frame_see_messages_you_got.setSize(500,500);
                                JPanel panel_see_messages_you_got = new JPanel(new GridBagLayout());
                                frame_see_messages_you_got.add(panel_see_messages_you_got);
                                GridBagConstraints c_see_you_got = new GridBagConstraints();
                                c_see_you_got.gridx = 0;
                                c_see_you_got.gridy = 0;
                                c_see_you_got.insets = new Insets(10,10,10,10);
                                try {
                                    loadMessagesFromFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                for(messages m:mess_1)
                                {
                                    if(Objects.equals(m.getTo(),txtuser.getText()))
                                    {
                                        JLabel mess_to = new JLabel("From:");
                                        panel_see_messages_you_got.add(mess_to,c_see_you_got);
                                        JLabel mess_send_to = new JLabel(m.getFrom());
                                        c_see_you_got.gridx = 1;
                                        panel_see_messages_you_got.add(mess_send_to,c_see_you_got);
                                        JLabel what_message = new JLabel(m.getMess());
                                        c_see_you_got.gridx = 0;
                                        c_see_you_got.gridy++;
                                        panel_see_messages_you_got.add(what_message,c_see_you_got);
                                        c_see_you_got.gridy++;
                                    }
                                }
                                JButton back_see_messages_got = new JButton("Back");
                                panel_see_messages_you_got.add(back_see_messages_got,c_see_you_got);
                                back_see_messages_got.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        frame_see_message.show(true);
                                        frame_see_messages_you_got.show(false);
                                    }
                                });
                            }
                        });
                        see_your_messages.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                frame_see_message.show(false);

                                final JFrame frame_see_your_messages = new JFrame();
                                frame_see_your_messages.setVisible(true);
                                frame_see_your_messages.setSize(500,500);
                                JPanel panel_see_your_messages = new JPanel(new GridBagLayout());
                                frame_see_your_messages.add(panel_see_your_messages);
                                GridBagConstraints c_see_your_messages = new GridBagConstraints();
                                c_see_your_messages.gridx = 0;
                                c_see_your_messages.gridy = 0;
                                c_see_your_messages.insets = new Insets(10,10,10,10);
                                try {
                                    loadMessagesFromFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                for(messages m:mess_1)
                                {
                                    if(Objects.equals(m.getFrom(),txtuser.getText()))
                                    {
                                        JLabel mess_to = new JLabel("To:");
                                        panel_see_your_messages.add(mess_to,c_see_your_messages);
                                        JLabel mess_send_to = new JLabel(m.getTo());
                                        c_see_your_messages.gridx = 1;
                                        panel_see_your_messages.add(mess_send_to,c_see_your_messages);
                                        JLabel what_message = new JLabel(m.getMess());
                                        c_see_your_messages.gridx = 0;
                                        c_see_your_messages.gridy++;
                                        panel_see_your_messages.add(what_message,c_see_your_messages);
                                        c_see_your_messages.gridy++;
                                    }
                                }
                                JButton back_see_your_messages = new JButton("Back");
                                panel_see_your_messages.add(back_see_your_messages,c_see_your_messages);
                                back_see_your_messages.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        frame_see_message.show(true);
                                        frame_see_your_messages.show(false);
                                    }
                                });


                            }
                        });
                    }
                });
            }
        });
        see_annoucements.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame1.show(false);
                try {
                    loadAnnoucementsFromFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    ann = objectMapper.readValue(Annouce_Path.toFile(), new TypeReference<List<annoucement>>() {
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

                final JFrame frame_see_ann = new JFrame("See annoucements");
                frame_see_ann.setVisible(true);
                frame_see_ann.setSize(500,500);
                JPanel pan_show_ann = new JPanel(new GridBagLayout());
                frame_see_ann.getContentPane().add(pan_show_ann, BorderLayout.NORTH);
                GridBagConstraints c_show_ann = new GridBagConstraints();
                c_show_ann.gridx = 0;
                c_show_ann.gridy = 0;
                c_show_ann.insets = new Insets(10,10,10,10);
                for(annoucement annouce:ann)
                {
                    c_show_ann.gridx = 0;

                    JLabel annoucem = new JLabel(annouce.getAnnouce());
                    pan_show_ann.add(annoucem,c_show_ann);
                    c_show_ann.gridy++;
                }
                JButton back_see_ann = new JButton("Back");
                pan_show_ann.add(back_see_ann,c_show_ann);
                back_see_ann.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame_see_ann.show(false);
                        frame1.show(true);
                    }
                });
            }
        });
        tourny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    loadUsersFromFile2();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ObjectMapper objectMapper = new ObjectMapper();

                try {
                    tour = objectMapper.readValue(USERS_PATH2.toFile(), new TypeReference<List<Tournament>>() {
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final JFrame frame_tour= new JFrame("List");
                frame_tour.setVisible(true);
                frame1.show(false);
                frame_tour.setSize(500,500);
                JPanel pan_show = new JPanel(new GridBagLayout());
                frame_tour.getContentPane().add(pan_show, BorderLayout.WEST);
                GridBagConstraints c_show = new GridBagConstraints();
                frame_tour.add(pan_show);
                c_show.gridx = 0;
                c_show.gridy = 0;
                c_show.insets = new Insets(10,10,10,10);
                for(Tournament t: tour) {

                    c_show.gridx = 0;
                    JLabel name = new JLabel(t.getName());
                    pan_show.add(name, c_show);
                    JLabel date = new JLabel(t.getDate());
                    c_show.gridx = 1;
                    pan_show.add(date, c_show);
                    c_show.gridy++;
                }
                JButton participate = new JButton("Participate");
                c_show.gridx = 1;
                c_show.gridy++;
                pan_show.add(participate,c_show);
                    participate.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            final JFrame frame_particpiate = new JFrame();
                            frame_particpiate.setSize(500,500);
                            frame_particpiate.setVisible(true);
                            frame_tour.show(false);
                            JPanel panel_participate = new JPanel(new GridBagLayout());
                            GridBagConstraints c_participate = new GridBagConstraints();
                            frame_particpiate.getContentPane().add(panel_participate, BorderLayout.NORTH);
                            final JLabel Username = new JLabel("Username");
                            c_participate.gridx = 0;
                            c_participate.gridy = 0;
                            panel_participate.add(Username,c_participate);
                            final JTextField user_txt_field = new JTextField(10);
                            c_participate.gridx = 1;
                            panel_participate.add(user_txt_field,c_participate);
                            JButton back_to_tourny = new JButton("Back");
                            final JLabel tournament = new JLabel("Tournament name:");
                            c_participate.gridx = 0;
                            c_participate.gridy = 1;
                            panel_participate.add(tournament,c_participate);
                            c_participate.gridx = 1;
                            final JTextField tour_txt_field = new JTextField(10);
                            panel_participate.add(tour_txt_field,c_participate);
                            c_participate.gridx = 0;
                            c_participate.gridy = 2;
                            panel_participate.add(back_to_tourny,c_participate);

                            JButton finish_participate = new JButton("Finish");
                            c_participate.gridx = 1;
                            panel_participate.add(finish_participate,c_participate);
                            back_to_tourny.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent actionEvent) {
                                    frame_particpiate.show(false);
                                    frame_tour.show(true);
                                }
                            });

                            finish_participate.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent actionEvent) {
                                    try {
                                        if(controller3.addParticipant(tour_txt_field.getText(),user_txt_field.getText())) {
                                            JOptionPane.showMessageDialog(null, "Succes", "Participate", JOptionPane.INFORMATION_MESSAGE);

                                        }
                                        else
                                        {
                                            JOptionPane.showMessageDialog(null, "Error", "Participate", JOptionPane.ERROR_MESSAGE);

                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        }
                    });

                JButton back_show = new JButton("Back");
                c_show.gridx = -1;
                c_show.gridy++;
                pan_show.add(back_show,c_show);
                back_show.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame_tour.show(false);
                        frame1.show(true);
                    }
                });
            }
        });
    }



    public static void main(String[] args) throws Exception {
        ModeratorServices.loadUsersFromFile();
        LogInView view = new LogInView();
        view.setVisible(true);
    }
}
