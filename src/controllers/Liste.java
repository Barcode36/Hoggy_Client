package controllers;

import Vues.LoadView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.PrintJob;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.Animation;
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
import jpaModel.Sexe;
import services.IConsultation;
import services.IMedecin;
import services.IPatient;
import services.IUser;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utiles.print;
import utiles.print2;

public class Liste implements Initializable {

    
       @FXML
    private AnchorPane root;

    @FXML
    private Circle cerccle;

    @FXML
    private Label today;

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
    private JFXCheckBox ouih;

    @FXML
    private JFXCheckBox nonh;
    @FXML
    private JFXCheckBox masculintbx;

    @FXML
    private Pane panetel1;
    
    @FXML
    private Pane panetel2;
    
    @FXML
    private Pane panetel3;
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
    private JFXCheckBox cautres;

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
  private final ObservableList<Classe> data = FXCollections.observableArrayList();

    private final FilteredList<Classe> filteredData = new FilteredList<>(data, p -> true);
    private final SortedList<Classe> sortedData = new SortedList<>(filteredData);
    @FXML
    private JFXCheckBox cvision;

    @FXML
    private JFXCheckBox cauditif;

    @FXML
    private JFXTextArea cconclusion;

    @FXML
    private Label datelabel;

    @FXML
    private Label parlabel;

    @FXML
    private JFXTextField champtbx;

    @FXML
    private Label image;

    @FXML
    private Label myphoto;

    @FXML
    private TableView<Classe> table;
   @FXML
    private TableColumn<?, ?> doccl;
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
    private JFXButton newbtn;

    @FXML
    private JFXButton listebtn;
    
    
    @FXML
    private ImageView documentA;
    @FXML
    private JFXCheckBox vision;
   @FXML
    private Label photo1;

    @FXML
    private Label photo2;

    @FXML
    private Label photo3;
    @FXML
    private JFXCheckBox auditif;

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
    private JFXCheckBox tuberculose;

    @FXML
    private JFXCheckBox diabete;

    @FXML
    private JFXCheckBox cardiaque;
    Patient p = new Patient();
    @FXML
    private JFXCheckBox ortho;


    @FXML
    private JFXTextArea conclusion;
    @FXML
    private JFXCheckBox autreshis;

    @FXML
    private ImageView documentB;

    @FXML
    private ImageView documentC;

      BufferedImage img1 = null;
    BufferedImage img2 = null;
    BufferedImage img3 = null;
 


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

  String mois="";
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
            
    File files =new File("src/images/back.png");
    File files2 = new File("src/images/back.png");
    File files3 = new File("src/images/back.png");

    @FXML
    private Label tailletbx;

    @FXML
    private Label pressiontbx;
List<Consultations> recupererdoc = new ArrayList<>();
    @FXML
    private Label temperaturetbx;
    @FXML
    private Label medecinname;
   
    Medecin med = new Medecin();
    private File file = new File("src/images/back.png");

