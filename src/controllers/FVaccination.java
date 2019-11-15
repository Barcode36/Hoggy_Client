/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Vues.LoadView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.corba.se.impl.util.Utility;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.rmi.Naming;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.rmi.CORBA.Stub;
import jpaModel.Administrateur;
import jpaModel.Comptable;
import jpaModel.Patient;
import jpaModel.Profil;
import jpaModel.Sexe;
import jpaModel.Ticket;
import jpaModel.Validite;
import services.IComptable;
import services.IPatient;
import services.IUser;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utiles.print;
import utiles.print2;

/**
 * FXML Controller class
 *
 * @author Fallou
 */
public class FVaccination implements Initializable {

     @FXML
    private Label today;
    @FXML
    private Label label;
   TrayNotification succes = new TrayNotification();
    TrayNotification alert = new TrayNotification();
    @FXML
    private Circle cercle;
    @FXML
    private Label matriculetbx;
    @FXML
    private Pane pane;

    @FXML
    private JFXTextField nomtbx;

    @FXML
    private JFXTextField prenomtbx;

    @FXML
    private JFXTextField adressetbx;

    @FXML
    private JFXTextField telephonetbx;

    @FXML
    private JFXDatePicker naissancetbx;

    @FXML
    private JFXTextField montanttbx;
    @FXML
    private JFXTextField mailtbx;

    @FXML
    private ComboBox<Sexe> sexecbx;
    @FXML
    private Label image;

    @FXML
    private Pane pane3;

    @FXML
    private JFXButton valider012;

    @FXML
    private JFXButton valider011;
  int n;
    int cpt;
    private File patient = new File("src/images/patient.png");
    private File file = new File("src/images/back.png");
  java.util.Calendar time = java.util.Calendar.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        naissancetbx.setValue(LocalDate.now());
        mailtbx.setText("adresse@gmail.com");
        telephonetbx.setText("770000000");
            n=(int) (Math.random()*9999999);
            matriculetbx.setText("V"+String.valueOf(n));
    
        try {
             IPatient stub = (IPatient) Naming.lookup("rmi://localhost:1099/patient");            
            List<Sexe>ls=stub.listeSexe();  sexecbx.getItems().addAll(ls);
        } catch (Exception e) {
        }
           alert.setMessage("ATTENTION");
            alert.setTitle("INFORMATION");
            alert.setAnimationType(AnimationType.POPUP);
            alert.setNotificationType(NotificationType.ERROR);
       
        bindToTime();
        backimage();
    }

    public void back() {
        LoadView.showView("connexion", "AccueilComptable.fxml", 1);
    }

    public void backimage() {
        try {
            initDefaultImage();
            BufferedImage img = null;
            img = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(img, null);
            ImageView imv = new ImageView(image);
            imv.setFitHeight(92);
            imv.setFitWidth(92);
            cercle.setFill(new ImagePattern(image));
        } catch (Exception e) {
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

    private void bindToTime() {
         Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        java.util.Calendar time = java.util.Calendar.getInstance();
                        String hourString = pad(2, ' ', time.get(java.util.Calendar.HOUR) == 0 ? "12" : time.get(java.util.Calendar.HOUR) + "");
                        String minuteString = pad(2, '0', time.get(java.util.Calendar.MINUTE) + "");
                        String secondString = pad(2, '0', time.get(java.util.Calendar.SECOND) + "");
                        String ampmString = time.get(java.util.Calendar.AM_PM) == java.util.Calendar.AM ? "AM" : "PM";
                        label.setText(hourString + " : " + minuteString + " : " + secondString + " " + ampmString);
                     

                      
                   
                    }
                }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        today.setText(LocalDate.now().getDayOfMonth() + "  " + LocalDate.now().getMonth());
       
                    

            
    }

    private void initDefaultImage() {

        BufferedImage img = null;
        try {

            ImageView imageview = new ImageView("/images/5.png");
            imageview.setFitWidth(90);
            imageview.setFitHeight(100);
            image.setGraphic(imageview);

        } catch (Exception e) {
        }
    }

    public void print() {
        try {
           print2.pane=pane;
       
        print2.print(PrinterJob.createPrinterJob( ), pane);
             //LoadView.showView("connexion", "AccueilComptable.fxml", 1);
        } catch (Exception e) {
        }
    }

    public void choixpayement() {
            TranslateTransition t3 = new TranslateTransition(Duration.seconds(0.5));
            t3.setNode(pane3);
            t3.setToX(-1300);
            t3.play();
  
    }


public void annuller(){
    try {
          LoadView.showView("connexion", "AccueilComptable.fxml", 1);
    } catch (Exception e) {
    }


}
    public void printe() {
        try {
            
            
             try {
                   enregistrer();
print.pane=pane;
print.getDocument();
 


                  } catch (Exception e) {
        }
             // LoadView.showView("connexion", "AccueilComptable.fxml", 1);
             } catch (Exception e) {
        }
    }
