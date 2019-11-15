/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Vues.LoadView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import jpaModel.Administrateur;
import jpaModel.Comptable;
import jpaModel.Patient;
import jpaModel.Sexe;
import jpaModel.Ticket;
import jpaModel.Validite;
import services.IPatient;
import services.IUser;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utiles.Genre;
import utiles.print;

/**
 * FXML Controller class
 *
 * @author Fallou
 */
public class AccueilSecretaire implements Initializable {

    TrayNotification succes = new TrayNotification();
    TrayNotification alert = new TrayNotification();
    private final ObservableList<Patient> data = FXCollections.observableArrayList();
    private final FilteredList<Patient> filteredData = new FilteredList<>(data, p -> true);
    private final SortedList<Patient> sortedData = new SortedList<>(filteredData);

    @FXML
    private JFXDrawer drawer;

    @FXML
    private VBox vbox;

    @FXML
    private JFXButton activertbbx;
    String mois;
    @FXML
    private JFXButton bloquertbx;

    @FXML
    private JFXCheckBox valide;

    @FXML
    private JFXCheckBox nonvalide;

    @FXML
    private JFXCheckBox tous;

    @FXML
    private Label noml;

    @FXML
    private Label prenoml;

    @FXML
    private Label adressel;
    @FXML
    private Label telephonel;

    @FXML
    private Circle cerccle;

    @FXML
    private Label today;

    @FXML
    private Label label;

    @FXML
    private Label prenomlabel;
    @FXML
    private Pane paneid;
    @FXML
    private Pane pane;

    @FXML
    private JFXTextField matriculetbx;

    @FXML
    private Pane panneau;

    @FXML
    private JFXTextField nomtbx;

    @FXML
    private JFXTextField prenomtbx;

    @FXML
    private JFXTextField adressetbx;

    @FXML
    private JFXTextField telephonetbx;

    @FXML
    private JFXTextField mailtbx;
    @FXML
    private JFXTextField recherchertbx;
    @FXML
    private Label labelimage;
    @FXML
    private JFXCheckBox cbxtous;

    @FXML
    private JFXCheckBox cbxattente;

    @FXML
    private JFXCheckBox cbxvalide;

    @FXML
    private JFXButton Imprimertbx;

    @FXML
    private JFXTextField matriculetbx1;

    @FXML
    private TableView<Patient> tb;

    @FXML
    private TableColumn<?, ?> tm;

    @FXML
    private TableColumn<?, ?> tn;

    @FXML
    private TableColumn<?, ?> tp;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private TableColumn<?, ?> ta;
    int n;
    int cpt;
    private File patient = new File("src/images/patient.png");
    private File file = new File("src/images/back.png");
    List<Patient> lp;
    List<Ticket> lt;
    Patient admin = null;
    java.util.Calendar time = java.util.Calendar.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            paneid.setVisible(false);
            mois = mois();
            lp = new ArrayList<>();
            lt = new ArrayList<>();
            IPatient stub = (IPatient) Naming.lookup("rmi://localhost:1099/patient");
            lp = stub.listePatient();
            lt = stub.listeticket();
            setCellTable();
            LoadEtudiantFromBase();
            activertbbx.setVisible(false);
            bloquertbx.setVisible(false);
    
            cerccle.setStroke(Color.WHITE);

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

            VBox box = FXMLLoader.load(getClass().getResource("VboxComptable.fxml"));
            drawer.setSidePane(box);
            HamburgerBackArrowBasicTransition burger = new HamburgerBackArrowBasicTransition();
            burger.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                burger.setRate(burger.getRate() * -1);
                burger.play();
                if (drawer.isShown()) {
                    drawer.close();
                    drawer.setVisible(false);

                } else {
                    drawer.setVisible(true);
                    drawer.open();
                }
            });
            
               alert.setAnimationType(AnimationType.POPUP);
            alert.setNotificationType(NotificationType.ERROR);
              succes.setAnimationType(AnimationType.POPUP);
            succes.setNotificationType(NotificationType.SUCCESS);
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
            System.out.println(e.getLocalizedMessage());
        }
        alert.setMessage("ATTENTION");
        alert.setTitle("INFORMATION");
     

        bindToTime();
