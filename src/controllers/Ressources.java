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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import jpaModel.Chambre;
import jpaModel.Patient;
import jpaModel.Typech;
import services.IPatient;
import services.IUser;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utiles.Type;

/**
 *
 * @author Fallou
 */
public class Ressources  implements Initializable{
       TrayNotification succes = new TrayNotification();
    TrayNotification alert = new TrayNotification();
  List<Chambre> l;

    private final ObservableList<Type> data = FXCollections.observableArrayList();

    private final FilteredList<Type> filteredData = new FilteredList<>(data, p -> true);
    private final SortedList<Type> sortedData = new SortedList<>(filteredData);
    @FXML
    private TableView<Type> table;
   @FXML
    private TableColumn<?, ?> capacitecl;
    @FXML
    private TableColumn<?, ?> matriculecl;

    @FXML
    private TableColumn<?, ?> typecl;

    @FXML
    private TableColumn<?, ?> superficiecl;

    @FXML
    private TableColumn<?, ?> datecl;

    @FXML
    private Pane panechambre;

    @FXML
    private JFXTextField matriculetbx;
@FXML
    private JFXTextField recherchertbx;
    @FXML
    private JFXTextField superficietbx;

    @FXML
    private ComboBox<Typech> combocbx;

    @FXML
    private JFXTextField capacitetbx;

    @FXML
    private JFXButton ajouterbtx;
 private File file = new File("src/images/back.png");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            List<Typech> ls=new ArrayList<>();
          
         
            
            
                IUser stub = (IUser) Naming.lookup("rmi://localhost:1099/admin");
                l=stub.listeChambre();
                ls=stub.listeTypeChambe();
                   combocbx.getItems().addAll(ls);
                setCellTable();
                LoadEtudiantFromBase();
            panechambre.setVisible(false);
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
        
   alert.setMessage("ATTENTION");
        alert.setTitle("INFORMATION");
        alert.setAnimationType(AnimationType.POPUP);
        alert.setNotificationType(NotificationType.ERROR);

        succes.setMessage("Operation Reussie");
        succes.setTitle("INFORMATION");
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
        }
           
        bindToTime();
   
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
           today.setText(LocalDate.now().getDayOfMonth()+"  "+LocalDate.now().getMonth());
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
      public void deconnecter(){
     LoadView.showView("connexion", "Authentification.fxml", 1);
     }
         public void back() {
        LoadView.showView("connexion", "AccueilAdmin.fxml", 1);
    }
         public void nouvelle(){
    recherchertbx.setVisible(false);
   panechambre.setVisible(true);
        
                      
                   
    
    ajouterbtx.setVisible(false);
    table.setVisible(false);
   

}
                  public void annuler(){
           
                      table.setVisible(true);
                    

                    panechambre.setVisible(false);
                    recherchertbx.setVisible(true);
                 
                    ajouterbtx.setVisible(true);
                     
                 

            


            }
                  
              

    public int validernombre(String texte) {
        int n = 0;
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(texte);
        char c = texte.charAt(0);
        char c1 = texte.charAt(1);

        if (m.find() && m.group().equals(texte) ) {
           
                n  =0;
            

        } else {

            n = 1;
        }
        return n;
    }

public void enrigistrer(){

    try {
           IUser stub = (IUser) Naming.lookup("rmi://localhost:1099/admin");
       
         
 
    if(matriculetbx.getText().equals("") || capacitetbx.getText().equals("")||combocbx.getValue()==null)
    {
      alert.setMessage("Veiller remplir tous les champs");
                alert.showAndDismiss(Duration.seconds(2));
    }
  else
     if(validernombre(capacitetbx.getText().trim())==1|| validernombre(superficietbx.getText().trim())==1)
    {
      alert.setMessage("La capacite ou la superficie donnee n est pas bonne");
                alert.showAndDismiss(Duration.seconds(2));
    }
    else
      {
         
      Chambre s=new Chambre();
      s.setCapacite(capacitetbx.getText());
      s.setMatricule(matriculetbx.getText());
      s.setType(combocbx.getValue());
      s.setSuperficie(superficietbx.getText());
    
            
      stub.InsererChambre(s);
         succes.setMessage("Chambre enregistre");
               succes.showAndDismiss(Duration.seconds(2));
               LoadEtudiantFromBase();
               annuler();
      }
    
  } catch (Exception e) {
        System.out.println(e.getLocalizedMessage());
    }

}
 private void setCellTable() {

        matriculecl.setCellValueFactory(new PropertyValueFactory<>("mat"));
        typecl.setCellValueFactory(new PropertyValueFactory<>("type"));
        superficiecl.setCellValueFactory(new PropertyValueFactory<>("superficie"));
        capacitecl.setCellValueFactory(new PropertyValueFactory<>("capacite"));
    

    }

    public void releasematricule() {

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

     public void LoadEtudiantFromBase() {
        try {
            data.clear();

            IUser stub = (IUser) Naming.lookup("rmi://localhost:1099/admin");
        
                l=stub.listeChambre();
            List<Type>lt=new ArrayList<>();
            l.stream().forEach((a) -> {
                Type t=new Type();
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
public void type()
{
if(combocbx.getValue().equals("Privee"))
{
 capacitetbx.setText("1");
capacitetbx.setVisible(false);
}
else
{
 capacitetbx.setText("10");
 capacitetbx.setVisible(true);
}

       }
}
                  
