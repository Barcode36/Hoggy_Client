/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Vues.LoadView;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
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
import services.IPatient;
import services.IUser;
import utiles.Genre;

/**
 * FXML Controller class
 *
 * @author Fallou
 */
public class Statique implements Initializable {

    private final ObservableList<Patient> data = FXCollections.observableArrayList();
    private final FilteredList<Patient> filteredData = new FilteredList<>(data, p -> true);
    private final SortedList<Patient> sortedData = new SortedList<>(filteredData);
    @FXML
    private JFXCheckBox cocheliste;

    @FXML
    private ComboBox<Genre> comboa;
    @FXML
    private JFXCheckBox cochegraphe;
    @FXML
    private JFXTextField matriculetbx;
   @FXML
    private JFXTextField matriculetbx1;
    @FXML
    private JFXTextField nomtbx;

    @FXML
    private JFXTextField prenomtbx;

    @FXML
    private JFXTextField adressetbx;

    @FXML
    private JFXTextField telephonetbx;

    @FXML
    private Label labelimage;
    @FXML
    private Pane paneliste;
    @FXML
    private Pane panegraphe;
    @FXML
    private TableView<Patient> tableconsultation;
    @FXML
    private Circle cerccle;

    @FXML
    private Label today;

    @FXML
    private Label label;

    @FXML
    private Label nomlabel;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
                  bindToTime();
            cerccle.setStroke(Color.WHITE);
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
            cerccle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITE));

            FileReader f = new FileReader("nom.txt");
            int pos = 0;
            String sortie = "";
            while (pos != -1) {
                char car = (char) pos;
                sortie = sortie + car;
                pos = f.read();
            }
            nomlabel.setText(sortie);
            f.close();

            panegraphe.setVisible(false);
            paneliste.setVisible(false);

            cochegraphe.setSelected(false);
            cocheliste.setSelected(false);

            VBox box = FXMLLoader.load(getClass().getResource("PieChart.fxml"));
            // drawer.setSidePane(box);

          

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
        today.setText(LocalDate.now().getDayOfMonth() + "  " + LocalDate.now().getMonth());
        
    }

    public void close() {

    }

    private void setCellTable() {

        matriculetbxc.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nomc.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomc.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adressec.setCellValueFactory(new PropertyValueFactory<>("adresse"));

    }

    public void releasematricule() {

        rechercherconsultation.textProperty().addListener((observable, oldValue, newValue) -> {
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
        sortedData.comparatorProperty().bind(tableconsultation.comparatorProperty());

        tableconsultation.setItems(sortedData);

    }

    public void consultation() {
        LoadView.showView("connexion", "FConsultation.fxml", 1);
    }

    public void back() {
        LoadView.showView("connexion", "AccueilComptable.fxml", 1);
    }

    public void LoadEtudiantFromBase() {
        try {
            data.clear();

            IPatient stub = (IPatient) Naming.lookup("rmi://localhost:1099/patient");
            List<Patient> l = stub.listePatient();

            for (Patient patient : l) {
                 {

                    data.add(patient);
                }

            }

            tableconsultation.setItems(data);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void selectedliste() {
        cocheliste.setSelected(true);
        cochegraphe.setSelected(false);
        paneliste.setVisible(true);
        panegraphe.setVisible(false);

    }

    public void selectedgraph() throws IOException {
        cocheliste.setSelected(false);
        cochegraphe.setSelected(true);
        paneliste.setVisible(false);
        panegraphe.setVisible(true);

        Parent root = FXMLLoader.load(getClass().getResource("PieChart.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void selectionner() {
        Patient m = (Patient) tableconsultation.getSelectionModel().getSelectedItem();
        
if(m!=null)
{
    matriculetbx.setText(m.getMatricule());
    nomtbx.setText(m.getNom());
        prenomtbx.setText(m.getPrenom());
            adressetbx.setText(m.getAdresse());  telephonetbx.setText(m.getTelephone());
            
            
            try {
                BufferedImage img = null;
                img = ImageIO.read(new ByteArrayInputStream(m.getPhoto()));
               
                Image image = SwingFXUtils.toFXImage(img, null);
                ImageView imv = new ImageView(image);
                imv.setFitHeight(90);
                imv.setFitWidth(100);
                labelimage.setGraphic(imv);
    } catch (Exception e) {
    }
}
    }

    
    
    
   
}
