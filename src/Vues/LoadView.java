/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.io.File;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Fallou
 */
public class LoadView {
    private static Parent root;
	private static int type;

	private static BorderPane rootBorder;

	private static Scene scene;
	private static Stage stage;
	private static String title;
	private static String name;

	private static void initStage()
	{
		if(stage==null){
			stage=new Stage();
		}
	}



	public static void showView(String titre,String nom, int letype)
	{
		title=titre;
		name=nom;
		type = letype;
		initStage();
		Load(type);
	}

	private static void Load(int type)
	{
		try
		{
			if(type == 1)
                        {
				root=FXMLLoader.load(LoadView.class.getResource("/view/"+name));
				scene=new Scene(root);
                                 stage.centerOnScreen();
				stage.setScene(scene);
				stage.setTitle(title);
                                stage.centerOnScreen();
                               
                               
                               
                                 
                              
                               
            
				showed();
			}
			else if(type == 2)
			{
				rootBorder=FXMLLoader.load(LoadView.class.getResource("/view/"+name));
				scene=new Scene(rootBorder);
				stage.setScene(scene);
				stage.setTitle(title);
				 stage.centerOnScreen();
                                 
                               
				showed();
			}
			else{
				root=FXMLLoader.load(LoadView.class.getResource("/view/"+name));
				rootBorder.setCenter(root);
                                  stage.centerOnScreen();
                                   
                                
				
			}

			

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}



	private static void showed()
	{
		stage.show();
	}

}