//        backimage();
    }

    public void back() {
        LoadView.showView("connexion", "AccueilComptable.fxml", 1);
    }

//    public void backimage() {
//        try {
//            initDefaultImage();
//            BufferedImage img = null;
//            img = ImageIO.read(file);
//            Image image = SwingFXUtils.toFXImage(img, null);
//            ImageView imv = new ImageView(image);
//            imv.setFitHeight(92);
//            imv.setFitWidth(92);
//            cercle.setFill(new ImagePattern(image));
//        } catch (Exception e) {
//        }
//    }
    public String pad(int fieldWidth, char padChar, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < fieldWidth; i++) {
            sb.append(padChar);
        }
        sb.append(s);

        return sb.toString();
    }

    public void deconnecter() {
        LoadView.showView("connexion", "Authentification.fxml", 1);
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

//    private void initDefaultImage() {
//
//        BufferedImage img = null;
//        try {
//
//            ImageView imageview = new ImageView("/images/5.png");
//            imageview.setFitWidth(90);
//            imageview.setFitHeight(100);
//            image.setGraphic(imageview);
//
//        } catch (Exception e) {
//        }
//    }
    public void print() {
        try {
            print.pane = pane;
            print.getDocument();
            LoadView.showView("connexion", "AccueilComptable.fxml", 1);
        } catch (Exception e) {
        }
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

    private void setCellTable() {

        tm.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        tn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tp.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ta.setCellValueFactory(new PropertyValueFactory<>("telephone"));

    }
  
    public void LoadEtudiantFromBase() {
        try {
            data.clear();
            cbxattente.setSelected(false);
            cbxvalide.setSelected(false);
       
            cbxtous.setSelected(true);

            for (Patient patient : lp) {
                {
                    for (Ticket ticket : lt) {
                        if (ticket.getMatricule().equals(patient.getMatricule())) {
                            data.add(patient);
                        }
                    }

                }

            }

            tb.setItems(data);
        } catch (Exception ex) {
            System.out.println(ex);
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

//        for (Patient m : l) {
//            if (m.getMatricule().equals(matriculetbx.getText())) {
//                admin = new Patient();
//                admin = m;
//                panneau.setVisible(true);
//                Enregistrertbx.setVisible(true);
//                Imprimertbx.setVisible(true);
//                matriculetbx.setText(m.getMatricule());
//                nomtbx.setText(m.getNom());
//                prenomtbx.setText(m.getPrenom());
//                adressetbx.setText(m.getAdresse());
//                telephonetbx.setText(m.getTelephone());
//
//                try {
//
//                    BufferedImage img = null;
//
//                    Image images = SwingFXUtils.toFXImage(img, null);
//                    ImageView imv = new ImageView(images);
//
//                    imv.setFitWidth(90);
//                    imv.setFitHeight(100);
//                    image.setGraphic(imv);
//
//                } catch (Exception e) {
//                }
//                break;
//            } else if (matriculetbx.getText().equals("")) {
//                alert.setMessage("Indiquer Le matricule");
//                alert.showAndDismiss(Duration.seconds(2));
//            } else {
//
//                alert.setMessage("Le matricule Nexiste Pas");
//                alert.showAndDismiss(Duration.seconds(2));
//                panneau.setVisible(false);
//                Enregistrertbx.setVisible(false);
//                Imprimertbx.setVisible(false);
//
//            }
//
//        }
//        {
//
//        }
    }

    public void retour2() {
    }

    public void selectionner() {
        try {

            Patient m = (Patient) tb.getSelectionModel().getSelectedItem();

            if (m != null) {
                paneid.setVisible(true);
                FadeTransition ft2 = new FadeTransition(Duration.millis(200), paneid);
                            ft2.setDuration(Duration.seconds(0.3));
                            ft2.setFromValue(0);
                            ft2.setToValue(5);
                            ft2.play();
                noml.setText(m.getNom());
                prenoml.setText(m.getPrenom());
                adressel.setText(m.getAdresse());
                telephonel.setText(m.getTelephone());

                IPatient stub = (IPatient) Naming.lookup("rmi://localhost:1099/patient");
                Ticket t = stub.findticket(m.getMatricule());

                if (t.getValidite().getId() == 1) {

                    activertbbx.setVisible(false);
                    bloquertbx.setVisible(true);
                 
                } else if (t.getValidite().getId() == 3) {
                    activertbbx.setVisible(true);
                    bloquertbx.setVisible(false);
                 

                } 
                try {
                    BufferedImage img = null;
                    img = ImageIO.read(new ByteArrayInputStream(m.getPhoto()));

                    Image image = SwingFXUtils.toFXImage(img, null);
                    ImageView imv = new ImageView(image);
                             imv.setFitHeight(100);
            imv.setFitWidth(120);
            
                    labelimage.setGraphic(imv);
                
                } catch (Exception e) {
                }
            } else {
                paneid.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            paneid.setVisible(false);
        }
    }

    public void releasematricule() {

        matriculetbx1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(medecin -> {

                if (newValue == null || newValue.isEmpty()) {

                    return true;

                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (medecin.getMatricule().toLowerCase().contains(lowerCaseFilter)) {

                    return true;
                } else if (medecin.getPrenom().toLowerCase().contains(lowerCaseFilter)) {

                    return true;
                }

                return false;

            });
        }
        );
        sortedData.comparatorProperty().bind(tb.comparatorProperty());

        tb.setItems(sortedData);

    }

    public void activer() {
        try {

            Patient et = (Patient) tb.getSelectionModel().getSelectedItem();
            Ticket ad = new Ticket();
            ad.setMatricule(et.getMatricule());
            IPatient stub = (IPatient) Naming.lookup("rmi://localhost:1099/patient");

            stub.activer(ad);
            succes.setMessage("TICKET ACTIVER");
            succes.showAndDismiss(Duration.seconds(2));

            activertbbx.setVisible(false);
         
            bloquertbx.setVisible(true);
          
           
            LoadEtudiantFromBaseValide();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
//

    public void bloquer() {
        try {

            Patient et = (Patient) tb.getSelectionModel().getSelectedItem();
            Ticket ad = new Ticket();
            ad.setMatricule(et.getMatricule());
            IPatient stub = (IPatient) Naming.lookup("rmi://localhost:1099/patient");

            stub.bloquer(ad);
            succes.setMessage("TICKET BLOQUER");
            succes.showAndDismiss(Duration.seconds(2));
            LoadEtudiantFromBase();
            LoadEtudiantFromBaseAttente();
    
            LoadEtudiantFromBaseValide();
            activertbbx.setVisible(false);
            bloquertbx.setVisible(true);
          

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void LoadEtudiantFromBaseValide() {
        try {
            data.clear();

            cbxattente.setSelected(false);
            cbxvalide.setSelected(true);
      
            cbxtous.setSelected(false);

            lp.forEach((patient) -> {
                for (Ticket ticket : lt) {
                    if (patient.getMatricule().equals(ticket.getMatricule()) && ticket.getValidite().getId() == 1) {
                        data.add(patient);
                    }
                }
            });

            tb.setItems(data);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

   

    public void LoadEtudiantFromBaseAttente() {
        try {
            data.clear();

            cbxattente.setSelected(true);
            cbxvalide.setSelected(false);
       
            cbxtous.setSelected(false);

            lp.forEach((patient) -> {
                for (Ticket ticket : lt) {
                    if (patient.getMatricule().equals(ticket.getMatricule()) && ticket.getValidite().getId() == 3) {
                        data.add(patient);
                    }
                }
            });

            tb.setItems(data);
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
