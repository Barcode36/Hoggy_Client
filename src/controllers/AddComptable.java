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
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.time.LocalDate;
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
import jpaModel.Administrateur;
import jpaModel.Comptable;
import jpaModel.Medecin;
import jpaModel.Profil;
import jpaModel.Sexe;
import jpaModel.Specialite;
import services.IComptable;
import services.IMedecin;
import services.IUser;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Fallou
 */
public class AddComptable implements Initializable {

    @FXML
    private ImageView cancel;
    @FXML
    private ImageView modif;
    TrayNotification succes = new TrayNotification();
    TrayNotification alert = new TrayNotification();
 
    private File file = new File("src/images/back.png");


    private final ObservableList<Comptable> data = FXCollections.observableArrayList();

    private final FilteredList<Comptable> filteredData = new FilteredList<>(data, p -> true);
    private final SortedList<Comptable> sortedData = new SortedList<>(filteredData);
    @FXML
    private Label today;

    @FXML
    private Label label;

    @FXML
    private Circle cercle;

    @FXML
    private Pane pane1;

    @FXML
    private ImageView view;
    @FXML
    private TableView<Comptable> table;

    @FXML
    private TableColumn<?, ?> matriculecl;

    @FXML
    private TableColumn<?, ?> nomcl;

    @FXML
    private TableColumn<?, ?> prenomcl;
   @FXML
    private Label prenomlabel;
    @FXML
    private Circle cerccle;

    @FXML
    private TableColumn<?, ?> adressecl;

    @FXML
    private TableColumn<?, ?> telephonecl;

    @FXML
    private TableColumn<?, ?> mailcl;

    @FXML
    private JFXTextField recherchertbx;

    @FXML
    private JFXButton activertbbx;

    @FXML
    private JFXButton bloquertbx;

    @FXML
    private Pane pane2;

    @FXML
    private JFXTextField matriculetbx;

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
    private JFXTextField mailtbx;

    @FXML
    private JFXButton enregistrertbx;

    @FXML
    private JFXTextField logintbx;

    @FXML
    private JFXPasswordField passwordtbx;

    @FXML
    private JFXPasswordField password2;

    @FXML
    private ComboBox<Sexe> sexecbx;
FileChooser f = new FileChooser();
File files =new File("src/images/comptable.png");
    @FXML
    private JFXButton modifiertbx;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
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
            prenomlabel.setText(sortie);
            f.close();
       

           BufferedImage imgg = null;
            imgg = ImageIO.read(file);
            Image imageg = SwingFXUtils.toFXImage(imgg, null);
            ImageView imvg = new ImageView(imageg);
            imv.setFitHeight(92);
            imv.setFitWidth(92);
            cercle.setFill(new ImagePattern(imageg));
            
            
                   pane1.setVisible(true);
            pane2.setVisible(false);
               modif.setVisible(false);
            bindToTime();
      
            succes.setAnimationType(AnimationType.SLIDE);
            succes.setNotificationType(NotificationType.SUCCESS);
            setCellTable();cancel.setVisible(false);
            LoadEtudiantFromBase();
        
