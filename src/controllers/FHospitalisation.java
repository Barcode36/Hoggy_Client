/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Vues.LoadView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
import java.sql.Date;
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
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.rmi.CORBA.Stub;
import jpaModel.Administrateur;
import jpaModel.Chambre;
import jpaModel.Hospitalisation;
import jpaModel.Comptable;
import jpaModel.Consultations;
import jpaModel.Patient;
import jpaModel.Profil;
import jpaModel.Sexe;
import jpaModel.Ticket;
import jpaModel.Typech;
import jpaModel.Validite;
import services.IComptable;
import services.IConsultation;
import services.IPatient;
import services.IUser;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utiles.Type;
import utiles.print;
import utiles.print2;

/**
 * FXML Controller class
 *
 * @author Fallou
 */
public class FHospitalisation implements Initializable {
    @FXML
    private JFXCheckBox priveescbx;
  @FXML
    private JFXButton imprimer;

    @FXML
    private JFXCheckBox communescbx;

    @FXML
    private JFXCheckBox toutescbx;
    private final ObservableList<Type> data = FXCollections.observableArrayList();

    private final FilteredList<Type> filteredData = new FilteredList<>(data, p -> true);
    private final SortedList<Type> sortedData = new SortedList<>(filteredData);

    private final ObservableList<Classe> datapatient = FXCollections.observableArrayList();

    private final FilteredList<Classe> filteredDatapatient = new FilteredList<>(datapatient, p -> true);
    private final SortedList<Classe> sortedDatapatient = new SortedList<>(filteredDatapatient);

    @FXML
    private Circle cerccle;

    @FXML
    private Label today;

    @FXML
    private Label label;

    @FXML
    private Label prenomlabel;

    @FXML
    private Circle cercle;
    @FXML
    private Pane toprint;
    @FXML
    private Pane pane;

    @FXML
    private Label nompatient;

    @FXML
    private Label prenompatient;

    @FXML
    private Label adressepatient;

    @FXML
    private Label telephonepatient;

    @FXML
    private Label naissancepatient;

    @FXML
    private Label numeroautho;

    @FXML
    private Label numerochambre;

    @FXML
    private Label dateentree;

    @FXML
    private Label matriculetbx;

    @FXML
    private Pane panelistepatient;

    @FXML
    private JFXTextField champstbx;

    @FXML
    private TableView<Classe> tablepatient;

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
    private Pane panechambre;

    @FXML
    private TableView<Type> table;

    @FXML
    private TableColumn<?, ?> matriculecl;

    @FXML
    private TableColumn<?, ?> typecl;

    @FXML
    private TableColumn<?, ?> superficiecl;
   @FXML
    private Label tod;
   @FXML
    private Label 
Photopatient;
    @FXML
    private TableColumn<?, ?> capacitecl;

    @FXML
    private JFXTextField recherchertbx;

    FileChooser f = new FileChooser();
 Patient p=null;
    TrayNotification succes = new TrayNotification();
    TrayNotification alert = new TrayNotification();
  Classe sclasse=null;
    int n;
    int cpt;
    List<Consultations> l;
    private File patient = new File("src/images/patient.png");
    private File file = new File("src/images/back.png");
    private File files = new File("src/images/5.png");
    java.util.Calendar time = java.util.Calendar.getInstance();
 List<Chambre> ls;String mois;
 Type stype;
  int matGen;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            
                imprimer.setVisible(false);
           matGen=(int) (Math.random() * 9999999);
           matriculetbx.setText(matGen+"");
            priveescbx.setSelected(false);
            communescbx.setSelected(false);
            toutescbx.setSelected(true);
            
            IUser stub = (IUser) Naming.lookup("rmi://localhost:1099/admin");
            ls = stub.listeChambre();
            
            setCellTablePatient();
            setCellTableChambre();
            
            LoadEtudiantFromBasePatient();
            LoadEtudiantFromBaseChambre();
            pane.setVisible(false);
            panelistepatient.setVisible(true);
            panechambre.setVisible(false);
            Path p = Paths.get("photo.txt");
            byte[] data = Files.readAllBytes(p);
             mois= mois();
            BufferedImage img = null;
            img = ImageIO.read(new ByteArrayInputStream(data));
            Image image = SwingFXUtils.toFXImage(img, null);
            ImageView imv = new ImageView(image);
            imv.setFitHeight(92);
            imv.setFitWidth(92);

