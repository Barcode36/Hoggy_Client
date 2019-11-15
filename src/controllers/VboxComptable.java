/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Vues.LoadView;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Fallou
 */
public class VboxComptable implements Initializable {

    @FXML
    private JFXButton bt1;

    @FXML
    private JFXButton bt2;

    @FXML
    private JFXButton bt3;

    @FXML
    private JFXButton bt4;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
 public void listeConsultation(){
      LoadView.showView("INSCRIPTION", "AccueilSecretaireConsultation.fxml", 1);
 }
  public void listeVaccination(){
      try {
          
    
      LoadView.showView("INSCRIPTION", "AccueilSecretaireVaccination.fxml", 1);
        } catch (Exception e) {
      }
 }
   public void listeRenderVous(){
      LoadView.showView("INSCRIPTION", "AccueilSecretaireRenderVous.fxml", 1);
 }
    public void listeHospitalisation(){
      LoadView.showView("INSCRIPTION", "AccueilSecretaireHospitalisation.fxml", 1);
 }
        public void accueil(){
      LoadView.showView("INSCRIPTION", "AccueilSecretaire.fxml", 1);
 }
        
                public void demande(){
      LoadView.showView("INSCRIPTION", "AccueilSecretaireDemande.fxml", 1);
 }
}
