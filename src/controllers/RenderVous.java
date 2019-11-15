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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class RenderVous implements Initializable {

    @FXML
    private JFXButton Enregistrertbx;

    @FXML
    private JFXButton Imprimertbx;
    @FXML
    private Label today;
          @FXML
    private Label prenomlabel;
    @FXML
    private Circle cerccle;
    @FXML
    private Label label;
    TrayNotification succes = new TrayNotification();
    TrayNotification alert = new TrayNotification();
    @FXML
    private Circle cercle;

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
    private JFXTextField matriculetbx;
    @FXML
    private JFXDatePicker naissancetbx;

    @FXML
    private Pane panneau;
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
    List<Patient> l = null;
    Patient admin = null;
    java.util.Calendar time = java.util.Calendar.getInstance();
  String mois;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
              
                 mois=mois();
            panneau.setVisible(false);
            Enregistrertbx.setVisible(false);
            Imprimertbx.setVisible(false);
            IPatient stub = (IPatient) Naming.lookup("rmi://localhost:1099/patient");

            l = new ArrayList<>();
            l = stub.listePatient();
            List<Sexe> ls = stub.listeSexe();
            sexecbx.getItems().addAll(ls);
            
                Path p = Paths.get("photo.txt");
            byte[] data = Files.readAllBytes(p);

            BufferedImage img = null;
            img = ImageIO.read(new ByteArrayInputStream(data));
            Image image = SwingFXUtils.toFXImage(img, null);
            ImageView imv = new ImageView(image);
            imv.setFitHeight(92);
            imv.setFitWidth(92);

            cerccle.setFill(new ImagePattern(image));
            cerccle.setFill(new ImagePattern(image));
            //cerccle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITE));

            FileReader f = new FileReader("nom.txt");
            int pos = 0;
            String sortie = "";
            while (pos != -1) {
                char car = (char) pos;
                sortie = sortie + car;
                pos = f.read();
            }
            System.out.println(sortie);
            prenomlabel.setText(sortie);
            f.close();
                 
            
            
            
            
            
            
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
  
public String mois()
{
   mois="";
int n=LocalDate.now().getMonth().getValue();
if(n==1)
    mois="JAanvier";
if(n==2)
    mois="Fevrier";
if(n==3)
    mois="Mars";
if(n==4)
    mois="Avril";
if(n==5)
    mois="Mai";
if(n==6)
    mois="Juin";
if(n==7)
    mois="Juillet";
if(n==8)
    mois="Aout";
if(n==9)
    mois="Mars";
if(n==10)
    mois="Octobre";
if(n==11)
    mois="Novembre";
if(n==12)
    mois="Decembre";
return mois;

}
    private void bindToTime() {
          today.setText(LocalDate.now().getDayOfMonth()+"  "+mois);
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
            print.pane = pane;
            print.getDocument();
            LoadView.showView("connexion", "AccueilComptable.fxml", 1);
        } catch (Exception e) {
        }
    }

    public void choixpayement() {
        TranslateTransition t3 = new TranslateTransition(Duration.seconds(0.5));
        t3.setNode(pane3);
        t3.setToX(-1300);
        t3.play();

    }

    public void annuller() {
        try {
            LoadView.showView("connexion", "AccueilComptable.fxml", 1);
        } catch (Exception e) {
        }

    }

    public void printe() {
        try {

            try {
                enregistrer();
                print.pane = pane;
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
            Ticket ti = new Ticket();
            Validite v = new Validite(1, "En Attente De Validation");

            ti.setDate(java.sql.Date.valueOf(LocalDate.now()));
            ti.setMatricule("C" + admin.getMatricule());
            admin.setMatricule("C" + admin.getMatricule());
            ti.setValidite(v);
            admin.setIdticket("C" + admin.getMatricule());

            stub.InsererPatient(admin);
            stub.InsererTicket(ti);
            succes.setMessage("PERSONNE ENREGISTREE AVEC SUCCES");
            succes.showAndDismiss(Duration.seconds(2));

        } catch (Exception e) {
            alert.setMessage("L IMAGE CHOISIE EST TROP GRAND");
            alert.showAndDismiss(Duration.seconds(2));
            System.out.println(e);

        }

    }

    public void rechercher() {

        for (Patient m : l) {
            if (m.getMatricule().equals(matriculetbx.getText())) {
                admin = new Patient();
                admin = m;
                panneau.setVisible(true);
                Enregistrertbx.setVisible(true);
                Imprimertbx.setVisible(true);
                matriculetbx.setText(m.getMatricule());
                nomtbx.setText(m.getNom());
                prenomtbx.setText(m.getPrenom());
                adressetbx.setText(m.getAdresse());
                telephonetbx.setText(m.getTelephone());

                try {

                    BufferedImage img = null;

                    Image images = SwingFXUtils.toFXImage(img, null);
                    ImageView imv = new ImageView(images);

                    imv.setFitWidth(90);
                    imv.setFitHeight(100);
                    image.setGraphic(imv);

                } catch (Exception e) {
                }
                break;
            } else if (matriculetbx.getText().equals("")) {
                alert.setMessage("Indiquer Le matricule");
                alert.showAndDismiss(Duration.seconds(2));
            } else {

                alert.setMessage("Le matricule Nexiste Pas");
                alert.showAndDismiss(Duration.seconds(2));
                panneau.setVisible(false);
                Enregistrertbx.setVisible(false);
                Imprimertbx.setVisible(false);

            }

        }
        {

        }
    }

}