            cerccle.setFill(new ImagePattern(image));
            cerccle.setFill(new ImagePattern(image));
        
            succes.setTitle("OPERATION REUSSITE");
            succes.setAnimationType(AnimationType.SLIDE);
            succes.setNotificationType(NotificationType.SUCCESS);
            
        
            alert.setTitle("ATTENTION");
            alert.setAnimationType(AnimationType.POPUP);
            alert.setNotificationType(NotificationType.ERROR);
            
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
    public void deconnecter() {
        LoadView.showView("connexion", "Authentification.fxml", 1);
    }

    public void backimage() {
        try {

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
        
           today.setText(LocalDate.now().getDayOfMonth() + "  " + mois);

    }

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

    public void enregistrer() {
    }

    public void retour1() {
    }

    public void retour2() {
    }

    public void printe() {
        try {

            try {

                print.pane = pane;
                print.getDocument();

            } catch (Exception e) {
            }
            // LoadView.showView("connexion", "AccueilComptable.fxml", 1);
        } catch (Exception e) {
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


    public void releasematricule() {

        champstbx.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(medecin -> {

                if (newValue == null || newValue.isEmpty()) {

                    return true;

                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (medecin.getMat().toLowerCase().contains(lowerCaseFilter)) {

                    return true;
                } else if (medecin.getSuperficie().toLowerCase().contains(lowerCaseFilter)) {

                    return true;
                }

                return false;

            });
        }
        );
        sortedDatapatient.comparatorProperty().bind(tablepatient.comparatorProperty());

        tablepatient.setItems(sortedDatapatient);

    }

    public void continuer1() {
        TranslateTransition t = new TranslateTransition();
        t.setNode(panelistepatient);
        t.setDuration(Duration.seconds(0.3));
        t.setToX(2000);
        t.play();
        t.setOnFinished((event5) -> {
            panelistepatient.setVisible(false);
            panechambre.setVisible(true);
            pane.setVisible(false);
            TranslateTransition t2 = new TranslateTransition();
            t2.setNode(panechambre);
            t2.setDuration(Duration.seconds(0.3));
            t2.setToX(-1800);
            t2.play();
        });

    }

    public void continuer2() {
        TranslateTransition t = new TranslateTransition();
        t.setNode(panechambre);
        t.setDuration(Duration.seconds(0.3));
        t.setToX(2000);
        t.play();
        t.setOnFinished((event5) -> {
            panelistepatient.setVisible(false);
            pane.setVisible(true);
            panechambre.setVisible(false);
            TranslateTransition t2 = new TranslateTransition();
            t2.setNode(pane);
            t2.setDuration(Duration.seconds(0.3));
            t2.setToX(-1800);
            t2.play();
            imprimer.setVisible(true);
        });

    }

    private void setCellTablePatient() {

        matcl.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nomcl.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcl.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adressecl.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        consultationcl.setCellValueFactory(new PropertyValueFactory<>("dateconcultation"));
        Telephonecl.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        doccl.setCellValueFactory(new PropertyValueFactory<>("docteur"));
        naissancecl.setCellValueFactory(new PropertyValueFactory<>("naissance"));

    }

    public void LoadEtudiantFromBasePatient() {
        try {
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            l = stub.listeConsultations();
            datapatient.clear();

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
                datapatient.add(s);
                tablepatient.setItems(datapatient);
            });

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void selectionnerpatient() {
        try {
                
                sclasse=tablepatient.getSelectionModel().getSelectedItem();
                 p=new Patient();
        if(sclasse!=null)
        {
            for (Consultations type : l) {
                if(  sclasse.getPrenom().equals(type.getIdpatient().getPrenom()) &&  sclasse.getNom().equals(type.getIdpatient().getNom()) && sclasse.getNaissance().equals(type.getIdpatient().getNaissance().toString() ));
                {
                p=type.getIdpatient();
                }
            }
         
        if(p!=null)
        {
            BufferedImage img = null;
                    img = ImageIO.read(new ByteArrayInputStream(p.getPhoto()));

                    Image image = SwingFXUtils.toFXImage(img, null);
                    ImageView imv = new ImageView(image);
                    imv.setFitHeight(90);
                    imv.setFitWidth(100);
                    Photopatient.setGraphic(imv);
        nompatient.setText(p.getNom());
        prenompatient.setText(p.getPrenom());
        adressepatient.setText(p.getAdresse());
        telephonepatient.setText(p.getAdresse());
        naissancepatient.setText(  p.getNaissance().toString().split(" ")[0] + " " + p.getNaissance().toString().split(" ")[1] + " " + p.getNaissance().toString().split(" ")[2]);
     numeroautho.setText(sclasse.getDocteur());
     dateentree.setText(LocalDate.now().toString());
        }
        }
                
                
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
      
    }

    public void selectionner() {
      stype  =table.getSelectionModel().getSelectedItem();
                 
                 if(stype!=null)
                 {
                 numerochambre.setText(stype.getMat());
                 }
    }
public void printhos(){
    try {
        
         IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
         Chambre ch=stub.findChambre(stype.getMat());
         Hospitalisation h=new Hospitalisation();
         h.setMatricule(matriculetbx.getText());
         h.setIdpatient(p);
         h.setIdchambre(ch);
         h.setEntre(Date.valueOf(LocalDate.now()));
         
         stub.InsererHospitalisation(h);
          succes.showAndDismiss(Duration.seconds(2));
        print2.pane=pane;
       
        print2.print(PrinterJob.createPrinterJob( ), pane);
    } catch (Exception e) {
    }

}
    private void setCellTableChambre() {

        matriculecl.setCellValueFactory(new PropertyValueFactory<>("mat"));
        typecl.setCellValueFactory(new PropertyValueFactory<>("type"));
        superficiecl.setCellValueFactory(new PropertyValueFactory<>("superficie"));
        capacitecl.setCellValueFactory(new PropertyValueFactory<>("capacite"));

    }

    public void releasematriculechambre() {

        recherchertbx.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(medecin -> {

                if (newValue == null || newValue.isEmpty()) {

                    return true;

                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (medecin.getMat().toLowerCase().contains(lowerCaseFilter)) {

                    return true;
                } else if (medecin.getSuperficie().toLowerCase().contains(lowerCaseFilter)) {

                    return true;
                }

                return false;

            });
        }
        );
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);

    }

    public void LoadEtudiantFromBaseChambre() {
        try {
            data.clear();

            IUser stub = (IUser) Naming.lookup("rmi://localhost:1099/admin");

            List<Chambre> ls = stub.listeChambre();
        
            ls.stream().forEach((a) -> {
                Type t = new Type();
                t.setMat(a.getMatricule());
                t.setSuperficie(a.getSuperficie());
                t.setType(a.getType().getNom());
                t.setCapacite(a.getCapacite());

                data.add(t);
            });
            table.setItems(data);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
      public void communes(){
    try{
  data.clear();

       priveescbx.setSelected(false);
            communescbx.setSelected(true);
            toutescbx.setSelected(false);
       
            ls.stream().forEach((a) -> {
                if(a.getType().getId()==2)
                {
                Type t = new Type();
                t.setMat(a.getMatricule());
                t.setSuperficie(a.getSuperficie());
                t.setType(a.getType().getNom());
                t.setCapacite(a.getCapacite());

                data.add(t);
                }
            });
            table.setItems(data);
        } catch (Exception ex) {
            System.out.println(ex);
        }

}
    public void privees(){
    try{
  data.clear();

             priveescbx.setSelected(true);
            communescbx.setSelected(false);
            toutescbx.setSelected(false);
        
            ls.stream().forEach((a) -> {
                if(a.getType().getId()==1)
                {
                Type t = new Type();
                t.setMat(a.getMatricule());
                t.setSuperficie(a.getSuperficie());
                t.setType(a.getType().getNom());
                t.setCapacite(a.getCapacite());

                data.add(t);
                }
            });
            table.setItems(data);
        } catch (Exception ex) {
            System.out.println(ex);
        }

}
    
public void toutes(){
    try{
  data.clear();

             priveescbx.setSelected(false);
            communescbx.setSelected(false);
            toutescbx.setSelected(true);
            List<Type> lt = new ArrayList<>();
            ls.stream().forEach((a) -> {
                Type t = new Type();
                t.setMat(a.getMatricule());
                t.setSuperficie(a.getSuperficie());
                t.setType(a.getType().getNom());
                t.setCapacite(a.getCapacite());

                data.add(t);
            });
            table.setItems(data);
        } catch (Exception ex) {
            System.out.println(ex);
        }

}
}