            alert.setAnimationType(AnimationType.POPUP);
            alert.setNotificationType(NotificationType.ERROR);
           
        } catch (Exception e) {
        }

    }
 public void deconnecter(){
     LoadView.showView("connexion", "Authentification.fxml", 1);
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
        today.setText(LocalDate.now().getDayOfMonth() + "  " + LocalDate.now().getMonth());
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

    public void back() {
        LoadView.showView("Centre de contrôle", "AccueilAdmin.fxml", 1);
    }
       public void cancel() {
     pane1.setVisible(true);
     pane2.setVisible(false);
     cancel.setVisible(false);
    }

    public void activer() {
        try {

            Comptable et = (Comptable) table.getSelectionModel().getSelectedItem();
            Administrateur ad = new Administrateur();
            ad.setTelephone(et.getTelephone());
            IUser stub = (IUser) Naming.lookup("rmi://localhost:1099/admin");
           // Administrateur m = stub.findUser(et.getLogin(), et.getPassword());
            //  if(m.getIdstatus()==1)
            stub.activer(ad);
            succes.setMessage("COMPTE ACTIVER");
            succes.showAndDismiss(Duration.seconds(2));
            activertbbx.setVisible(false);
            bloquertbx.setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void bloquer() {
        try {

            Comptable et = (Comptable) table.getSelectionModel().getSelectedItem();
            IUser stub = (IUser) Naming.lookup("rmi://localhost:1099/admin");
            Administrateur ad = new Administrateur();
            ad.setTelephone(et.getTelephone());
            stub.bloquer(ad);
            alert.setMessage("COMPTE BLOQUER");
            alert.showAndDismiss(Duration.seconds(2));
            activertbbx.setVisible(true);
            bloquertbx.setVisible(false);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void LoadEtudiantFromBase() {
        try {
            data.clear();

            IUser stub = (IUser) Naming.lookup("rmi://localhost:1099/admin");
              IComptable stub2 = (IComptable) Naming.lookup("rmi://localhost:1099/comptable");
            List<Comptable> l = stub2.listeComptable();
            List<Sexe>ls=stub.listeSexe();  sexecbx.getItems().addAll(ls);
            l.stream().filter((a) -> (a.getIdprofil().getId()==4)).forEachOrdered((a) -> {
                data.add(a);
            });
            table.setItems(data);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void modif()
    {
        cancel.setVisible(true);
        pane1.setVisible(false);
        pane2.setVisible(true);
     Comptable et = (Comptable) table.getSelectionModel().getSelectedItem();
               logintbx.setText(et.getLogin());
            password2.setText(et.getPassword());
            passwordtbx.setText(et.getPassword());
            mailtbx.setText(et.getMail());
            enregistrertbx.setVisible(false);
            modifiertbx.setVisible(true);
            nomtbx.setText(et.getNom());
            prenomtbx.setText(et.getPrenom());
            adressetbx.setText(et.getAdresse());
            sexecbx.setValue(et.getIdsexe());
            telephonetbx.setText(et.getTelephone());
            matriculetbx.setText(et.getMatricule());
    }
    public void Selectionner() throws IOException {

        try {
            Comptable et = (Comptable) table.getSelectionModel().getSelectedItem();
           
            IUser stub = (IUser) Naming.lookup("rmi://localhost:1099/admin");
            Administrateur m = stub.findUser(et.getLogin(), et.getPassword());
            if (m.getIdstatus() == 1) {
                activertbbx.setVisible(false);
                bloquertbx.setVisible(true);
                modif.setVisible(true);
            } else {
                activertbbx.setVisible(true);
                bloquertbx.setVisible(false);
                     modif.setVisible(true);
            }

        } catch (Exception e) {
        }

    }

    private void setCellTable() {

        matriculecl.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nomcl.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcl.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adressecl.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        telephonecl.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        mailcl.setCellValueFactory(new PropertyValueFactory<>("mail"));

    }

    public void releasematricule() {

        recherchertbx.textProperty().addListener((observable, oldValue, newValue) -> {
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
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);

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

    
     public void enregistrer() {

        try {

            IUser stub = (IUser) Naming.lookup("rmi://localhost:1099/admin");
            IComptable stub2 = (IComptable) Naming.lookup("rmi://localhost:1099/comptable");
            if (matriculetbx.getText().trim().isEmpty() || nomtbx.getText().trim().isEmpty() || prenomtbx.getText().trim().isEmpty() || adressetbx.getText().trim().isEmpty() || naissancetbx.getValue() == null) {
                alert.setMessage("VEILLER REMPLIR TOUS LES CHAMPS SVP");
               alert.showAndDismiss(Duration.seconds(2));
               

            } else if (naissancetbx.getValue().getYear() > 1999 || naissancetbx.getValue().getYear() < 1980) {
                  alert.setMessage("VOUS N AVEZ PAS L AGE D UTILISER LE SYSTEM");
               alert.showAndDismiss(Duration.seconds(2));
              

            } else if (validerchamp(nomtbx.getText()) == 1 || nomtbx.getText().length() > 15) {
              
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
            
            } else if (!passwordtbx.getText().equals(password2.getText())) {
           
                 alert.setMessage("LES MOTS DE PASSSE NE SONT PAS LES MEMES");
               alert.showAndDismiss(Duration.seconds(2));
            
                password2.setVisible(true);
              
            } else {
               enregistrertbx.setVisible(true);
                Administrateur admin = new Administrateur();
                admin.setMatricule(matriculetbx.getText());
                admin.setNom(nomtbx.getText());
                admin.setPrenom(prenomtbx.getText());
                admin.setTelephone(telephonetbx.getText());
                admin.setAdresse(adressetbx.getText());
                admin.setLogin(logintbx.getText());
                admin.setPassword(passwordtbx.getText());
                admin.setIdsexe(sexecbx.getValue());
                admin.setMail(mailtbx.getText());
                admin.setNaissance(java.sql.Date.valueOf(naissancetbx.getValue()));
                Profil p = new Profil(4, "COMPTABLE");
                admin.setIdprofil(p);
                admin.setIdstatus(2);
                byte[] t = Files.readAllBytes(files.toPath());
                admin.setPhoto(t);
                admin.setLog(0);
                
                        
                       Comptable m=new Comptable();
                        m.setMatricule(matriculetbx.getText());
                m.setNom(nomtbx.getText());
                m.setPrenom(prenomtbx.getText());
                m.setTelephone(telephonetbx.getText());
                m.setAdresse(adressetbx.getText());
                m.setLogin(logintbx.getText());
                m.setPassword(passwordtbx.getText());
                m.setIdsexe(sexecbx.getValue());
                m.setMail(mailtbx.getText());
                m.setNaissance(java.sql.Date.valueOf(naissancetbx.getValue()));
              
                m.setIdprofil(p);
          
            
                m.setPhoto(t);
            
                       
                       
                        List<Administrateur> v = stub.listeAdministrateur();
                for (int i = 0; i < v.size(); i++) {
                    if (v.get(i).getMatricule().equals(matriculetbx.getText().trim())) {
                      
                          alert.setMessage("LE MATRICULE EXISTE DEJA");
               alert.showAndDismiss(Duration.seconds(2));
                   
                    
                        break;

                    } else if (v.get(i).getTelephone().equals(telephonetbx.getText().trim())) {
                     
                        alert.setMessage("LE NUMERO TELEPHONE EXISTE DEJA");
               alert.showAndDismiss(Duration.seconds(2));
                      
                    
                       
                        break;

                    } else if (v.get(i).getLogin().equals(logintbx.getText().trim())) {
                       
                         alert.setMessage("LE LOGIN EST DEJA UTILISE");
               alert.showAndDismiss(Duration.seconds(2));
                
                       
                        break;

                    } else if (v.get(i).getMail().equals(mailtbx.getText().trim())) {
                      
                          alert.setMessage("LE MAIL EST DEJA UTILISER");
               alert.showAndDismiss(Duration.seconds(2));
                  
                        break;
                    }

                }
                int ok = 0;
                for (int i = 0; i < v.size(); i++) {

                    if (v.get(i).getMatricule().equals(matriculetbx.getText()) || v.get(i).getTelephone().equals(telephonetbx.getText())
                            || v.get(i).getLogin().equals(logintbx.getText()) || v.get(i).getMail().equals(mailtbx.getText())) {
                        ok = 1;
                        break;
                    } else {
                        ok = 0;
                    }
                }
                System.out.println(ok);
                if (ok == 0) {
                    stub.InsererUtilisateur(admin);
                     stub2.InsererComptable(m);
                    succes.setMessage("PERSONNE ENREGISTREE AVEC SUCCES");
               succes.showAndDismiss(Duration.seconds(2));
                    
                    LoadEtudiantFromBase();
                        pane1.setVisible(true);
                          pane2.setVisible(false);
                }
            }
        } catch (Exception e) {
             alert.setMessage("L IMAGE CHOISIE EST TROP GRAND");
               alert.showAndDismiss(Duration.seconds(2));
            System.out.println(e);
                pane1.setVisible(true);
                          pane2.setVisible(false);
            
        }

    }
public void ajout(){
    pane1.setVisible(false);
    cancel.setVisible(true);
                          pane2.setVisible(true);
                          modifiertbx.setVisible(false);
                          enregistrertbx.setVisible(true);
}
    public void modifier() {
       try {

              IUser stub = (IUser) Naming.lookup("rmi://localhost:1099/admin");
                IComptable  stub2 = (IComptable) Naming.lookup("rmi://localhost:1099/comptable");
           
            if (matriculetbx.getText().trim().isEmpty() || nomtbx.getText().trim().isEmpty() || prenomtbx.getText().trim().isEmpty() || adressetbx.getText().trim().isEmpty() ) {
                  alert.setMessage("VEILLER REMPLIR TOUS LES CHAMPS SVP");
               alert.showAndDismiss(Duration.seconds(2));

            } else {
                if (naissancetbx.getValue().getYear() > 1999 || naissancetbx.getValue().getYear() < 1980) {
                 
                     alert.setMessage("VOUS N AVEZ PAS L AGE D UTILISER LE SYSTEME");
               alert.showAndDismiss(Duration.seconds(2));
                    
                
                } else {
                    if (validerchamp(nomtbx.getText()) == 1 || nomtbx.getText().length() > 15) {
                     
                            alert.setMessage("LE NOM DOIT CONTENIR QUE DES LETTRES ET INFERIEUR A 15 CARACTERES");
               alert.showAndDismiss(Duration.seconds(2));
                        
                       
                      
                    } else {
                        if (validerchamp(prenomtbx.getText()) == 1 || nomtbx.getText().length() > 15) {
                             
                     alert.setMessage("LE PRENOM DOIT CONTENIR QUE DES LETTRES ET INFERIEUR A 15 CARACTERES");
               alert.showAndDismiss(Duration.seconds(2));
                          
                     
                        } else {
                            if (validernombre(telephonetbx.getText()) == 1) {
                                 
                     alert.setMessage("LE NUMERO TEL DOIT COMMENCE PAR 77 76 78 70 OU 33");
               alert.showAndDismiss(Duration.seconds(2));
                               

                            
                            } else {
                                if (!passwordtbx.getText().equals(password2.getText())) {
                                           
                     alert.setMessage("LES MOTS DE PASSE NE SONT PAS LE MEME");
               alert.showAndDismiss(Duration.seconds(2));
                                  
                               
                                    password2.setVisible(true);
                                   
                                } else {
                               
                                    modifiertbx.setVisible(false);
                                    enregistrertbx.setVisible(true);
                           
                        Comptable m   = (Comptable) table.getSelectionModel().getSelectedItem();
                                Administrateur et=  stub.findUser(m.getLogin(), m.getPassword());
                                  
                                    et.setTelephone(telephonetbx.getText());
                                    et.setAdresse(adressetbx.getText());
                                    et.setLogin(logintbx.getText());
                                    et.setPassword(passwordtbx.getText());                               
                                    et.setMail(mailtbx.getText());
                                    
                                    
                                       m.setTelephone(telephonetbx.getText());
                                    m.setAdresse(adressetbx.getText());
                                    m.setLogin(logintbx.getText());
                                    m.setPassword(passwordtbx.getText());                               
                                    m.setMail(mailtbx.getText());
                                    stub2.ModifierComptable(m);
                                          
                                                succes.setMessage("INFOS MISES A JOUR");
                                             succes.showAndDismiss(Duration.seconds(2));
                                            
                                            LoadEtudiantFromBase();
                                            pane2.setVisible(false);
                                                 pane1.setVisible(true);
                                        
                                    
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
                alert.setMessage("CHANGER  L IMAGE OU VERIFIER SI LA DATE DE NAISSANCE N EST PAS NULL");
               alert.showAndDismiss(Duration.seconds(2));
                    pane2.setVisible(false);
                           pane1.setVisible(true);
           
        }

    }
   public void getphoto()
    {
        try {
            
       
                 f=new FileChooser();
                f.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL Images", "*.jpg","*.jpeg"),
                new FileChooser.ExtensionFilter("JPG", "*jpg"));
       
                files=f.showOpenDialog(new Stage());
               // parcourir.setText(file.toString());
                Image m=new Image("file:///"+files.toPath().toString());
                view.setImage(m);

                 } catch (Exception e) {
        }
    
    }
}
