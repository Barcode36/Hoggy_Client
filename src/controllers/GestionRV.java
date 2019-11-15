package controllers;

import Vues.LoadView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.Animation;
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
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import jpaModel.Administrateur;
import jpaModel.Allergie;
import jpaModel.Consultations;
import jpaModel.Historique;
import jpaModel.Medecin;
import jpaModel.Observation;
import jpaModel.Patient;
import jpaModel.Profil;
import jpaModel.RendezVous;
import jpaModel.Sexe;
import services.IConsultation;
import services.IMedecin;
import services.IPatient;
import services.IUser;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utiles.GoogleMailValide;
import utiles.print;

public class GestionRV implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Label nomtbx;

    @FXML
    private Label prenomtbx;

    @FXML
    private Label adressetbx;

    @FXML
    private Label telephonetbx;

    @FXML
    private Label consultertbx;

    @FXML
    private Label datetbx;

    @FXML
    private Pane panedetail;
    @FXML
    private TextArea conclusiontbx;
    @FXML
    private Circle cerccle;
    @FXML
    private Label today;
    @FXML
    private Label prenom;

    @FXML
    private Label label;

    @FXML
    private Label docname;

    @FXML
    private Circle cercle;

    @FXML
    private ScrollPane sp;

    @FXML
    private Pane pane;

    @FXML
    private Label name;

    @FXML
    private Label naiss;

    @FXML
    private Label mat;

    @FXML
    private JFXTextField champtbx;

    @FXML
    private Label image;

    @FXML
    private Label myphoto;

    @FXML
    private TableView<Classe> table;

    @FXML
    private TableColumn<?, ?> matcl;

    @FXML
    private TableColumn<?, ?> nomcl;

    @FXML
    private TableColumn<?, ?> prenomcl;

    @FXML
    private TableColumn<?, ?> adressecl;

    @FXML
    private TableColumn<?, ?> naissancecl;

    @FXML
    private TableColumn<?, ?> consultationcl;

    @FXML
    private TableColumn<?, ?> Telephonecl;

    @FXML
    private TableColumn<?, ?> doccl;

    @FXML
    private JFXButton newbtn;

    @FXML
    private JFXButton listebtn;
    private final ObservableList<Classe> data = FXCollections.observableArrayList();

    private final FilteredList<Classe> filteredData = new FilteredList<>(data, p -> true);
    private final SortedList<Classe> sortedData = new SortedList<>(filteredData);

    TrayNotification succes = new TrayNotification();
    TrayNotification alert = new TrayNotification();

    FileChooser f = new FileChooser();
    FileChooser f2 = new FileChooser();
    FileChooser f3 = new FileChooser();

    File files = new File("src/images/back.png");
    File files2 = new File("src/images/back.png");
    File files3 = new File("src/images/back.png");

    Medecin med = new Medecin();
    private File file = new File("src/images/back.png");

    @FXML
    private ImageView updateimg;

    @FXML
    private Pane paneformulaire;

    @FXML
    private JFXTextField fadresse;

    @FXML
    private JFXTextField fmail;

    @FXML
    private JFXTextField ftelephone;

    @FXML
    private JFXDatePicker frendvous;
    private static Label jobStatus = new Label();
    private Label pren;
    List<Patient> ll = new ArrayList<>();
    String mois;
    Allergie allergie = new Allergie();
    Historique historique = new Historique();
    Observation observation = new Observation();
    List<RendezVous> l;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mois = mois();
            panedetail.setVisible(false);
            paneformulaire.setVisible(false);
            setCellTable();
            LoadEtudiantFromBase();
            FileReader f2 = new FileReader("matricule.txt");
            int pos2 = 0;
            String sortie2 = "";
            while (pos2 != -1) {
                char car2 = (char) pos2;
                sortie2 = sortie2 + car2;
                pos2 = f2.read();
            }

            f2.close();

            IPatient stub = (IPatient) Naming.lookup("rmi://localhost:1099/patient");
            IMedecin stub2 = (IMedecin) Naming.lookup("rmi://localhost:1099/medecin");
            med = stub2.find(sortie2);

            ll = stub.listePatient();

            Path p = Paths.get("photo.txt");
            byte[] data = Files.readAllBytes(p);

            BufferedImage img = null;
            img = ImageIO.read(new ByteArrayInputStream(data));
            Image image = SwingFXUtils.toFXImage(img, null);
            ImageView imv = new ImageView(image);
            imv.setFitHeight(92);
            imv.setFitWidth(92);
    
               alert.setAnimationType(AnimationType.POPUP);
            alert.setNotificationType(NotificationType.ERROR);
              succes.setAnimationType(AnimationType.POPUP);
            succes.setNotificationType(NotificationType.SUCCESS);
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
            prenom.setText(sortie);

            f.close();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        sp.setContent(pane);
        alert.setMessage("ATTENTION");
        alert.setTitle("INFORMATION");
        alert.setAnimationType(AnimationType.POPUP);
        alert.setNotificationType(NotificationType.ERROR);
        try {
            bindToTime();

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

    public void back() {
        LoadView.showView("connexion", "AccueilMedecin.fxml", 1);
    }

    int cpt = 0;

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

    public void nouvelle() {
        LoadView.showView("connexion", "Consultation.fxml", 1);
    }

    public void liste() {
        LoadView.showView("connexion", "GestionRV.fxml", 1);
    }

    private void setCellTable() {

        matcl.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nomcl.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcl.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adressecl.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        consultationcl.setCellValueFactory(new PropertyValueFactory<>("dateconcultation"));
        Telephonecl.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        doccl.setCellValueFactory(new PropertyValueFactory<>("docteur"));
        naissancecl.setCellValueFactory(new PropertyValueFactory<>("naissance"));

    }

    public void releasematricule() {

        champtbx.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(medecin -> {

                if (newValue == null || newValue.isEmpty()) {

                    return true;

                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (medecin.getMatricule().toLowerCase().contains(lowerCaseFilter)) {

                    return true;
                } else if (medecin.getMatricule().toLowerCase().contains(lowerCaseFilter)) {

                    return true;
                }

                return false;

            });
        }
        );
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);

    }

    public void LoadEtudiantFromBase() {
        try {
            data.clear();
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");

            l = stub.listeRv();

            l.forEach((ad) -> {
                Classe s = new Classe();
                s.setMatricule(ad.getMatricule());
                s.setDocteur(ad.getIddocteur().getPrenom() + " " + ad.getIddocteur().getNom());
                s.setDateconcultation(ad.getDate().toString());
                s.setNom(ad.getIdpatient().getNom());
                s.setPrenom(ad.getIdpatient().getPrenom());
                s.setAdresse(ad.getIdpatient().getAdresse());
                s.setNaissance(ad.getIdpatient().getNaissance().toString());
                s.setTelephone(ad.getIdpatient().getTelephone());
                data.add(s);
                table.setItems(data);
            });

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void print() {
    }
    String mail;

    public void selectionner() {

        Classe s = table.getSelectionModel().getSelectedItem();
        if (s != null) {
            panedetail.setVisible(true);
            TranslateTransition t = new TranslateTransition();
            t.setNode(panedetail);
            t.setDuration(Duration.seconds(0.3));
            t.setToY(50);
            t.play();

            try {
                IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
                Consultations m = stub.find(s.getMatricule());

                BufferedImage img = null;
                img = ImageIO.read(new ByteArrayInputStream(m.getIdpatient().getPhoto()));

                Image image = SwingFXUtils.toFXImage(img, null);
                ImageView imv = new ImageView(image);
                imv.setFitHeight(90);
                imv.setFitWidth(100);
                myphoto.setGraphic(imv);

                mat.setText(s.getMatricule());
                name.setText(s.getPrenom() + "   " + s.getNom());
                naiss.setText(s.getNaissance().toString());
             
                nomtbx.setText(m.getIdpatient().getNom());
                prenomtbx.setText(m.getIdpatient().getPrenom());
                adressetbx.setText(m.getIdpatient().getAdresse());
                telephonetbx.setText(m.getIdpatient().getTelephone());
                conclusiontbx.setText(m.getConclusion());

                datetbx.setText(s.getDateconcultation());
                consultertbx.setText(m.getIddocteur().getNom() + " " + m.getIddocteur().getPrenom());

                System.out.println("4");
            } catch (Exception e) {
                System.out.println(e);

            }
        } else {
            if (table.getSelectionModel().getSelectedItem() == null) {
                panedetail.setVisible(false);
            }
        }
    }

    public void update() {
        try {
            Classe s = table.getSelectionModel().getSelectedItem();
            if (s != null) {
                paneformulaire.setVisible(true);
                TranslateTransition t = new TranslateTransition();
                t.setNode(paneformulaire);
                t.setDuration(Duration.seconds(0.3));
                t.setToY(50);
                t.play();

            }
        } catch (Exception e) {
        }
    }

    public void deconnecter() {
        LoadView.showView("connexion", "Authentification.fxml", 1);
    }

    public void change() {
        try {
            Classe s = table.getSelectionModel().getSelectedItem();
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            RendezVous r = stub.findRenderVous(s.getMatricule());
            int dif = (int) ChronoUnit.DAYS.between(LocalDate.now(), frendvous.getValue());

            if (dif < 7 || dif > 21 || frendvous.getValue() == null) {
                alert.setMessage("La Date n est pas bon");
                alert.showAndDismiss(Duration.seconds(2));
            } else {
                r.setDate(java.sql.Date.valueOf(frendvous.getValue()));
                stub.updateRenderVous(r);
                stub.updateRenderVousWeb(r);

                GoogleMailValide.nom = s.getNom();
                GoogleMailValide.date = s.getDateconcultation();
                GoogleMailValide.prenom = s.getPrenom();
                GoogleMailValide.valide = "Valider";

                GoogleMailValide.main();
                succes.setMessage("UN EMAIL VOUS A  ETE ENVOYER SUR LE COMPTE DU PATIENT");
                succes.showAndDismiss(Duration.seconds(1));
                LoadEtudiantFromBase();

                paneformulaire.setVisible(false);
                panedetail.setVisible(false);
            }

        } catch (Exception e) {
        }
    }
}