public int validernombre(String texte) {
        int n = 0;
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(texte);
        char c = texte.charAt(0);
        char c1 = texte.charAt(1);

        if (m.find() && m.group().equals(texte) && texte.length() == 9 && c == '7' || c == '3') {
            if (c1 == '7' || c1 == '8' || c1 == '6' || c1 == '0' || c1 == '3') {
                n = 0;
            } else {
                n = 1;
            }

        } else {

            n = 1;
        }
        return n;
    }
   
 public int validerchamp(String texte) {
        int trouve = 0;
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(texte);

        if (m.find() && m.group().equals(texte)) {
            trouve = 0;
        } else {

            trouve = 1;
        }
        return trouve;
    }
    
      public int validermail(String texte) {
        int n = 0;

        int x = texte.indexOf('@');
        if (x > 3) {
            String t = texte.substring(x);
            if ("@yahoo.com".equals(t) || "@yahoo.fr".equals(t) || "@gmail.fr".equals(t) || "@gmail.com".equals(t) || "@hotmail.fr".equals(t) || "@hotmail.com".equals(t)) {
                n = 1;

            } else {
                n = 0;
            }
        }
        return n;
    }
    public void retour1() {
        try {

            
            
            
               TranslateTransition t3 = new TranslateTransition(Duration.seconds(0.5));
            t3.setNode(pane3);
            t3.setToX(1300);
            t3.play();

        } catch (Exception e) {
        }
    }

    
     public void retour2() {
        try {

//            TranslateTransition t = new TranslateTransition(Duration.seconds(0.5));
//            t.setNode(pane2);
//            t.setToX(1300);
//            t.play();

        } catch (Exception e) {
        }
    }  
   public void enregistrer() {

        try {

            IPatient stub = (IPatient) Naming.lookup("rmi://localhost:1099/patient");
            if ( nomtbx.getText().trim().isEmpty() || prenomtbx.getText().trim().isEmpty() || adressetbx.getText().trim().isEmpty()  ) {
                alert.setMessage("VEILLER REMPLIR TOUS LES CHAMPS SVP");
               alert.showAndDismiss(Duration.seconds(2));
               

            }  else if (validerchamp(nomtbx.getText()) == 1 || nomtbx.getText().length() > 15) {
              
                  alert.setMessage("LE NOM DOIT CONTENIR QUE DES LETTRES ET INFERIEUR A 15 CARACTERES");
               alert.showAndDismiss(Duration.seconds(2));
               
      
            
            } else if (validerchamp(prenomtbx.getText()) == 1 || nomtbx.getText().length() > 15) {
            
                alert.setMessage("LE NOM DOIT CONTENIR QUE DES LETTRES ET INFERIEUR A 15 CARACTERES");
               alert.showAndDismiss(Duration.seconds(2));
               
             
            } else if (validernombre(telephonetbx.getText()) == 1) {
             
                
              alert.setMessage("LE NUMERO TELEPHONE N EST PAS BON.IL DOIT COMMENCE PAR 77 78 76 70 OU 33");
               alert.showAndDismiss(Duration.seconds(2));
            
            } else if (validermail(mailtbx.getText()) == 0) {
             
                  alert.setMessage("L ADRESSE MAIL N EST PAS BON");
               alert.showAndDismiss(Duration.seconds(2));
            
            }  else {
              
                Patient  admin = new Patient();
                admin.setMatricule(matriculetbx.getText());
                admin.setNom(nomtbx.getText());
                admin.setPrenom(prenomtbx.getText());
                admin.setTelephone(telephonetbx.getText());
                admin.setAdresse(adressetbx.getText());
             
                admin.setIdsexe(sexecbx.getValue());
                admin.setMail(mailtbx.getText());
                admin.setNaissance(java.sql.Date.valueOf(naissancetbx.getValue()));
                
                Ticket ti=new Ticket();
                Validite v=new Validite(3,"En Attente De Validation");
                
                ti.setDate(java.sql.Date.valueOf(naissancetbx.getValue()));
                ti.setMatricule("C"+String.valueOf(n));
                ti.setValidite(v);
               
                admin.setIdticket("C"+String.valueOf(n));
              
                
                
                
               
                byte[] t = Files.readAllBytes(file.toPath());
                admin.setPhoto(t);
          
                     
             
                
              
                    stub.InsererPatient(admin);
                stub.InsererTicket(ti);
                    succes.setMessage("PERSONNE ENREGISTREE AVEC SUCCES");
               succes.showAndDismiss(Duration.seconds(2));
                    
                 
                
            }
        } catch (Exception e) {
             alert.setMessage("L IMAGE CHOISIE EST TROP GRAND");
               alert.showAndDismiss(Duration.seconds(2));
            System.out.println(e);
            
            
        }

    }
    
 

}
