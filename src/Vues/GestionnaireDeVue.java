/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Fallou
 */
public class GestionnaireDeVue {
    private Parent root;
	private Scene scene;
	private Stage stage;
	private String nomvue, titrefenetre;

	static GestionnaireDeVue gestionnaireDeVue;
	
	public static  GestionnaireDeVue getInstance(){
		if (gestionnaireDeVue == null)
			gestionnaireDeVue = new GestionnaireDeVue();
		return gestionnaireDeVue;
	}

	public void ChargerVue(String nvue, String titref){

		nomvue = nvue;
		titrefenetre = titref;
		try {
			root = FXMLLoader.load(getClass().getResource("view" + nomvue));
			scene = new Scene(root);
                     
			if (stage == null)
				stage = new Stage();
			stage.setScene(scene);
			stage.setTitle(titrefenetre);
                        
			stage.setFullScreen(true);
                        stage.sizeToScene();
                        
			stage.setResizable(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void show(){
		stage.show();
	}

	public void fermervue(){
		if (stage != null)
			stage.close();
	}

    
}
