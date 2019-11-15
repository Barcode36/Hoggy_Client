/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import jpaModel.Administrateur;
import Vues.LoadView;
import services.IUser;
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
import javafx.scene.control.TextArea;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utiles.GoogleMail;

public class forget implements Initializable {

   TrayNotification  succes=new TrayNotification();
     TrayNotification  alert=new TrayNotification();

      
    @FXML
    private JFXTextField logintbx;
    @FXML
    private JFXCheckBox luetbx;

    @FXML
    private JFXCheckBox acceptetbx;

    @FXML
    private TextArea contrattbx;
    @FXML
    private JFXButton connectiontbx;

    @FXML
    private JFXPasswordField passwordtbx;

    @FXML
    private Label onlinetbx;

    @FXML
    private Label offlinetbx;

    @FXML
    private ImageView offlineimg;

    @FXML
    private ImageView onlineimg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
   succes.setMessage("OPERATION REUSSIE");
             succes.setTitle("INFORMATIONS");
             succes.setAnimationType(AnimationType.SLIDE);
             succes.setNotificationType(NotificationType.SUCCESS);
         
             
             
             alert.setMessage("ATTENTION");
             alert.setTitle("INFORMATION");
             alert.setAnimationType(AnimationType.POPUP);
             alert.setNotificationType(NotificationType.ERROR);
    }

    public void connection() {
        try {
          
    if (logintbx.getText().trim().isEmpty() ) {
                alert.setMessage("VEILLER RENSEIGNER VOTRE MAIL");
               alert.showAndDismiss(Duration.seconds(2));
    }else
    {
            IUser stub = (IUser) Naming.lookup("rmi://localhost:1099/admin");

            Administrateur ad = stub.findByMail(logintbx.getText());
            if (ad != null) {
                GoogleMail.login = ad.getLogin();
                GoogleMail.password = ad.getPassword();
                GoogleMail.nom=ad.getNom();
                GoogleMail.prenom=ad.getPrenom();
                GoogleMail.main();
             succes.setMessage("UN EMAIL VOUS A  ETE ENVOYER SUR VOTRE COMPTE"+logintbx.getText());
               succes.showAndDismiss(Duration.seconds(2));
               stub.reset(logintbx.getText());
               connectiontbx.setText("SE CONNECTER");
             
            } else {

              alert.setMessage("EMAIL INCORRECTE");
               alert.showAndDismiss(Duration.seconds(2));
            }
    
            
           
            {
            if(connectiontbx.getText().equals("SE CONNECTER"))
                    LoadView.showView("Connexion", "Authentification.fxml", 1);
            
            }
    }
        } catch (Exception e) {
            System.out.println(e);
        }
      //  LoadView.showView("INSCRIPTION", "Authentification.fxml", 1);

    }
public void accueil(){
  LoadView.showView("Connexion", "Authentification.fxml", 1);
}
}
