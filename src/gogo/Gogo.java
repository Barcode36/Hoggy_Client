
package gogo;


import Vues.LoadView;
import javafx.application.Application;
import javafx.stage.Stage;


public class Gogo extends Application {
    
    @Override
  public void start(Stage primaryStage)  throws Exception {
      
      
        LoadView.showView("connexion", "Authentification.fxml", 1);

    }

    public static void main(String[] args) {
         
         // String nom=s.
        System.setProperty("java.security.policy", "file:boisson.policy");
        System.setSecurityManager(new SecurityManager());
        
     launch(args);
     
    }
    
}
