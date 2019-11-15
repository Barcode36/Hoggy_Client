/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Vues.LoadView;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
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
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import jpaModel.Administrateur;
import jpaModel.Patient;
import jpaModel.Sexe;
import jpaModel.Ticket;
import services.IPatient;
import services.IUser;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Fallou
 */
public class AccueilComptable implements Initializable {

    String mois;
    @FXML
    private JFXCheckBox cbxtous;

    @FXML
    private JFXCheckBox cbxattente;

    @FXML
    private JFXCheckBox cbxvalide;

    @FXML
    private JFXCheckBox cbxexpirer;
    private final ObservableList<Patient> data = FXCollections.observableArrayList();
    private final FilteredList<Patient> filteredData = new FilteredList<>(data, p -> true);
    private final SortedList<Patient> sortedData = new SortedList<>(filteredData);
    @FXML
    private TableView<Patient> tableconsultation;
    @FXML
    private JFXTextField rechercherconsultation;
    @FXML
    private TableColumn<?, ?> matriculetbxc;

    @FXML
    private TableColumn<?, ?> nomc;

    @FXML
    private TableColumn<?, ?> prenomc;
    @FXML
    private TableColumn<?, ?> adressec;
    @FXML
    private Label today;

    @FXML
    private Label label;

    @FXML
    private Label imagea1;

    @FXML
    private Label imagea2;

    @FXML
    private Label imagea3;

    @FXML
    private Label imageb1;

    @FXML
    private Label imageb2;

    @FXML
    private Label imageb3;
    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Label imagec2;
    @FXML
    private Label prenomlabel;
    @FXML
    private Circle cerccle;

    List<Patient> l;
    List<Ticket> t;

    @FXML
    private Label nomlabel;
    @FXML
    private Label imagec3;
    TrayNotification succes = new TrayNotification();
    TrayNotification alert = new TrayNotification();
    @FXML
    private Label imagec1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mois = mois();
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
          
            bindToTime();

            IPatient stub = (IPatient) Naming.lookup("rmi://localhost:1099/patient");
            l = stub.listePatient();
            t = stub.listeticket();
        } catch (Exception e) {
        }
    }

    public void calandar() {
        LoadView.showView("connexion", "Calendar.fxml", 1);
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
        today.setText(LocalDate.now().getDayOfMonth() + "  " + mois);
    }

    public void close() {

    }

//      private void setCellTable() {
//
//        matriculetbxc.setCellValueFactory(new PropertyValueFactory<>("matricule"));
//        nomc.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        prenomc.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//        adressec.setCellValueFactory(new PropertyValueFactory<>("adresse"));
//      
//
//    }
//    public void releasematricule() {
//
//        rechercherconsultation.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(medecin -> {
//
//                if (newValue == null || newValue.isEmpty()) {
//
//                    return true;
//
//                }
//                String lowerCaseFilter = newValue.toLowerCase();
//                if (medecin.getMatricule().toLowerCase().contains(lowerCaseFilter)) {
//
//                    return true;
//                } else if (medecin.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
//
//                    return true;
//                }
//
//                return false;
//
//            });
//        }
//        );
//        sortedData.comparatorProperty().bind(tableconsultation.comparatorProperty());
//
//        tableconsultation.setItems(sortedData);
//
//    }
    public void hospitalisation() {
        LoadView.showView("connexion", "FHospitalisation.fxml", 1);
    }

    public void rendervous() {
        LoadView.showView("connexion", "FRenderVous.fxml", 1);
    }

    public void consultation() {
        LoadView.showView("connexion", "FConsultation.fxml", 1);
    }

    public void sta() {
        LoadView.showView("connexion", "Statique.fxml", 1);
    }

    public void vaccination() {
        LoadView.showView("connexion", "FVaccination.fxml", 1);
    }

    public void deconnecter() {
        LoadView.showView("connexion", "Authentification.fxml", 1);
    }

    public void LoadEtudiantFromBase() {
        try {

            data.clear();

            l.forEach((patient) -> {
                for (Ticket ticket : t) {
                    if (patient.getMatricule().equals(ticket.getMatricule())) {
                        data.add(patient);
                    }
                }
            });

            tableconsultation.setItems(data);
        } catch (Exception ex) {
            System.out.println(ex);
        }

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
