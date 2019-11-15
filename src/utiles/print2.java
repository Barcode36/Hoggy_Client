/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Fallou
 */
public class print2 {
      
      public   static    VBox root = new VBox(6000);
      public   static   Pane pane = new Pane();
  
          ;

    public static VBox getRoot() {
        return root;
    }

    public static void setRoot(VBox root) {
        print2.root = root;
    }

    public print2() {
    }
      
    public static  void pageSetup(Node node, Stage owner) 
	{
		// Create the PrinterJob
		PrinterJob job =  PrinterJob.createPrinterJob();
		
		if (job == null) 
		{
			return;
		}
		
		// Show the page setup dialog
		boolean proceed = job.showPageSetupDialog(owner);
		
		if (proceed) 
		{
			print(job, node);
		}
                owner.close();
	}
	
	public static void print(PrinterJob job, Node node) 
	{
		// Set the Job Status Message
//		jobStatus.textProperty().bind(job.jobStatusProperty().asString());
		
		// Print the node
		boolean printed = job.printPage(node);
	
		if (printed) 
		{
			job.endJob();
		}
	}	
   public static void getDocument()
   {
           
            Stage stage=new Stage();
       
            root.getChildren().add(pane);
            Scene scene = new Scene(root);
            root.setAlignment(Pos.CENTER);
	   stage.setScene(scene);
           stage.show();
           pageSetup(root, stage);
           root = new VBox(7000);
               
              
   }
}
