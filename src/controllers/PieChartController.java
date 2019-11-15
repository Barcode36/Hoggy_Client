/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.rmi.Naming;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import jpaModel.Patient;
import services.IPatient;


/**
 *
 * @author gail
 */
public class PieChartController implements Initializable {
  
    @FXML
    private PieChart chart;
//    @FXML
//    private Rectangle rectangle;
    private ObservableList<PieChart.Data> pcData;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
             pcData = FXCollections.observableArrayList();
             IPatient stub = (IPatient) Naming.lookup("rmi://localhost:1099/patient");
           
            List< Patient> l = stub.listePatient();
           int value=0, value2=0;
            for (Patient patient : l) {
               
              
                    if(patient.getMatricule().charAt(0)=='C')
                    
                        
                        value++;
             
                          if(patient.getMatricule().charAt(0)=='V')
                          value2++;
                    
                }
              pcData.add(new PieChart.Data("CONSULTATION"+" "+value+" ", value));
               pcData.add(new PieChart.Data("VACCINATION"+" "+value2+" ", value2));
               
            
       
       
//        pcData.add(new PieChart.Data("Nokia", 77.3));
//        pcData.add(new PieChart.Data("RIM", 51.1));
//        pcData.add(new PieChart.Data("Apple", 93.2));
//        pcData.add(new PieChart.Data("HTC", 43.5));
//        pcData.add(new PieChart.Data("Samsung", 94.0));
//        pcData.add(new PieChart.Data("Others", 132.3));
        chart.setData(pcData);
        chart.setTitle("LES VISITES  LES PLUS FREQUENT ");
        setupAnimation();
        
         } catch (Exception e) {
        }
    }

    private void setupAnimation() {
        pcData.stream().forEach(pieData -> {
            
            System.out.println(pieData.getName() + ": " + pieData.getPieValue());
            pieData.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> { 
                Bounds b1 = pieData.getNode().getBoundsInLocal();
                double newX = (b1.getWidth()) / 2 + b1.getMinX();
                double newY = (b1.getHeight()) / 2 + b1.getMinY();
                // Make sure pie wedge location is reset
                pieData.getNode().setTranslateX(0);
                pieData.getNode().setTranslateY(0);
                
                // Show the BoundsInLocal of the selected wedge with a rectangle
//                rectangle.setTranslateX(newX);
//                rectangle.setTranslateY(newY);
//                rectangle.setWidth(b1.getWidth());
//                rectangle.setHeight(b1.getHeight());

                // Create the animation
                TranslateTransition tt = new TranslateTransition(
                        Duration.millis(1500), pieData.getNode());
                tt.setByX(newX);
                tt.setByY(newY);
                tt.setAutoReverse(true);
                tt.setCycleCount(2);
                tt.play();
            });
        });
    }

}
