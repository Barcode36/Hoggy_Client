package controllers;

import Vues.LoadView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.control.TextArea;
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
import jpaModel.ConsultationWeb;
import jpaModel.Consultations;
import jpaModel.Historique;
import jpaModel.Medecin;
import jpaModel.Observation;
import jpaModel.Ordonnance;
import jpaModel.OrdonnanceWeb;
import jpaModel.Patient;
import jpaModel.Profil;
import jpaModel.RenderVousWeb;
import jpaModel.RendezVous;
import services.IConsultation;
import services.IMedecin;
import services.IPatient;
import services.IUser;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utiles.print;

public class Consultation implements Initializable {
 @FXML
    private JFXTextField medicament1tbx;

    @FXML
    private JFXTextField medicament2tbx;

    @FXML
    private JFXTextField medicament3tbx;

    @FXML
    private JFXTextField medicament4tbx;

    @FXML
    private JFXTextField medicament5tbx;

    @FXML
    private JFXTextField medicament6tbx;
    @FXML
    private ImageView documentA;
    @FXML
    private JFXCheckBox vision;
    @FXML
    private JFXCheckBox oui;
        @FXML
    private JFXCheckBox non;
            @FXML
    private JFXCheckBox ouih;
                @FXML
    private JFXCheckBox nonh;
    @FXML
    private JFXCheckBox auditif;
  @FXML
    private Label daterv1;

    @FXML
    private Label parrv1;

    @FXML
    private Label daterv2;

    @FXML
    private Label parrv2;

    @FXML
    private JFXDatePicker daterv;

    @FXML
    private Label daterv3;

    @FXML
    private Label parrv3;
    @FXML
    private JFXCheckBox langage;

    @FXML
    private JFXCheckBox physique;

    @FXML
    private JFXCheckBox medicament;

    @FXML
    private JFXCheckBox aliment;

    @FXML
    private JFXCheckBox autre;

    @FXML
    private Label ordonnancedakarle;

    @FXML
    private Label Pardr;
    @FXML
    private JFXCheckBox cdiabete;

    @FXML
    private JFXCheckBox ccardiaque;

    @FXML
    private JFXCheckBox ctubercolose;

    @FXML
    private JFXCheckBox cortho;

    @FXML
    private JFXCheckBox caudiovision;

    @FXML
    private JFXCheckBox cautreshisto;
    @FXML
    private JFXCheckBox tuberculose;

    @FXML
    private JFXCheckBox diabete;

    @FXML
    private JFXCheckBox cardiaque;
    Patient p = new Patient();
    @FXML
    private JFXCheckBox ortho;

    @FXML
    private JFXTextArea cconclusion;

    @FXML
    private JFXTextArea conclusion;
    @FXML
    private JFXCheckBox autreshis;
 
    @FXML
    private ImageView documentB;

    @FXML
    private ImageView documentC;

    @FXML
    private Label name;

    @FXML
    private Label naiss;

    @FXML
    private Label mat;
 @FXML
    private Label ordonnanceid;
    @FXML
    private Pane ordonnance;

    @FXML
    private Pane fiche;

    @FXML
    private JFXTextField matriculetbx;

    @FXML
    private JFXTextField nomtbx;

    @FXML
    private JFXTextField prenomtbx;

    @FXML
    private JFXCheckBox masculintbx;

    @FXML
    private JFXCheckBox feminintbx;

    @FXML
    private JFXTextField datetbx;

    @FXML
    private JFXTextField adressetbx;

    @FXML
    private JFXTextField mailtbx;

    @FXML
    private JFXTextField telephonetbx;

    @FXML
    private JFXCheckBox cmedicament;

    @FXML
    private JFXCheckBox calimentaire;
    @FXML
    private JFXCheckBox cauditif;
    @FXML
    private JFXCheckBox cautres;

    @FXML
    private JFXTextField ctaille;

    @FXML
    private JFXTextField cpoid;

    @FXML
    private JFXTextField ctemp;

    @FXML
    private JFXTextField cpression;

    @FXML
    private JFXCheckBox clangage;

