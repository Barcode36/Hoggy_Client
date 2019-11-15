/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Vues.LoadView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import jpaModel.Administrateur;
import services.IUser;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utiles.print;

/**
 * FXML Controller class
 *
 * @author Fallou
 */
public class Authentification implements Initializable {
   
    @FXML
    private JFXSlider betSlider;

    @FXML
    private Label betLabel;

    @FXML
    private JFXTextField logintbx;

    @FXML
    private Label label;
    @FXML
    private JFXPasswordField passwordtbx;

    @FXML
    private JFXButton connectiontbx;
 @FXML
    private CubicCurve pulse;
    @FXML
    private Label onlinetbx;

    @FXML
    private Circle cercle;
        @FXML
    private Circle cercle2;
    @FXML
    private Label offlinetbx;
    private File file = new File("src/images/senegal.png");
      private File file2 = new File("src/images/sante.png");
    @FXML
    private Label message;
    @FXML
    private Label label2;
    TrayNotification succes = new TrayNotification();
    TrayNotification alert = new TrayNotification();
    private Thread t1;
    int n = 0;
    @FXML
    private Label slider;
    @FXML
    private Label sante;
        @FXML
    private Label aidetbx;

    @FXML
    private Label centrelabel;
         String matricule;
            @FXML
    private Pane messagerie;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
       bindToTime();
           
               alert.setAnimationType(AnimationType.POPUP);
            alert.setNotificationType(NotificationType.ERROR);
              succes.setAnimationType(AnimationType.POPUP);
            succes.setNotificationType(NotificationType.SUCCESS);
       
       
       
            BufferedImage img = null;

            img = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(img, null);
            ImageView imv = new ImageView(image);
            imv.setFitHeight(92);
            imv.setFitWidth(92);

            cercle.setFill(new ImagePattern(image));
            cercle.setFill(new ImagePattern(image));
           
               img = ImageIO.read(file2);
             image = SwingFXUtils.toFXImage(img, null);
             imv = new ImageView(image);
            imv.setFitHeight(100);
            imv.setFitWidth(120);

            cercle2.setFill(new ImagePattern(image));
            cercle2.setFill(new ImagePattern(image));
            
          

