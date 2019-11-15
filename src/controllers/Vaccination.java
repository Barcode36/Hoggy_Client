/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Vues.LoadView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import jpaModel.Carnet;
import jpaModel.Consultations;
import jpaModel.Medecin;
import jpaModel.Patient;
import jpaModel.RendezVous;
import jpaModel.Simplevaccination;
import services.IConsultation;
import services.IMedecin;
import services.IPatient;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utiles.print;

/**
 * FXML Controller class
 *
 * @author Fallou
 */
public class Vaccination implements Initializable {

    @FXML
    private Label today;

    @FXML
    private Label label;

    @FXML
    private Circle cerccle;

    @FXML
    private Label docname;

    @FXML
    private Circle cercle;

    @FXML
    private ScrollPane scrolle;

    @FXML
    private Pane pane;

    @FXML
    private Pane pane1;

    @FXML
    private JFXButton btx1;

    @FXML
    private Pane pane2;

    @FXML
    private JFXButton btx2;

    @FXML
    private Pane pane3;

    @FXML
    private JFXButton btx3;

    @FXML
    private Pane pane4;

    @FXML
    private JFXButton btx4;

    @FXML
    private Pane pane5;

    @FXML
    private JFXButton btx5;

    @FXML
    private Pane pane6;

    @FXML
    private JFXButton btx6;


    @FXML
    private Pane pane8;

    @FXML
    private JFXButton btx8;

    @FXML
    private Pane pane9;

    @FXML
    private JFXButton btx9;

    @FXML
    private Pane pane10;

    @FXML
    private JFXButton btx10;

    @FXML
    private Pane pane11;

    @FXML
    private JFXButton btx11;

    @FXML
    private Pane pane12;

    @FXML
    private JFXButton btx12;

    @FXML
    private Pane pane13;

    @FXML
    private JFXButton btx13;

    @FXML
    private Pane pane14;

    @FXML
    private JFXButton btx14;

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
    private TableColumn<?, ?> Telephonecl;

    @FXML
    private JFXTextField champtbx;

    @FXML
    private Label name;

    @FXML
    private Label naiss;

    @FXML
    private Label mat;

    @FXML
    private Label prenomlabel;
    @FXML
    private Label myphoto;

    @FXML
    private JFXButton carnetbtx;

    @FXML
    private JFXButton simplebtx;

    @FXML
    private Pane panesimple;

    @FXML
    private JFXTextField typevacinntbx;

    @FXML
    private JFXTextField conclusionvaccintbx;

    TrayNotification succes = new TrayNotification();
    TrayNotification alert = new TrayNotification();
    Patient m;
    @FXML
    private Label typedetail;

    @FXML
    private Label nomdetail;

    @FXML
    private Label numerodetail;
    private final ObservableList<Classe> data = FXCollections.observableArrayList();

    private final FilteredList<Classe> filteredData = new FilteredList<>(data, p -> true);
    private final SortedList<Classe> sortedData = new SortedList<>(filteredData);

    Carnet trouve = new Carnet();
    Medecin med = new Medecin();
    List<Carnet> lc = new ArrayList<>();
    List<Patient> ll = new ArrayList<>();
    private File file = new File("src/images/back.png");
    int number;
    String mois;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mois = mois();
            pane.setVisible(false);
            simplebtx.setVisible(false);
            carnetbtx.setVisible(false);
            panesimple.setVisible(false);
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
            setCellTable();
            LoadEtudiantFromBase();
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