    @FXML
    private JFXCheckBox ccommunication;

    @FXML
    private JFXCheckBox cphysique;
    @FXML
    private JFXButton newbtn;

    @FXML
    private JFXButton listebtn;
    @FXML
    private JFXCheckBox cvision;
    @FXML
    private Label label;
    @FXML
    private Label datelabel;
     @FXML
    private Label prevutbx;

    @FXML
    private JFXTextField champtbx;

    @FXML
    private Label image;

    TrayNotification succes = new TrayNotification();
    TrayNotification alert = new TrayNotification();
    @FXML

    private JFXCheckBox cdentition;
    @FXML
    private JFXCheckBox dentition;
    @FXML
    private JFXCheckBox communication;
    @FXML
    private JFXCheckBox corl;

    @FXML
    private Label myphoto;
    @FXML
    private JFXSlider poidslider;

    @FXML
    private JFXSlider tailleslider;

    @FXML
    private JFXSlider temperatureslider;

    @FXML
    private JFXSlider pressionslider;
    @FXML
    private Label poidtbx;
    FileChooser f = new FileChooser();
        FileChooser f2 = new FileChooser();
            FileChooser f3 = new FileChooser();
            String mois; 
    File files =new File("src/images/back.png");
    File files2 = new File("src/images/back.png");
    File files3 = new File("src/images/back.png");

    @FXML
    private Label tailletbx;

    @FXML
    private Label pressiontbx;

    @FXML
    private Label temperaturetbx;
    @FXML
    private Circle cercle;
    @FXML
    private Pane pane;
    @FXML
    private Label parlabel;
    @FXML
    private Label docname;
    @FXML
    private Label today;
    Medecin med = new Medecin();
    private File file = new File("src/images/back.png");
    @FXML
    private ScrollPane sp;
    private static Label jobStatus = new Label();
     @FXML
    private Label prenomlabel;
    List<Patient> ll = new ArrayList<>();
    @FXML
    private Circle cerccle;
    Allergie allergie=new Allergie();
    Historique historique=new Historique();
    Observation observation=new Observation();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           poidslider.setValue(0.0);
               temperatureslider.setValue(0.0);
                   tailleslider.setValue(0.0);   
                   pressionslider.setValue(0.0);
                       
               alert.setAnimationType(AnimationType.POPUP);
            alert.setNotificationType(NotificationType.ERROR);
              succes.setAnimationType(AnimationType.POPUP);
            succes.setNotificationType(NotificationType.SUCCESS);
         mois=mois();
            FileReader f2 = new FileReader("matricule.txt");
            int pos2 = 0;
            String sortie2 = "";
            while (pos2 != -1) {
                char car2 = (char) pos2;
                sortie2 = sortie2 + car2;
                pos2 = f2.read();
            }
        
            f2.close();

            
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
                Pardr.setText( "Par Docteur:" +sortie);
                parlabel.setText("Par Docteur: "+sortie);
               datelabel.setText("Dakar le: "+LocalDate.now().toString());
              
            
            
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

