/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Vues.LoadView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.imageio.ImageIO;


/**
 * FXML Controller class
 *
 * @author Fallou
 */
public class Calendar implements Initializable {
     @FXML
    private Circle cercle;
  private File file = new File("src/images/back.png");
    @FXML
    private Label today;
   
    @FXML
    private Label label;

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
     
     public void back(){
     LoadView.showView("connexion", "AccueilMedecin.fxml", 1);
     }
}
