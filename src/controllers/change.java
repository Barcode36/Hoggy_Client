/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import Vues.LoadView;
import javafx.scene.control.Alert;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.rmi.Naming;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.util.Duration;
import services.IUser;

public class change implements Initializable {

    public Alert alert = new Alert(Alert.AlertType.INFORMATION);
     DialogPane dialog = alert.getDialogPane();
    @FXML
    private JFXTextField logintbx;

    @FXML
    private JFXPasswordField passwordtbx;

    @FXML
    private Label onlinetbx;

    @FXML
    private JFXButton connectiontbx;

    @FXML
    private JFXCheckBox acceptetbx;

    @FXML
    private JFXCheckBox luetbx;

    @FXML
    private TextArea contrattbx;
 

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TranslateTransition transition2 = new TranslateTransition();
        transition2.setDuration(Duration.seconds(3));
        transition2.setNode(contrattbx);

        transition2.setToY(40);
        transition2.play();

        transition2.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TranslateTransition transition2 = new TranslateTransition();
                transition2.setDuration(Duration.seconds(3));
                transition2.setNode(luetbx);

                transition2.setToY(50);
                transition2.play();

            }
        });

        acceptetbx.setVisible(false);

    }

    public void connection() {
        try {
            
         if (logintbx.getText().trim().isEmpty() || passwordtbx.getText().trim().isEmpty()) {
                alert.setTitle("BOISSON A GOGO");
                alert.setContentText("VEILLEZ RENSEIGNER VOTRE EMAIL");
                alert.showAndWait();
    }else
         {
         IUser stub= (IUser) Naming.lookup("rmi://localhost:1099/admin");
          
             
            FileReader f = new FileReader("matricule.txt");
            int pos = 0;
            String sortie = "";
            while (pos != -1) {
                char car = (char) pos;
                sortie = sortie + car;
                pos = f.read();
            }
              stub.change(logintbx.getText(),passwordtbx.getText(),sortie);
              
            alert.setTitle("BOISSON A GOGO");
            alert.setHeaderText("BOISSON A GOGO");
            alert.setContentText("COMPTE MODIFIER VEILLER VOUS CONNECTER AVEC LES NOUVEAUX PARAMETRES");
            alert.showAndWait();
         }
              } catch (Exception e) {
                  System.out.println(e);
        }
          LoadView.showView("INSCRIPTION", "Authentification.fxml", 1);
        
    }

    public void lue() {
        if (luetbx.isFocused()) {
           TranslateTransition  transition2=new TranslateTransition();
            acceptetbx.setVisible(true);
                  transition2.setDuration(Duration.seconds(3));
                  transition2.setNode(acceptetbx);                            
                
                   transition2.setToY(50);
                   transition2.play();
                  
        }
    }

    public void accepter() {
        if (acceptetbx.isFocused()) {
           
            contrattbx.setVisible(false);

            onlinetbx.setText("CHANGER DE LOGIN ET DE MOT DE PASSE");
        } else {
            luetbx.setVisible(false);
            contrattbx.setVisible(true);
        }
    }

}
