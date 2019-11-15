/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Vues.LoadView;
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
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.imageio.ImageIO;

import jpaModel.Administrateur;
import services.IUser;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Fallou
 */
public class AccueilAdmin implements Initializable {

    @FXML
    private Label prenomlabel;
    @FXML
    private Circle cerccle;
    @FXML
    private Circle cercle;

    @FXML
    private Label today;
    TrayNotification succes = new TrayNotification();
    TrayNotification alert = new TrayNotification();
    @FXML
    private Label nbmedecintxt;
    @FXML
    private ImageView imgadmin;
    @FXML
    private Label nbadmintxt;
    @FXML
    private Label nbinfirmiertxt;
    @FXML
    private Label label;
    String mois;
    int cptmedecin = 0;
    int cptinfirmier = 0;
    int cptcomptable = 0;
    int cptsecretaire = 0;
    int cptchambre = 0;
    int nbadmin = 0;
    List<Administrateur> la;

    @FXML
    private Label nbcomptabletxt;

    @FXML
    private Label nbsecretairetxt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            mois = mois();
            IUser stub = (IUser) Naming.lookup("rmi://localhost:1099/admin");
            la = stub.listeAdministrateur();
            la.stream().filter((administrateur) -> (administrateur.getIdprofil().getId() == 1)).forEachOrdered((_item) -> {
                nbadmin++;

            });
            la.stream().filter((administrateur) -> (administrateur.getIdprofil().getId() == 2)).forEachOrdered((_item) -> {
                cptmedecin++;

            });

            la.stream().filter((administrateur) -> (administrateur.getIdprofil().getId() == 4)).forEachOrdered((_item) -> {
                cptcomptable++;

            });
            la.stream().filter((administrateur) -> (administrateur.getIdprofil().getId() == 5)).forEachOrdered((_item) -> {
                cptsecretaire++;

            });

            nbmedecintxt.setText(cptmedecin + "");
            nbadmintxt.setText(nbadmin + "");
            nbcomptabletxt.setText(cptcomptable + "");
            nbinfirmiertxt.setText(cptinfirmier + "");
            nbsecretairetxt.setText(cptsecretaire + "");
            Path p = Paths.get("photo.txt");
            byte[] data = Files.readAllBytes(p);

            BufferedImage img = null;
            img = ImageIO.read(new ByteArrayInputStream(data));
            Image image = SwingFXUtils.toFXImage(img, null);
            ImageView imv = new ImageView(image);
            imv.setFitHeight(110);
            imv.setFitWidth(120);

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
            prenomlabel.setText(sortie);
            f.close();
          

        } catch (Exception e) {
        }

        bindToTime();

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
        today.setText(LocalDate.now().getDayOfMonth() + "  " + mois);
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
    }

    public void deconnecter() {
        LoadView.showView("connexion", "Authentification.fxml", 1);
    }

    public void ressources() {
        LoadView.showView("Centre de contrôle", "Ressources.fxml", 1);
    }
    public void administateur() {
        LoadView.showView("Centre de contrôle", "AddAdministrateur.fxml", 1);
    }

    public void addInfirmier() {
        LoadView.showView("Centre de contrôle", "AddMedecin.fxml", 1);
    }

    public void addMedecin() {
        LoadView.showView("Centre de contrôle", "AddMedecin.fxml", 1);
    }

    public void addComptable() {
        LoadView.showView("Centre de contrôle", "AddComptable.fxml", 1);
    }

    public void addSecretaire() {
        LoadView.showView("Centre de contrôle", "AddSecretaire.fxml", 1);
    }

    public String mois() {
        mois = "";
        int n = LocalDate.now().getMonth().getValue();
        if (n == 1) {
            mois = "JAanvier";
        }
        if (n == 2) {
            mois = "Fevrier";
        }
        if (n == 3) {
            mois = "Mars";
        }
        if (n == 4) {
            mois = "Avril";
        }
        if (n == 5) {
            mois = "Mai";
        }
        if (n == 6) {
            mois = "Juin";
        }
        if (n == 7) {
            mois = "Juillet";
        }
        if (n == 8) {
            mois = "Aout";
        }
        if (n == 9) {
            mois = "Mars";
        }
        if (n == 10) {
            mois = "Octobre";
        }
        if (n == 11) {
            mois = "Novembre";
        }
        if (n == 12) {
            mois = "Decembre";
        }
        return mois;

    }
}
