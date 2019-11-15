

package utiles;

import java.rmi.Naming;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Duration;
import jpaModel.Patient;
import services.IPatient;

public class Bar extends Application {
    
    final static String itemA = "A";
    final static String itemB = "B";
    final static String itemC = "C";

    @Override
    public  void start(Stage stage) {
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> bc = new BarChart<Number, String>(xAxis, yAxis);
         XYChart.Series series1=new XYChart.Series();
          XYChart.Series series2=new XYChart.Series();
        XYChart.Series series3 =new XYChart.Series();
        
        bc.setTitle("TYPES DE VISITE LORS DES 3 PRECEDENTS MOIS");
        xAxis.setLabel("VALEUR");
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("VISITES");

        try {
            
     
          IPatient stub = (IPatient) Naming.lookup("rmi://localhost:1099/patient");
         
            List< Patient> l = stub.listePatient();
           
             int mois=LocalDateTime.now().getMonth().getValue();
             int value3=0;
                   int value2=0;
                       int value1=0; 
                       String nom="";
                       String nomb="";String nomc="";
            for (Patient marque : l) {
            
              
                    if(marque.getMatricule().charAt(0)=='C' )
                    {
                    
                        value3++;
                    }
                    if(marque.getMatricule().charAt(0)=='V' )
                    {
                    
                        value2++;
                    }
                     if(marque.getMatricule().charAt(0)=='H' )
                    {
                    
                        value1++;
                    }
                 
       
        
               
            }
        series1.setName("I YA 3 MOIS"+" "+value1+value2+value3+" FCFA");
        series1.getData().add(new XYChart.Data(value1,"Consultation"));
        series1.getData().add(new XYChart.Data(value2, "Vaccination"));
        series1.getData().add(new XYChart.Data(value3, "Hospiatlisation"));
            
                  value3=0;
                  value2=0;
                  value1=0; 
                  nom="";
                   nomb="";
                      nomc="";
             for (Patient marque : l) {
               
                  if(marque.getMatricule().charAt(0)=='C' )
                    {
                    
                        value3++;
                    }
                    if(marque.getMatricule().charAt(0)=='V' )
                    {
                    
                        value2++;
                    }
                     if(marque.getMatricule().charAt(0)=='H' )
                    {
                    
                        value1++;
                    }
                
                    
        
        
               
            }
             
             series2.setName(nom+" "+value1+value2+value3+" FCFA");
        series2.getData().add(new XYChart.Data(value1, "Consultation"));
        series2.getData().add(new XYChart.Data(value2, "Vaccination"));
        series2.getData().add(new XYChart.Data(value3,  "Hospiatlisation"));
             
                   value3=0;
                   value2=0;
                      value1=0; 
                      nom="";
                      nomb="";
                      nomc="";
              for (Patient marque : l) {
                
                 if(marque.getMatricule().charAt(0)=='C' )
                    {
                    
                        value3++;
                    }
                    if(marque.getMatricule().charAt(0)=='V' )
                    {
                    
                        value2++;
                    }
                     if(marque.getMatricule().charAt(0)=='H' )
                    {
                    
                        value1++;
                    }
                   
     
               
            }
            
        series3 = new XYChart.Series();
        series3.setName("Consultation"+" "+ value1+value2+value3 +" FCFA");
        series3.getData().add(new XYChart.Data(value1, "Consultation"));
        series3.getData().add(new XYChart.Data(value2, "Vaccination"));
        series3.getData().add(new XYChart.Data(value3,  "Hospiatlisation"));
        
           } catch (Exception e) {
        }
        
        
        
        
        
        
        
        
        
        
     
    

//        XYChart.Series series2 = new XYChart.Series();
//        series2.setName("MINERALE");
//        series2.getData().add(new XYChart.Data(50, itemA));
//        series2.getData().add(new XYChart.Data(41, itemB));
//        series2.getData().add(new XYChart.Data(45, itemC));
//
//        XYChart.Series series3 = new XYChart.Series();
//        series3.setName("JUS LOCAUX");
//        
//        series3.getData().add(new XYChart.Data(45, itemA));
//        series3.getData().add(new XYChart.Data(44, itemB));
//        series3.getData().add(new XYChart.Data(18, itemC));

        
       
        Timeline tl = new Timeline();
        
        
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(3), 
            new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent actionEvent) {
                for (XYChart.Series<Number, String> series : bc.getData()) {
                    for (XYChart.Data<Number, String> data : series.getData()) {
                        data.setXValue(1);
                    }
                }
            }
        }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();

        Scene scene = new Scene(bc, 900, 800);
        bc.getData().addAll(series1, series2, series3);
        stage.setScene(scene);
      
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}

   