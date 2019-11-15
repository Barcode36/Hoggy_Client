package controllers;

import Vues.LoadView;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Fallou
 */
public class AccueilMedecin implements Initializable {

    TrayNotification succes = new TrayNotification();
    TrayNotification alert = new TrayNotification();
    String mois;
    @FXML
    private Label today;

    @FXML
    private Label label;

    @FXML
    private Label prenomlabel;
    @FXML
    private Circle cerccle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            Path p = Paths.get("photo.txt");
            byte[] data = Files.readAllBytes(p);
            mois = mois();
            BufferedImage img = null;
            img = ImageIO.read(new ByteArrayInputStream(data));
            Image image = SwingFXUtils.toFXImage(img, null);
            ImageView imv = new ImageView(image);
            imv.setFitHeight(92);
            imv.setFitWidth(92);

            cerccle.setFill(new ImagePattern(image));
            cerccle.setFill(new ImagePattern(image));
            //cerccle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITE));

            FileReader f = new FileReader("nom.txt");
            int pos = 0;
            String sortie = "";
            while (pos != -1) {
                char car = (char) pos;
                sortie = sortie + car;
                pos = f.read();
            }
            prenomlabel.setText(sortie);
            f.close();

         

            bindToTime();
        } catch (Exception e) {
        }
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
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Calendar time = Calendar.getInstance();
                        String hourString = pad(2, ' ', time.get(Calendar.HOUR) == 0 ? "12" : time.get(Calendar.HOUR) + "");
                        String minuteString = pad(2, '0', time.get(Calendar.MINUTE) + "");
                        String secondString = pad(2, '0', time.get(Calendar.SECOND) + "");
                        String ampmString = time.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
                        label.setText(hourString + " : " + minuteString + " : " + secondString + " " + ampmString);

                    }
                }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        today.setText(LocalDate.now().getDayOfMonth() + "  " + mois);
    }

    public void sta() {

    }

    public void dossier() {
        LoadView.showView("Dossiers", "Dossier.fxml", 1);
    }

    public void rendervous() {
        LoadView.showView("Rendez-Vous", "GestionRV.fxml", 1);
    }

    public void calendar() {
        LoadView.showView("", "Calendar.fxml", 1);
    }
  public void hospitalisation() {
        LoadView.showView("Hospitalisation", "Hospitalisation.fxml", 1);
    }

    public void consultation() {
        LoadView.showView("Consultations", "Consultation.fxml", 1);
    }

    public void vaccination() {
        LoadView.showView("Vaccination", "Vaccination.fxml", 1);
    }

    public void deconnecter() {
        LoadView.showView("connexion", "Authentification.fxml", 1);
    }

    public String mois() {
        mois = "";
        int n = LocalDate.now().getMonth().getValue();
        if (n == 1) {
            mois = "JAanvier";
        }
        if (n == 2) {
            mois = "Fevrier";
        }
        if (n == 3) {
            mois = "Mars";
        }
        if (n == 4) {
            mois = "Avril";
        }
        if (n == 5) {
            mois = "Mai";
        }
        if (n == 6) {
            mois = "Juin";
        }
        if (n == 7) {
            mois = "Juillet";
        }
        if (n == 8) {
            mois = "Aout";
        }
        if (n == 9) {
            mois = "Mars";
        }
        if (n == 10) {
            mois = "Octobre";
        }
        if (n == 11) {
            mois = "Novembre";
        }
        if (n == 12) {
            mois = "Decembre";
        }
        return mois;

    }
}
