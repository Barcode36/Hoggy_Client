/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Vues.LoadView;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import jpaModel.Allergie;
import jpaModel.Consultations;
import jpaModel.Historique;
import jpaModel.Hospitalisation;
import jpaModel.Observation;
import jpaModel.Patient;
import services.IConsultation;
import services.IMedecin;
import services.IPatient;
import tray.animations.AnimationType;
import tray.animations.FadeAnimation;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Fallou
 */
public class Hospitalisations implements Initializable {
String mois;  private File file = new File("src/images/back.png");
   private final ObservableList<Classe> data = FXCollections.observableArrayList();

    private final FilteredList<Classe> filteredData = new FilteredList<>(data, p -> true);
    private final SortedList<Classe> sortedData = new SortedList<>(filteredData);
    TrayNotification succes = new TrayNotification();
    TrayNotification alert = new TrayNotification();

    @FXML
    private Pane detail;
    int n;
    int cpt;
    private File patient = new File("src/images/patient.png");
@FXML
    private Circle cercle;
     @FXML
    private JFXTextField champtbx;
   @FXML
    private Circle cerccle;
    @FXML
    private Label name;

    @FXML
    private Label naiss;

    @FXML
    private Label mat;
    @FXML
    private Label datepane;

    @FXML
    private Label motifpane;


    @FXML
    private Label tod;

    @FXML
    private Label label;

    @FXML
    private Label prenomlabel;
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
  List<Hospitalisation> l;
    List<Patient> ll = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        try {
            
          detail.setVisible(false);
           setCellTable();LoadEtudiantFromBase();
            cerccle.setStroke(Color.WHITE);

            Path p = Paths.get("photo.txt");
            byte[] data = Files.readAllBytes(p);

            BufferedImage img = null;
            img = ImageIO.read(new ByteArrayInputStream(data));
            Image image = SwingFXUtils.toFXImage(img, null);
            ImageView imv = new ImageView(image);
            imv.setFitHeight(92);
            imv.setFitWidth(92);
mois= mois();
            cerccle.setFill(new ImagePattern(image));
            cerccle.setFill(new ImagePattern(image));
          //  cerccle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITE));
                
            
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
        alert.setAnimationType(AnimationType.POPUP);
        alert.setNotificationType(NotificationType.ERROR);

        bindToTime();
//        backimage();
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
    public void back() {
        LoadView.showView("connexion", "AccueilMedecin.fxml", 1);
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
        
           tod.setText(LocalDate.now().getDayOfMonth() + "  " + mois);

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

    public void annuller() {
        try {
            LoadView.showView("connexion", "AccueilComptable.fxml", 1);
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

        matcl.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nomcl.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcl.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adressecl.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        consultationcl.setCellValueFactory(new PropertyValueFactory<>("dateconcultation"));
        Telephonecl.setCellValueFactory(new PropertyValueFactory<>("telephone"));
         // doccl.setCellValueFactory(new PropertyValueFactory<>("docteur"));
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
       
          l = stub.listeHospitalisation();
           
            l.forEach((ad) -> {
               Classe s=new Classe();
               s.setMatricule(ad.getMatricule());
             //   s.setDocteur(ad.get.getPrenom()+" "+ad.getIddocteur().getNom());
               s.setDateconcultation(ad.getEntre().toString());
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
         public void selectionner()
{
Classe s=table.getSelectionModel().getSelectedItem();
if(s!=null)
{
    try {
        
       detail.setVisible(true);
         FadeTransition ft2 = new FadeTransition(Duration.millis(200), detail);
                            ft2.setDuration(Duration.seconds(0.3));
                            ft2.setFromValue(0);
                            ft2.setToValue(5);
                            ft2.play();
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
          Hospitalisation h=stub.findHospi(s.getMatricule());
       
        
        if(h!=null)
        {
                    BufferedImage img = null;
                    img = ImageIO.read(new ByteArrayInputStream(h.getIdpatient().getPhoto()));

                    Image image = SwingFXUtils.toFXImage(img, null);
                    ImageView imv = new ImageView(image);
                    imv.setFitHeight(90);
                    imv.setFitWidth(100);
                    myphoto.setGraphic(imv);
                      mat.setText(s.getMatricule());
                name.setText(s.getPrenom() + "   " + s.getNom());
                naiss.setText(s.getNaissance().toString());
                    
                  motifpane.setText(s.getMatricule());
                  datepane.setText(s.getDateconcultation());
        }
             
                } catch (Exception e) {
                    System.out.println(e);
                }
}

}
}