    private static Label jobStatus = new Label();
    private Label prenomlabel;
    List<Patient> ll = new ArrayList<>();
       List<Consultations> l;
    Allergie allergie=new Allergie();
    Historique historique=new Historique();
    Observation observation=new Observation();
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mois=mois();
            setCellTable();LoadEtudiantFromBase();
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
            medecinname.setText(sortie);
          
           
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        sp.setContent(pane);
        alert.setMessage("ATTENTION");
        alert.setTitle("INFORMATION");
        alert.setAnimationType(AnimationType.POPUP);
        alert.setNotificationType(NotificationType.ERROR);
        try {
            System.out.println("ici");
            bindToTime();
      System.out.println("ici");
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


    public void shows() {

        try {
            print.pane = fiche;
            print.getDocument();
               LoadView.showView("connexion", "Liste.fxml", 1);
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
            c.setIdpatient(p);
            c.setIddocteur(med);
        
  
        
           historique=new Historique();
           observation=new Observation();
           allergie=new Allergie();
           
         
           
           allergie.setIdconsultation(mat);
           observation.setIdconsultation(mat);
           historique.setIdconsultation(mat);
           
           
            
           
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            stub.InsererConsultation(c);
            stub.InsererHistorique(historique);
            stub.InsererObservation(observation);
            stub.InsererAllergie(allergie);

            succes.setMessage("Consultation ENREGISTREE AVEC SUCCES");
            succes.showAndDismiss(Duration.seconds(2));
            shows();
               LoadView.showView("connexion", "AccueilMedecin.fxml", 1);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

 

    public void conclusion() {
        cconclusion.clear();
        cconclusion.setText(conclusion.getText());
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
      //  today.setText(LocalDate.now().getDayOfMonth() + "  " + LocalDate.now().getMonth());
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
    public void nouvelle()
    {
        LoadView.showView("connexion", "Consultation.fxml", 1);
    }
        public void liste()
    {
        LoadView.showView("connexion", "Liste.fxml", 1);
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
       
          l = stub.listeConsultations();
           
            l.forEach((ad) -> {
               Classe s=new Classe();
               s.setMatricule(ad.getMatricule());
                  s.setDocteur(ad.getIddocteur().getPrenom()+" "+ad.getIddocteur().getNom());
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
         
         
public void deconnecter(){
     LoadView.showView("connexion", "Authentification.fxml", 1);
     }
public void selectionner()
{
  
Classe s=table.getSelectionModel().getSelectedItem();
if(s!=null)
{
    try {
        
        IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
          Consultations  m= stub.find(s.getMatricule());
                    if (m.getDocumentA() != null) {

                        img1 = ImageIO.read(new ByteArrayInputStream(m.getDocumentA()));
                        Image image = SwingFXUtils.toFXImage(img1, null);
                        ImageView imv = new ImageView(image);
                        imv.setFitHeight(50);
                        imv.setFitWidth(50);
                        photo1.setGraphic(imv);

                    } else {
                        photo1.setGraphic(null);
                    }
                    if (m.getDocumentB() != null) {

                        img2 = ImageIO.read(new ByteArrayInputStream(m.getDocumentB()));
                        Image image = SwingFXUtils.toFXImage(img2, null);
                        ImageView imv = new ImageView(image);
                        imv.setFitHeight(50);
                        imv.setFitWidth(50);
                        photo2.setGraphic(imv);

                    } else {
                        photo1.setGraphic(null);
                    }
                    if (m.getDocumentC() != null) {

                        img3 = ImageIO.read(new ByteArrayInputStream(m.getDocumentC()));
                        Image image = SwingFXUtils.toFXImage(img3, null);
                        ImageView imv = new ImageView(image);
                       imv.setFitHeight(50);
                        imv.setFitWidth(50);
                        photo3.setGraphic(imv);

                    } else {
                        photo1.setGraphic(null);
                    }

         
         
         
    
        //  System.out.println(m.getIdpatient().getAdresse());
          Allergie a=stub.findAllergie(m.getMatricule());
          Observation obs=stub.findObservation(m.getMatricule());
         Historique histo =stub.findHistorique(m.getMatricule());
        
        
                    BufferedImage img = null;
                    img = ImageIO.read(new ByteArrayInputStream(m.getIdpatient().getPhoto()));

                    Image image = SwingFXUtils.toFXImage(img, null);
                    ImageView imv = new ImageView(image);
                    imv.setFitHeight(90);
                    imv.setFitWidth(100);
                    myphoto.setGraphic(imv);
                    
                    
                   
                    
                mat.setText( m.getMatricule());
                name.setText( s.getPrenom() + "   " + s.getNom());
                naiss.setText( s.getNaissance());
                nomtbx.setText(s.getNom());
                prenomtbx.setText(s.getPrenom());
                adressetbx.setText(s.getAdresse());
                telephonetbx.setText(s.getTelephone());
                mailtbx.setText(m.getIdpatient().getMail());
                datetbx.setText(s.getNaissance());
                matriculetbx.setText(s.getMatricule());
                datelabel.setText(s.getDateconcultation().toString());
                parlabel.setText(s.getDocteur());
                if (m.getIdpatient().getIdsexe().getId() == 1) {
                    masculintbx.setSelected(true);
                    feminintbx.setSelected(false);
                } else {
                    masculintbx.setSelected(false);
                    feminintbx.setSelected(true);
                }
                
                
             ctaille.setText(m.getTaille());
             cpoid.setText(m.getPoids());
             ctemp.setText(m.getTemperature());
             cpression.setText(m.getPression());
             cconclusion.setText(m.getConclusion());
             System.out.println(m.getIdpatient().getHosptaliser());
             if(m.getIdpatient().getHosptaliser()==1)
             {
             ouih.setSelected(true);
             }
             else
             {
             nonh.setSelected(true);
             }
             
             if(obs.getCommunication()==1)
                 ccommunication.setSelected(true);
             else
               ccommunication.setSelected(false);
             
             if(obs.getAuditif()==1)
                 cauditif.setSelected(true);
             else
                  cauditif.setSelected(false); 
             
             if(obs.getLangage()==1)
             
                   clangage.setSelected(true);
             else
                  clangage.setSelected(false); 
             
             if(obs.getVision()==1)
                        cvision.setSelected(true);
             else
                  cvision.setSelected(false); 
             
             if(obs.getPhysique()==1)
             
                    cphysique.setSelected(true);
             else
                  cphysique.setSelected(false); 
           
             if(a.getAliments()==1)
                 
              
                    calimentaire.setSelected(true);
             else
                  calimentaire.setSelected(false); 
             
              if(a.getMedicament()==1)
                 
              
                    cmedicament.setSelected(true);
             else
                  cmedicament.setSelected(false); 
              if(a.getAutres()==1)
                 
              
                    cautres.setSelected(true);
             else
                  cautres.setSelected(false); 
              
              if(histo.getDiabete()==1)
                cdiabete.setSelected(true);
             else
                  cdiabete.setSelected(false); 
              
              if(histo.getOrthepedique()==1)
                cortho.setSelected(true);
             else
                  cortho.setSelected(false); 
              
              if(histo.getHypertension()==1)
                ccardiaque.setSelected(true);
             else
                  ccardiaque.setSelected(false); 
              
              if(histo.getTuberculose()==1)
                ctubercolose.setSelected(true);
             else
                  ctubercolose.setSelected(false); 
             
              
              if(histo.getAutre()==1)
                cautreshisto.setSelected(true);
             else
                  cautreshisto.setSelected(false); 
             
             
             
                } catch (Exception e) {
                    System.out.println(e);
                }
}

}
    public void download1(){
    print2.pane=panetel1;
    print2.print(PrinterJob.createPrinterJob(), panetel1);
    
    }
        public void download2(){
    
       print2.pane=panetel2;
    print2.print(PrinterJob.createPrinterJob(), panetel2);
    }
        public void download3(){
       print2.pane=panetel3;
    print2.print(PrinterJob.createPrinterJob(), panetel3);
    
    }
        
        public void hospi(){}
}