            //  parlabel.setText(parlabel.getText() + "  " + docname.getText());
            //  datelabel.setText(datelabel.getText() + " " + LocalDate.now().toString());
//
        } catch (Exception e) {
        }
        //  sp.setContent(pane);
        alert.setMessage("ATTENTION");
        alert.setTitle("INFORMATION");
        alert.setAnimationType(AnimationType.POPUP);
        alert.setNotificationType(NotificationType.ERROR);

        succes.setMessage("Operation Reussie");
        succes.setTitle("INFORMATION");
        succes.setAnimationType(AnimationType.POPUP);
        succes.setNotificationType(NotificationType.SUCCESS);
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
        today.setText(LocalDate.now().getDayOfMonth() + "  " + mois);
    }

    public void deconnecter() {
        LoadView.showView("connexion", "Authentification.fxml", 1);
    }

    public void fadepane() {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        FadeTransition ft = new FadeTransition(Duration.millis(200), pane1);
                        ft.setDuration(Duration.seconds(0.3));
                        ft.setFromValue(0);
                        ft.setToValue(5);
                        ft.play();

                        ft.setOnFinished((event) -> {

                            FadeTransition ft2 = new FadeTransition(Duration.millis(200), pane2);
                            ft2.setDuration(Duration.seconds(0.3));
                            ft2.setFromValue(0);
                            ft2.setToValue(5);
                            ft2.play();

                            ft2.setOnFinished((event2) -> {
                                FadeTransition ft3 = new FadeTransition(Duration.millis(200), pane3);
                                ft3.setDuration(Duration.seconds(0.3));
                                ft3.setFromValue(0);
                                ft3.setToValue(5);
                                ft3.play();

                                ft3.setOnFinished((event3) -> {
                                    FadeTransition ft4 = new FadeTransition(Duration.millis(200), pane4);
                                    ft4.setDuration(Duration.seconds(0.3));
                                    ft4.setFromValue(0);
                                    ft4.setToValue(5);
                                    ft4.play();

                                    ft4.setOnFinished((event4) -> {
                                        FadeTransition ft5 = new FadeTransition(Duration.millis(200), pane5);
                                        ft5.setDuration(Duration.seconds(0.3));
                                        ft5.setFromValue(0);
                                        ft5.setToValue(5);
                                        ft5.play();

                                        ft5.setOnFinished((event5) -> {
                                            FadeTransition ft6 = new FadeTransition(Duration.millis(200), pane6);
                                            ft6.setDuration(Duration.seconds(0.3));
                                            ft6.setFromValue(0);
                                            ft6.setToValue(5);
                                            ft6.play();

                                        });

                                    });

                                });

                            });

                        });

                    }
                }
                ),
                new KeyFrame(Duration.seconds(5))
        );
        // timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void print() {

        print.getDocument();

    }

    public void terminer() {

        simplebtx.setVisible(false);
        carnetbtx.setVisible(false);
        table.setVisible(true);
        champtbx.setVisible(true);
        FadeTransition ft3 = new FadeTransition(Duration.millis(200), table);
        ft3.setDuration(Duration.seconds(0.5));
        ft3.setFromValue(0);
        ft3.setToValue(5);
        ft3.play();
        pane.setVisible(false);
        LoadEtudiantFromBase();

    }

    public void detailtop() {

        Classe s = table.getSelectionModel().getSelectedItem();
        numerodetail.setText(s.getMatricule());

        //  datedetail.setText(s.getDateconcultation().toString());
        nomdetail.setText(s.getNom() + " " + s.getPrenom());

    }

    public void type1() {

        typedetail.setText("DiTe pediatric adsorbed");
    }

    public void type2() {

        typedetail.setText("DiTe pediatric adsorbed");
    }

    public void type3() {

        typedetail.setText("DiTe pediatric adsorbed");
    }

    public void type4() {

        typedetail.setText("DiTe pediatric adsorbed");
    }

    public void type5() {

        typedetail.setText("Engerix-B 20");
    }

    public void type6() {

        typedetail.setText("Priorix");
    }

    public void type7() {

        typedetail.setText("HEPATITE B");
    }

    public void type8() {

        typedetail.setText("OREILLONS");
    }

    public void type9() {

        typedetail.setText("Pollorix");
    }

    public void type10() {

        typedetail.setText("POLIOMYELITE");
    }

    public void detailmiddle() {

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

    public void chercher() {

        for (Patient m : ll) {
            if (m.getMatricule().equals(champtbx.getText())) {

                mat.setText(mat.getText() + "   " + m.getMatricule());
                name.setText(name.getText() + " " + m.getPrenom() + "   " + m.getNom());
                naiss.setText(naiss.getText() + "   " + m.getNaissance());

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
    String mail;

    public void detailbottom() {
    }

    public void selectionner() {

        Classe s = table.getSelectionModel().getSelectedItem();
        if (s != null) {
            simplebtx.setVisible(true);
            carnetbtx.setVisible(true);

            try {

                mat.setText(s.getMatricule());
                Patient pat = new Patient();
                for (Patient classe : ll) {
                    if (classe.getMatricule().equals(s.getMatricule())) {
                        pat = classe;
                    }
                    break;
                }

             
                name.setText(s.getPrenom() + "   " + s.getNom());
                naiss.setText(s.getNaissance().toString());
              
                BufferedImage img = null;
                img = ImageIO.read(new ByteArrayInputStream(pat.getPhoto()));
                
                Image image = SwingFXUtils.toFXImage(img, null);
                ImageView imv = new ImageView(image);
                imv.setFitHeight(90);
                imv.setFitWidth(100);
                myphoto.setGraphic(imv);
            
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }

        }

    }

    public void LoadEtudiantFromBase() {
        try {
            data.clear();
//  IPatient stub = ( IPatient) Naming.lookup("rmi://localhost:1099/patient");
//       
//          l = stub.listePatient();

            ll.forEach((ad) -> {

                Classe s = new Classe();
                s.setMatricule(ad.getMatricule());
                // s.setDocteur(ad.getIddocteur().getPrenom()+" "+ad.getIddocteur().getNom());
                //  s.setDateconcultation(ad.getDate().toString());
                s.setNom(ad.getNom());
                s.setPrenom(ad.getPrenom());
                s.setAdresse(ad.getAdresse());
                s.setNaissance(ad.getNaissance().toString());
                s.setTelephone(ad.getTelephone());

                data.add(s);
                table.setItems(data);
            });

        } catch (Exception ex) {
            System.out.println(ex);
        }

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

    private void setCellTable() {

        matcl.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nomcl.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcl.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adressecl.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        // consultationcl.setCellValueFactory(new PropertyValueFactory<>("dateconcultation"));
        Telephonecl.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        //  doccl.setCellValueFactory(new PropertyValueFactory<>("docteur"));
        naissancecl.setCellValueFactory(new PropertyValueFactory<>("naissance"));

    }

    public void simple() {
        table.setVisible(false);
        champtbx.setVisible(false);
        panesimple.setVisible(true);
        pane.setVisible(false);
        TranslateTransition t = new TranslateTransition();
        t.setNode(panesimple);
        t.setDuration(Duration.seconds(0.3));
        t.setToY(-200);
        t.play();
    }

    public void carnet() {
        try {

            Classe s = table.getSelectionModel().getSelectedItem();
            table.setVisible(false);
            champtbx.setVisible(false);
            panesimple.setVisible(false);
            pane.setVisible(true);
            simplebtx.setVisible(false);
            carnetbtx.setVisible(false);
            fadepane();

            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");

            Patient mm = ll
                    .stream()
                    .filter((ma) -> ma.getMatricule().equals(s.getMatricule()))
                    .findAny()
                    .orElse(null);

            lc = stub.listCarnet();

            trouve = lc
                    .stream()
                    .filter((ma) -> ma.getIdpatient().getMatricule().equals(mm.getMatricule()))
                    .findAny()
                    .orElse(null);
            if (trouve != null) {
                alert.setMessage("Le patient possede deja un carnet");
                alert.showAndDismiss(Duration.seconds(2));
             

                if (trouve.getVacc1() == 1) {
                    btx1.setVisible(false);
                     
                       pane1.setStyle("-fx-background-color: #18e4a6");
                } else {
                       
                    btx1.setVisible(true);
                    
                }
                if (trouve.getVacc2() == 1) {
                    btx2.setVisible(false);     
                            
                       pane2.setStyle("-fx-background-color: #18e4a6");
                } else {
                       
                   
                    btx2.setVisible(true);
                }
            
                if (trouve.getVacc4() == 1) {
                     
                    btx4.setVisible(false);
                   
                       pane4.setStyle("-fx-background-color: #18e4a6");
                } else {
                  
                    btx4.setVisible(true);
               
                }
                if (trouve.getVacc5() == 1) {
                    btx5.setVisible(false);       
                       pane5.setStyle("-fx-background-color: #18e4a6");
              
                } else {
                    
                    btx5.setVisible(true);
                 
                }
                
                
                 if (trouve.getVacc3() == 1) {
                    btx3.setVisible(false);    
                 
                       pane3.setStyle("-fx-background-color: #18e4a6");
                } else {
                      
                    btx3.setVisible(true);
                    
                }
                
                
                if (trouve.getVacc6() == 1) {
                    btx6.setVisible(false);    
                  
                       pane6.setStyle("-fx-background-color: #18e4a6");
                } else {
                       
                    btx6.setVisible(true);
                
                }
             
                if (trouve.getVacc8() == 1) {
                     
                    btx8.setVisible(false);
                 
                       pane8.setStyle("-fx-background-color: #18e4a6");
                } else {
                       
                    btx8.setVisible(true);
                  
                }
                if (trouve.getVacc9() == 1) {
                       
                       pane9.setStyle("-fx-background-color: #18e4a6");
                    btx9.setVisible(false);
            
                } else {
                     
                    btx9.setVisible(true);
                   
                }
                if (trouve.getVacc10() == 1) {
                 
                    btx10.setVisible(false);
                
                       pane10.setStyle("-fx-background-color: #18e4a6");
                } else {
                        
                    btx10.setVisible(true);
                   
                }

                if (trouve.getVacc11() == 1) {
                    btx11.setVisible(false);    
                    
                       pane11.setStyle("-fx-background-color: #18e4a6");
                } else {
                      
                    btx11.setVisible(true);
                 
                }

                if (trouve.getVacc12() == 1) {
                  
                    btx12.setVisible(false);
                     pane12.setStyle("-fx-background-color: #18e4a6");
                } else {
                     
                    btx12.setVisible(true);
                  
                }

                if (trouve.getVacc13() == 1) {
                  
                    btx13.setVisible(false);
                 pane12.setStyle("-fx-background-color: #18e4a6");
                } else {
                    btx13.setVisible(true);
                 
                }
                if (trouve.getVac14() == 1) {
                     
                    btx14.setVisible(false);
                      pane14.setStyle("-fx-background-color: #18e4a6");
                } else {
                    btx14.setVisible(true);
               
                }

            } else {
                {
                    Carnet c = new Carnet();
                    c.setDate(java.sql.Date.valueOf(LocalDate.now()));
                    c.setIddocteur(med);
                    c.setIdpatient(mm);
                    c.setMatricule(mm.getMatricule());
                    stub.InsererCarnet(c);
                    succes.setMessage("Carnet de vaccination creer");
                    succes.showAndDismiss(Duration.seconds(2));

                }
            }

        } catch (Exception e) {
        }

    }

    public void saveSimpleVaccinnation() {
        try {

            Classe s = table.getSelectionModel().getSelectedItem();
            Simplevaccination v = new Simplevaccination();

            m = ll
                    .stream()
                    .filter((ma) -> ma.getMatricule().equals(s.getMatricule()))
                    .findAny()
                    .orElse(null);
            if ("".equals(conclusionvaccintbx.getText()) || "".equals(typevacinntbx.getText())) {
                alert.setMessage("Veiller choisir un patient et remplir tous les champs");
                alert.showAndDismiss(Duration.seconds(2));
            } else if (s == null || m == null) {
                alert.setMessage("Veiller choisir un patient et remplir tous les champs");
                alert.showAndDismiss(Duration.seconds(2));

            } else {

                v.setIdpatient(m);
                v.setIddocteur(med);
                v.setDate(java.sql.Date.valueOf(LocalDate.now()));
                v.setConclusion(conclusionvaccintbx.getText());
                v.setType(typevacinntbx.getText());
                int mat = (int) (Math.random() * 9999999);
                v.setMatricule(String.valueOf(mat));

                IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
                stub.InsererSimpleVaccin(v);

                TranslateTransition t = new TranslateTransition();
                t.setNode(panesimple);
                t.setDuration(Duration.seconds(0.3));
                t.setToY(50);
                t.play();
                t.setOnFinished((event5) -> {

                    panesimple.setVisible(false);
                    pane.setVisible(false);
                    table.setVisible(true);
                    champtbx.setVisible(true);
                    succes.setMessage("Vaccination ENREGISTREE AVEC SUCCES");
                    succes.showAndDismiss(Duration.seconds(2));

                });

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void annuler() {
        try {

            TranslateTransition t = new TranslateTransition();
            t.setNode(panesimple);
            t.setDuration(Duration.seconds(0.3));
            t.setToY(50);
            t.play();
            t.setOnFinished((event5) -> {

                panesimple.setVisible(false);
                pane.setVisible(false);
                table.setVisible(true);
                champtbx.setVisible(true);

            });

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void validerVacc1() {
        try {

            trouve.setVacc1(1);
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            stub.updateCarnet(trouve);
            btx1.setVisible(false);
            pane1.setStyle("-fx-background-color: #18e4a6");
            succes.setMessage("Vaccination ENREGISTREE AVEC SUCCES");
            succes.showAndDismiss(Duration.seconds(2));
        } catch (Exception e) {
        }
    }

    public void validerVacc2() {
        try {

            trouve.setVacc2(1);
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            stub.updateCarnet(trouve);
            btx2.setVisible(false);
            pane2.setStyle("-fx-background-color: #18e4a6");
            succes.setMessage("Vaccination ENREGISTREE AVEC SUCCES");
            succes.showAndDismiss(Duration.seconds(2));
        } catch (Exception e) {
        }
    }

    public void validerVacc3() {
        try {

            trouve.setVacc3(1);
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            stub.updateCarnet(trouve);
            btx3.setVisible(false);
            pane3.setStyle("-fx-background-color: #18e4a6");
            succes.setMessage("Vaccination ENREGISTREE AVEC SUCCES");
            succes.showAndDismiss(Duration.seconds(2));
        } catch (Exception e) {
        }
    }

    public void validerVacc4() {
        try {

            trouve.setVacc4(1);
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            stub.updateCarnet(trouve);
            btx4.setVisible(false);
            pane4.setStyle("-fx-background-color: #18e4a6");
            succes.setMessage("Vaccination ENREGISTREE AVEC SUCCES");
            succes.showAndDismiss(Duration.seconds(2));
        } catch (Exception e) {
        }

    }

    public void validerVacc5() {
        try {

            trouve.setVacc5(1);
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            stub.updateCarnet(trouve);
            btx5.setVisible(false);
            pane5.setStyle("-fx-background-color: #18e4a6");
            succes.setMessage("Vaccination ENREGISTREE AVEC SUCCES");
            succes.showAndDismiss(Duration.seconds(2));
        } catch (Exception e) {
        }

    }

    public void validerVacc6() {
        try {

            trouve.setVacc6(1);
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            stub.updateCarnet(trouve);
            btx6.setVisible(false);
            pane6.setStyle("-fx-background-color: #18e4a6");
            succes.setMessage("Vaccination ENREGISTREE AVEC SUCCES");
            succes.showAndDismiss(Duration.seconds(2));
        } catch (Exception e) {
        }
    }

    public void validerVacc7() {
        try {

     
        } catch (Exception e) {
        }
    }

    public void validerVacc8() {
        try {

            trouve.setVacc8(1);
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            stub.updateCarnet(trouve);
            btx8.setVisible(false);
            pane8.setStyle("-fx-background-color: #18e4a6");
            succes.setMessage("Vaccination ENREGISTREE AVEC SUCCES");
            succes.showAndDismiss(Duration.seconds(2));
        } catch (Exception e) {
        }
    }

    public void validerVacc9() {
        try {

            trouve.setVacc9(1);
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            stub.updateCarnet(trouve);
            btx9.setVisible(false);
            pane9.setStyle("-fx-background-color: #18e4a6");
            succes.setMessage("Vaccination ENREGISTREE AVEC SUCCES");
            succes.showAndDismiss(Duration.seconds(2));
        } catch (Exception e) {
        }
    }

    public void validerVacc10() {
        try {

            trouve.setVacc10(1);
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            stub.updateCarnet(trouve);
            btx10.setVisible(false);
            pane10.setStyle("-fx-background-color: #18e4a6");
            succes.setMessage("Vaccination ENREGISTREE AVEC SUCCES");
            succes.showAndDismiss(Duration.seconds(2));
        } catch (Exception e) {
        }
    }

    public void validerVacc11() {
        try {

            trouve.setVacc11(1);
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            stub.updateCarnet(trouve);
            btx11.setVisible(false);
            pane11.setStyle("-fx-background-color: #18e4a6");
            succes.setMessage("Vaccination ENREGISTREE AVEC SUCCES");
            succes.showAndDismiss(Duration.seconds(2));
        } catch (Exception e) {
        }
    }

    public void validerVacc12() {
        try {

            trouve.setVacc12(1);
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            stub.updateCarnet(trouve);
            btx12.setVisible(false);
            pane12.setStyle("-fx-background-color: #18e4a6");
            succes.setMessage("Vaccination ENREGISTREE AVEC SUCCES");
            succes.showAndDismiss(Duration.seconds(2));
        } catch (Exception e) {
        }
    }

    public void validerVacc13() {
        try {

            trouve.setVacc13(1);
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            stub.updateCarnet(trouve);
            btx13.setVisible(false);
            pane13.setStyle("-fx-background-color: #18e4a6");
            succes.setMessage("Vaccination ENREGISTREE AVEC SUCCES");
            succes.showAndDismiss(Duration.seconds(2));
        } catch (Exception e) {
        }
    }

    public void validerVacc14() {
        try {

            trouve.setVac14(1);
            IConsultation stub = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
            stub.updateCarnet(trouve);
            btx14.setVisible(false);
            pane14.setStyle("-fx-background-color: #18e4a6");
            succes.setMessage("Vaccination ENREGISTREE AVEC SUCCES");
            succes.showAndDismiss(Duration.seconds(2));
        } catch (Exception e) {
        }
    }

}