            t1 = new Thread(r);
            t1.start();
           

        } catch (Exception e) {
        }
    }
    int cpt = 0;

    public void show() {

        cpt++;

        TranslateTransition t = new TranslateTransition();
        t.setNode(label);

        if (cpt % 2 != 0) {

            message.setVisible(false);
            t.setDuration(Duration.seconds(0.5));
            t.setToY(-300);
            label.setAlignment(Pos.BOTTOM_LEFT);
            label2.setStyle("-fx-background-color:#1ABC9C");
            t.play();

        } else {
            t.setDuration(Duration.seconds(0.5));
            t.setToY(-3.0);
            label.setAlignment(Pos.CENTER);
            t.play();
            message.setVisible(true);
            label.setText("BIENVENUE");
             label2.setStyle("-fx-background-color:#06082b");

        }

    }

    public String pad(int fieldWidth, char padChar, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < fieldWidth; i++) {
            sb.append(padChar);
        }
        sb.append(s);

        return sb.toString();
    }
    int z = 0;

    private void bindToTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Calendar time = Calendar.getInstance();
                        String hourString = pad(2, ' ', time.get(Calendar.HOUR) == 0 ? "12" : time.get(Calendar.HOUR) + "");
                        String minuteString = pad(2, '0', time.get(Calendar.MINUTE) + "");
                        String secondString = pad(2, '0', time.get(Calendar.SECOND) + "");
                        String ampmString = time.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
                        label.setText(hourString + ":" + minuteString + ":" + secondString + " " + ampmString);
                     

                      
                   
                    }
                }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    Runnable r = () -> {

        while (true) {
            try {
                IUser stub = (IUser) Naming.lookup("rmi://localhost:1099/admin");

                if (n == 1) {
                    onlinetbx.setVisible(false);
                    offlinetbx.setVisible(true);
                    //   onlineimg.setVisible(false);
                    //  offlineimg.setVisible(true);
                  //  System.out.println("d");

                } else {
                    if (stub == null || n == 0) {
                        onlinetbx.setVisible(true);
                        offlinetbx.setVisible(false);

                    }
                }
                // Thread.sleep(1000);
            } catch (Exception e) {
                onlinetbx.setVisible(false);
                offlinetbx.setVisible(true);
              

            }

        }

    };
    
    
    public void connection() {


        try {

           
           
            if (logintbx.getText().trim().isEmpty() || passwordtbx.getText().trim().isEmpty()) {
                alert.setMessage("VEILLER REMPLIR TOUS LES CHAMPS SVP");
               alert.showAndDismiss(Duration.seconds(2));

            } else {
            
                IUser stub = (IUser) Naming.lookup("rmi://localhost:1099/admin");
                
                
                
                
                
                
                
              Administrateur user = stub.findUser(logintbx.getText(), passwordtbx.getText());
              
               if (user == null) {
                     print.t=user;

                       alert.setMessage("LOGIN OU MOT DE PASSE INCORRECTE");
               alert.showAndDismiss(Duration.seconds(2));
                } else if (user.getIdstatus() == 1) {
                    int x = user.getIdprofil().getId();
                    
                    
                    FileOutputStream fos = new FileOutputStream("photo.txt");
                    fos.write(user.getPhoto());
                    fos.close();

                    File fichier = new File("nom.txt");
                    FileWriter v = new FileWriter(fichier);
                    String nom = user.getPrenom() + " " + user.getNom();
                    v.write(nom);
                    v.close();

                    File fichier2 = new File("matricule.txt");
                    FileWriter v2 = new FileWriter(fichier2);

               matricule = user.getMatricule();
                    v2.write(matricule);
                    v2.close();

                    int z = user.getLog();
                 
                    switch (x) {
                        
                        case 1: {

                            if (z == 0) {
                                LoadView.showView("INSCRIPTION", "change.fxml", 1);
                            } else {
                                   succes.setMessage("BONJOUR "+user.getPrenom()+" "+user.getNom());
                                 succes.showAndDismiss(Duration.seconds(2));
                                LoadView.showView("Centre De controle", "AccueilAdmin.fxml", 1);
                            }
                        }
                        break;
                        case 2: {
                            if (z == 0) {
                                LoadView.showView("INSCRIPTION", "change.fxml", 1);
                            } else {
                                             succes.setMessage("BONJOUR "+user.getPrenom()+" "+user.getNom());
                                 succes.showAndDismiss(Duration.seconds(2));
                                LoadView.showView("Accueil Medecin", "AccueilMedecin.fxml", 1);
                            }
                        }
                        break;
                        case 4: {
                            if (z == 0) {
                                LoadView.showView("INSCRIPTION", "change.fxml", 1);
                            } else {
                                             succes.setMessage("BONJOUR "+user.getPrenom()+" "+user.getNom());
                                 succes.showAndDismiss(Duration.seconds(2));
                                LoadView.showView("Accueil Comptable", "AccueilComptable.fxml", 1);
                            }
                        }
                        break;
                        case 3: {
                            if (z == 0) {
                                LoadView.showView("INSCRIPTION", "change.fxml", 1);
                            } else {
                                LoadView.showView("INSCRIPTION", "AccueilMecanicien.fxml", 1);
                            }
                        }
                        break;
                        case 5: {
                            if (z == 0) {
                                LoadView.showView("INSCRIPTION", "change.fxml", 1);
                            } else {
                                           succes.setMessage("BONJOUR "+user.getPrenom()+" "+user.getNom());
                                 succes.showAndDismiss(Duration.seconds(2));
                                LoadView.showView("Accueil Secretaire", "AccueilSecretaire.fxml", 1);
                            }
                        }
                        break;
                        
                        case 6: {
                            if (z == 0) {
                                LoadView.showView("INSCRIPTION", "change.fxml", 1);
                            } else {
                                LoadView.showView("INSCRIPTION", "SuperUser.fxml", 1);
                            }
                        }
                        
                        
                      
                    }
                } else {
                 alert.setMessage("VOTRE COMPTE A ETE BLOQUER .VEILLER CONTACTER L ADMINISTRATEUR");
                    alert.showAndDismiss(Duration.seconds(2));
                
                }

            }

        } catch (Exception e) {

            alert.setMessage("LE SERVEUR N A PAS ETE DEMARRER");
                    alert.showAndDismiss(Duration.seconds(2));
            System.out.println(e);

        }
           t1.stop();
         t1.interrupt();

    }


//    public void slider(int n)
//    {
//       
//        BufferedImage img = null;
//        try {
//
//            ImageView imageview = new ImageView("/images/"+n+".jpg");
//            imageview.setFitWidth(300);
//            imageview.setFitHeight(300);
//            slider.setGraphic(imageview);
//
//        } catch (Exception e) {
//        }
//    }
    
    public void fademessage()
    {
    Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                       
                     
                        FadeTransition ft = new FadeTransition(Duration.millis(400), message);
                        ft.setDuration(Duration.seconds(4));
                        ft.setFromValue(10);
                        ft.setToValue(0);
                        ft.play();
                        
                      
                   
                    }
                }
                ),
                new KeyFrame(Duration.seconds(6))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
 
    public void forget() {
        try {

            LoadView.showView("INSCRIPTION", "forget.fxml", 1);
        } catch (Exception e) {
        }

    }
  




}