            cerccle.setFill(new ImagePattern(image));
            cerccle.setFill(new ImagePattern(image));
          //  cerccle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITE));


        } catch (Exception e) {
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
public void deconnecter(){
     LoadView.showView("connexion", "Authentification.fxml", 1);
     }
    public void back() {
        LoadView.showView("connexion", "AccueilMedecin.fxml", 1);
    }

    int cpt = 0;

    public void rechercher() {
        try {
            cpt++;
            f = new FileChooser();
            f.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("ALL Images", "*.jpg", "*.jpeg"),
                    new FileChooser.ExtensionFilter("JPG", "*jpg"));
            
               f2 = new FileChooser();
            f2.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("ALL Images", "*.jpg", "*.jpeg"),
                    new FileChooser.ExtensionFilter("JPG", "*jpg"));
            
               f3 = new FileChooser();
            f3.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("ALL Images", "*.jpg", "*.jpeg"),
                    new FileChooser.ExtensionFilter("JPG", "*jpg"));
       
            
            

            if (cpt % 2 == 0) {
                files = f.showOpenDialog(new Stage());
                Image m = new Image("file:///" + files.toPath().toString());
                documentB.setImage(m);
            } else if (cpt % 3 == 0) {
                files2 = f2.showOpenDialog(new Stage());
                Image m = new Image("file:///" + files2.toPath().toString());
                documentC.setImage(m);
            } else {
                files3 = f3.showOpenDialog(new Stage());
                Image m = new Image("file:///" + files3.toPath().toString());
                documentA.setImage(m);
            }

        } catch (Exception e) {
        }

    }

    public void releasematricule() {

    }

    public void shows() {

        try {
            print.pane = fiche;
            print.getDocument();
        } catch (Exception e) {
        }
    }

    public void print() {
        try {

            Consultations c = new Consultations();
            int mat=(int) (Math.random() * 9999999);
            c.setMatricule(String.valueOf(mat));
            c.setPression(pressiontbx.getText());
            c.setTemperature(temperaturetbx.getText());
            c.setPoids(poidtbx.getText());
            c.setDate(Date.valueOf(LocalDate.now()));
            c.setTaille(tailletbx.getText());
            
            
    
             
            
            byte[] t = Files.readAllBytes(files.toPath());
            
            c.setDocumentA(t);

            byte[] t2 = Files.readAllBytes(files2.toPath());
            c.setDocumentB(t2);

            byte[] t3 = Files.readAllBytes(files3.toPath());
            c.setDocumentC(t3);

            c.setConclusion(conclusion.getText());
        if(ouih.isSelected())
      
        {
                    IPatient i = (IPatient) Naming.lookup("rmi://localhost:1099/patient");
                    i.ModifierPatient(p);
        }
           
            c.setIdpatient(p);
            c.setIddocteur(med);
        
  
        
           historique=new Historique();
           observation=new Observation();
           allergie=new Allergie();
           
           allergie();observation(); antecedent();
           
           allergie.setIdconsultation(mat);
           observation.setIdconsultation(mat);
           historique.setIdconsultation(mat);
           
           RendezVous rv=new RendezVous();
          int dif=(int) ChronoUnit.DAYS.between(LocalDate.now(),daterv.getValue());
          
           
          
           rv.setDate(java.sql.Date.valueOf(daterv.getValue()));
           rv.setIddocteur(med);
           rv.setIdpatient(p);
           
           rv.setMatricule(c.getMatricule());
           
           Ordonnance or=new Ordonnance();
            OrdonnanceWeb ow=new OrdonnanceWeb();
            
           or.setDate(java.sql.Date.valueOf(LocalDate.now()));
           or.setDaterv(java.sql.Date.valueOf(daterv.getValue()));
           or.setIdpatient(p);
           or.setIddocteur(med);
          
            
           or.setMatricule(String.valueOf(mat));
           or.setMedicament1(medicament1tbx.getText());
            or.setMedicament2(medicament2tbx.getText());
             or.setMedicament3(medicament3tbx.getText());
              or.setMedicament4(medicament4tbx.getText()); 
              or.setMedicament6(medicament6tbx.getText());
               or.setMedicamen5(medicament5tbx.getText());
               or.setMedicament6(medicament6tbx.getText());
               
               
               ow.setMatricule(c.getIdpatient().getMatricule());
             ow.setMedicament1(medicament1tbx.getText());
            ow.setMedicament2(medicament2tbx.getText());
             ow.setMedicament3(medicament3tbx.getText());
              ow.setMedicament4(medicament4tbx.getText()); 
              ow.setMedicament6(medicament6tbx.getText());
               ow.setMedicament5(medicament5tbx.getText());
               ow.setDocteur(med.getPrenom()+" "+med.getPrenom()+" "+med.getTelephone());
               ow.setDatepreinscription(Date.valueOf(LocalDate.now()));
               ow.setNom(p.getNom());
               ow.setPrenom(p.getPrenom());
               ow.setNaissance(p.getNaissance());
               Integer l=mat;
               ow.setId(l.longValue());
             ordonnanceid.setText(String.valueOf(mat));
             
               
               ConsultationWeb cw=new ConsultationWeb();
               Integer id=mat;
               Long n=id.longValue();
               cw.setId(n);
               cw.setConsultation(c.getDate());
               cw.setNom(c.getIdpatient().getNom());
               cw.setPrenom(c.getIdpatient().getPrenom());
               cw.setDocteur(c.getIddocteur().getNom()+" "+c.getIddocteur().getPrenom());
               cw.setMatricule(c.getIdpatient().getMatricule());
               cw.setDaterv(rv.getDate());
               
               RenderVousWeb rw=new RenderVousWeb();
                  rw.setConsultation(c.getDate());
               rw.setNom(c.getIdpatient().getNom());
               rw.setPrenom(c.getIdpatient().getPrenom());
               rw.setDocteur(c.getIddocteur().getNom()+" "+c.getIddocteur().getPrenom());
               rw.setMatricule(c.getIdpatient().getMatricule());
               rw.setDaterv(rv.getDate());
               rw.setId(n);
               
            
         if(dif<7 ||dif>21 || daterv.getValue()==null)
           {
           alert.setMessage("La Date n est pas bon");
            alert.showAndDismiss(Duration.seconds(2));
           }
           else
             if  (p==null)
             {
              alert.setMessage("Aucun patient n a ete choisie");
            alert.showAndDismiss(Duration.seconds(2));
             }
           else
             {
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            stub.InsererConsultation(c);
            stub.InsererHistorique(historique);
            stub.InsererObservation(observation);
            
             stub.InsererRenderVousWeb(rw);
            stub.InsererOrdonnanceWeb(ow);
            stub.InsererConsultationWeb(cw);
            stub.InsererAllergie(allergie);
            stub.InsererRV(rv);
             stub.InsererOrdonnance(or);
             
             
            succes.setMessage("Consultation ENREGISTREE AVEC SUCCES");
            succes.showAndDismiss(Duration.seconds(2));
            shows();
               LoadView.showView("connexion", "AccueilMedecin.fxml", 1);
             }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void sliderValuePoid() {
        poidtbx.setText((int) poidslider.getValue() + "");
        if (poidslider.getValue() < 5) {
            poidtbx.setStyle("-fx-background-color:red");
        } else if (poidslider.getValue() > 200) {
            poidtbx.setStyle("-fx-background-color:red");
        } else {
            poidtbx.setStyle("-fx-background-color:#20da08");
        }
        cpoid.setText((int) poidslider.getValue() + "");

    }

    public void conclusion() {
        cconclusion.clear();
        cconclusion.setText(conclusion.getText());
    }

    public void sliderValuetemperature() {
        temperaturetbx.setText((int) temperatureslider.getValue() + "");
        if (temperatureslider.getValue() < 35) {
            temperaturetbx.setStyle("-fx-background-color:red");
        } else if (temperatureslider.getValue() > 37) {
            temperaturetbx.setStyle("-fx-background-color:red");
        } else {
            temperaturetbx.setStyle("-fx-background-color:#20da08");
        }
        ctemp.setText((int) temperatureslider.getValue() + "");
    }

    public void sliderValuepression() {
        pressiontbx.setText((int) pressionslider.getValue() + "");
        if (pressionslider.getValue() < 5) {
            pressiontbx.setStyle("-fx-background-color:red");
        } else if (pressionslider.getValue() > 200) {
            pressiontbx.setStyle("-fx-background-color:red");
        } else {
            pressiontbx.setStyle("-fx-background-color:#20da08");
        }
        cpression.setText((int) pressionslider.getValue() + "");
    }

    public void sliderValuetaille() {
        tailletbx.setText((int) tailleslider.getValue() + "");
        if (tailleslider.getValue() < 10) {
            tailletbx.setStyle("-fx-background-color:red");
        } else if (tailleslider.getValue() > 2000) {
            tailletbx.setStyle("-fx-background-color:red");
        } else {
            tailletbx.setStyle("-fx-background-color:#20da08");
        }
        ctaille.setText((int) tailleslider.getValue() + "");
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
        today.setText(LocalDate.now().getDayOfMonth() + "  " +mois);
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

    public void chercher() {
 ordonnancedakarle.setText(""); ordonnancedakarle.setText(LocalDate.now().toString());
 Pardr.setText("");Pardr.setText(med.getNom()+"  "+med.getPrenom());
        for (Patient m : ll) {
            if (m.getMatricule().equals(champtbx.getText())) {

                p = m;
                mat.setText(mat.getText() + "   " + m.getMatricule());
                name.setText(name.getText() + " " + m.getPrenom() + "   " + m.getNom());
                naiss.setText(naiss.getText() + "   " + m.getNaissance());
                nomtbx.setText(m.getNom());
                prenomtbx.setText(m.getPrenom());
                adressetbx.setText(m.getAdresse());
                telephonetbx.setText(m.getTelephone());
                mailtbx.setText(m.getMail());
                datetbx.setText(m.getNaissance().toString());
                matriculetbx.setText(p.getMatricule());
                if (m.getIdsexe().getId() == 1) {
                    masculintbx.setSelected(true);
                    feminintbx.setSelected(false);
                } else {
                    masculintbx.setSelected(false);
                    feminintbx.setSelected(true);
                }
                try {
                    
                    
                    BufferedImage img = null;
                    img = ImageIO.read(new ByteArrayInputStream(m.getPhoto()));

                    Image image = SwingFXUtils.toFXImage(img, null);
                    ImageView imv = new ImageView(image);
                    imv.setFitHeight(90);
                    imv.setFitWidth(100);
                    myphoto.setGraphic(imv);

                } catch (Exception e) {
                }
                break;
            } else if (champtbx.getText().equals("")) {
                alert.setMessage("Indiquer Le matricule");
                alert.showAndDismiss(Duration.seconds(2));
            } else {

                alert.setMessage("Le matricule Nexiste Pas");
                alert.showAndDismiss(Duration.seconds(2));

            }

           }
   

    }

    public void observation() {

        if (vision.isSelected()) {
            cvision.setSelected(true);
        observation.setVision(1);
        }
        if (communication.isSelected()) {
            ccommunication.setSelected(true);
            observation.setCommunication(1);
        }
        if (langage.isSelected()) {
            clangage.setSelected(true);
            observation.setLangage(1);

        }
        if (auditif.isSelected()) {
            caudiovision.setSelected(true);
            cauditif.setSelected(true);
            observation.setAuditif(1);

        }
        if (physique.isSelected()) {
            cphysique.setSelected(true);
            observation.setPhysique(1);
        }

    }

    public void antecedent() {
        if (cardiaque.isSelected()) {
            ccardiaque.setSelected(true);
            historique.setHypertension(1);
        }
       
        if (tuberculose.isSelected()) {
            ctubercolose.setSelected(true);
            historique.setTuberculose(1);
        }
        if (diabete.isSelected()) {
            cdiabete.setSelected(true);
            historique.setDiabete(1);
        }
        if (ortho.isSelected()) {
            cortho.setSelected(true);
            historique.setDiabete(1);
        }

    }

    public void allergie() {
        if (medicament.isSelected()) {
            cmedicament.setSelected(true);
            allergie.setMedicament(1);
        }
        if (aliment.isSelected()) {
            calimentaire.setSelected(true);
            allergie.setAliments(1);
        }
        if (autre.isSelected()) {
            cautres.setSelected(true);
            allergie.setAutres(1);
        }
    }
    public void nouvelle()
    {
        LoadView.showView("connexion", "Consultation.fxml", 1);
    }
        public void liste()
    {
        LoadView.showView("connexion", "Liste.fxml", 1);
    }
        public void setdaterv(){
            try {
                
          
        prevutbx.setText("");
        prevutbx.setText("Votre prochain Render-Vous est prevu pour:"+daterv.getValue().toString());
        
          } catch (Exception e) {
            }
        
        }
        public void hospi(){
        if(oui.isSelected())
        {
            non.setSelected(false);
            ouih.setSelected(true);
        }
        
          if(non.isSelected())
        {
            oui.setSelected(false);
            nonh.setSelected(true);
        
        }
}}
