package controllers;

import Vues.LoadView;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import jpaModel.Carnet;
import jpaModel.Consultations;
import jpaModel.Medecin;
import jpaModel.Patient;
import jpaModel.RendezVous;
import services.IConsultation;
import services.IPatient;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Dossier implements Initializable {

    @FXML
    private Label photo1;

    @FXML
    private Label photo2;

    @FXML
    private Label photo3;

    @FXML
    private AnchorPane root;
    private final ObservableList<Patient> data = FXCollections.observableArrayList();
    @FXML
    private JFXTextField champstbx;
    private final FilteredList<Patient> filteredData = new FilteredList<>(data, p -> true);
    private final SortedList<Patient> sortedData = new SortedList<>(filteredData);
    @FXML
    private Circle cerccle;
    @FXML
    private Label myphoto;

    @FXML
    private Label nomlb;
    @FXML
    private Label consultationA;

    @FXML
    private Label rendervousA;

    @FXML
    private Label VaccinA;

    @FXML
    private Label prenomlb;

    @FXML
    private Label naissancelb;

    @FXML
    private Label adresselb;

    @FXML
    private Label telephonelb;

    @FXML
    private Label maillb;
    @FXML
    private Label today;

    @FXML
    private Label label;
    @FXML
    private Pane preview;

    @FXML
    private Label photopreview;

    @FXML
    private Label docname;

    @FXML
    private Circle cercle;

    TrayNotification succes = new TrayNotification();
    TrayNotification alert = new TrayNotification();
    @FXML
    private Label nconsultation;

    @FXML
    private Label nhospitalisation;

    @FXML
    private Label nvaccin;

    @FXML
    private Label nrendervous;

    @FXML
    private TableView<Patient> table;

    @FXML
    private TableColumn<?, ?> matcl;

    @FXML
    private TableColumn<?, ?> nomcl;

    @FXML
    private TableColumn<?, ?> prenomcl;

    XYChart.Series<String, Double> series1 = new XYChart.Series<>();
    XYChart.Series<String, Double> series2 = new XYChart.Series<>();
    XYChart.Series<String, Double> series3 = new XYChart.Series<>();
    XYChart.Series<String, Double> series4 = new XYChart.Series<>();
    Medecin med = new Medecin();
    private File file = new File("src/images/back.png");

    private static Label jobStatus = new Label();
    @FXML
    private Label namme;
    List<Patient> ll = new ArrayList<>();

    List<RendezVous> listeRenderVous = new ArrayList<>();
    List<Consultations> listeConsultation = new ArrayList<>();
    List<Carnet> listeVaccin = new ArrayList<>();

    List<RendezVous> dernierListeRenderVous = new ArrayList<>();
    List<Consultations> dernierListeConsultation = new ArrayList<>();
    List<Carnet> dernierListeVaccin = new ArrayList<>();

    @FXML
    LineChart<String, Double> linechart;

    BufferedImage img1 = null;
    BufferedImage img2 = null;
    BufferedImage img3 = null;
    String mois;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            mois = mois();
            preview.setVisible(false);
            setCellTable();
            LoadFromBase();

            Path p = Paths.get("photo.txt");
            byte[] data = Files.readAllBytes(p);

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
            // System.out.println(sortie);

            f.close();

            namme.setText(sortie);
            //  name.setText(sortie);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        alert.setMessage("ATTENTION");
        alert.setTitle("INFORMATION");
        alert.setAnimationType(AnimationType.POPUP);
        alert.setNotificationType(NotificationType.ERROR);
        try {
            bindToTime();

            BufferedImage img = null;
            img = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(img, null);
            ImageView imv = new ImageView(image);
            imv.setFitHeight(92);
            imv.setFitWidth(92);
            cercle.setFill(new ImagePattern(image));
        } catch (Exception e) {
        }
    }

    public void back() {
        LoadView.showView("connexion", "AccueilMedecin.fxml", 1);
    }

    int cpt = 0;

    public String pad(int fieldWidth, char padChar, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < fieldWidth; i++) {
            sb.append(padChar);
        }
        sb.append(s);

        return sb.toString();
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

    private void bindToTime() {
        today.setText(LocalDate.now().getDayOfMonth() + "  " + mois);
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

    public void nouvelle() {
        LoadView.showView("connexion", "Consultation.fxml", 1);
    }

    public void liste() {
        LoadView.showView("connexion", "Liste.fxml", 1);
    }

    private void setCellTable() {

        matcl.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nomcl.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcl.setCellValueFactory(new PropertyValueFactory<>("prenom"));

    }

    public void releasematricule() {

        champstbx.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(medecin -> {

                if (newValue == null || newValue.isEmpty()) {

                    return true;

                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (medecin.getMatricule().toLowerCase().contains(lowerCaseFilter)) {

                    return true;
                } else if (medecin.getMatricule().toLowerCase().contains(lowerCaseFilter)) {

                    return true;
                }

                return false;

            });
        }
        );
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);

    }

    public void LoadFromBase() {
        try {
            data.clear();

            IPatient stub = (IPatient) Naming.lookup("rmi://localhost:1099/patient");
            IConsultation cons = (IConsultation) Naming.lookup("rmi://localhost:1099/consultation");
             
            listeConsultation = cons.listeConsultations();
            ll = stub.listePatient();
            listeVaccin = cons.listCarnet();
            listeRenderVous=cons.listeRv();

            for (Patient classe : ll) {
                data.add(classe);

            }

            table.setItems(data);

        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }
    String date = "";

    int nConsultations = 0;
    int nHospitalisation = 0;
    int nRenderVous = 0;
    int nVaccins = 0;

    public void selectionner() {
        try {

            Patient p = table.getSelectionModel().getSelectedItem();
            if (p != null) {
                dernierListeConsultation.clear();
                dernierListeVaccin.clear();
                dernierListeRenderVous.clear();

                nConsultations = 0;
                nHospitalisation = 0;
                nRenderVous = 0;
                nVaccins = 0;
                
          
          
                for (Consultations c : listeConsultation) {
                    if (c.getIdpatient().equals(p)) {
                        dernierListeConsultation.add(c);
                        nConsultations++;
                        if (c.getIdpatient().getHosptaliser() == 1) {
                            nHospitalisation++;
                        }

                    }
                }
            
                
                for (Carnet c : listeVaccin) {
                    if (c.getIdpatient().equals(p)) {
                        dernierListeVaccin.add(c);
                        nVaccins++;
                      
                    }
                }

          
                
                for (RendezVous rv : listeRenderVous) {
                    if (rv.getIdpatient().equals(p)) {
                        dernierListeRenderVous.add(rv);
                
                        nRenderVous++;
                    }
                }
         
                
                
         
                
                if (!dernierListeConsultation.isEmpty()) {

                    Consultations c = dernierListeConsultation.get(dernierListeConsultation.size() - 1);
                    consultationA.setText(c.getDate().toString());

                } else {
                    consultationA.setText("Aucune Consultations trouvee");
                }

                if (!dernierListeRenderVous.isEmpty()) {

                    RendezVous c = dernierListeRenderVous.get(dernierListeRenderVous.size() - 1);
                    rendervousA.setText(c.getDate().toString());

                } else {
                    rendervousA.setText("Aucun Render Vous trouve");
                }

                try {
                    
              
                   if (!dernierListeVaccin.isEmpty()) {

                    Carnet c = dernierListeVaccin.get(dernierListeVaccin.size() - 1);
                    VaccinA.setText(c.getDate().toString());

                } else {
                    VaccinA.setText("Aucun Render Vous trouve");
                }
                  } catch (Exception e) {
                      System.out.println(e.getLocalizedMessage());
                }
                
                Consultations dernierConsul = dernierListeConsultation.get(dernierListeConsultation.size() - 1);
              
                if (dernierConsul.getDocumentA() != null) {

                    img1 = ImageIO.read(new ByteArrayInputStream(dernierConsul.getDocumentA()));
                    Image image = SwingFXUtils.toFXImage(img1, null);
                    ImageView imv = new ImageView(image);
                    imv.setFitHeight(35);
                    imv.setFitWidth(35);
                    photo1.setGraphic(imv);

                } else {
                    photo1.setGraphic(null);
                }
                if (dernierConsul.getDocumentB() != null) {

                    img2 = ImageIO.read(new ByteArrayInputStream(dernierConsul.getDocumentB()));
                    Image image = SwingFXUtils.toFXImage(img2, null);
                    ImageView imv = new ImageView(image);
                    imv.setFitHeight(35);
                    imv.setFitWidth(35);
                    photo2.setGraphic(imv);

                } else {
                    photo1.setGraphic(null);
                }
                if (dernierConsul.getDocumentC() != null) {

                    img3 = ImageIO.read(new ByteArrayInputStream(dernierConsul.getDocumentC()));
                    Image image = SwingFXUtils.toFXImage(img3, null);
                    ImageView imv = new ImageView(image);
                    imv.setFitHeight(35);
                    imv.setFitWidth(35);
                    photo3.setGraphic(imv);

                } else {
                    photo1.setGraphic(null);
                }

                nhospitalisation.setText(nHospitalisation + " ");
                nconsultation.setText(nConsultations + " ");
                nrendervous.setText(nRenderVous + " ");
                nvaccin.setText(nVaccins+"");
            
                BufferedImage img = null;
                img = ImageIO.read(new ByteArrayInputStream(p.getPhoto()));

                Image image = SwingFXUtils.toFXImage(img, null);
                ImageView imv = new ImageView(image);
                imv.setFitHeight(90);
                imv.setFitWidth(100);
                myphoto.setGraphic(imv);
                nomlb.setText(p.getNom());
                prenomlb.setText(p.getPrenom());   adresselb.setText(p.getAdresse());
                telephonelb.setText(p.getTelephone());
                date = p.getNaissance().toString();
                naissancelb.setText(date);
                maillb.setText(p.getMail());

                int j = nConsultations - 1;
                int i = 1;

                linechart.getData().clear();
                series1.getData().clear();
                series2.getData().clear();
                series3.getData().clear();
                series4.getData().clear();

                series1 = new XYChart.Series<>();
                series2 = new XYChart.Series<>();
                series3 = new XYChart.Series<>();
                series4 = new XYChart.Series<>();

                series1.setName("Poids");
                series3.setName("Pression");
                series4.setName("Taille");
                series2.setName("Temperature");

                series2.getData().add(new XYChart.Data<>(" 0", 0.0));
                series3.getData().add(new XYChart.Data<>(" 0", 0.0));
                series4.getData().add(new XYChart.Data<>(" 0", 0.0));
                series1.getData().add(new XYChart.Data<>(" 0", 0.0));

                while (j >= 0) {
                 
                    series1.getData().add(new XYChart.Data<>(String.valueOf(i), Double.valueOf(dernierListeConsultation.get(j).getPoids()+"")));
                       
                    i = i + 1;
                    series2.getData().add(new XYChart.Data<>(String.valueOf(i), Double.valueOf(dernierListeConsultation.get(j).getTemperature())));
                    i = i + 1;
                    series3.getData().add(new XYChart.Data<>(String.valueOf(i), Double.valueOf(dernierListeConsultation.get(j).getPression())));
                    i = i + 1;
                    series4.getData().add(new XYChart.Data<>(String.valueOf(i), Double.valueOf(dernierListeConsultation.get(j).getTaille())));
                    i = i + 1;

                    j--;
                }

                linechart.getData().add(series1);
                linechart.getData().add(series2);
                linechart.getData().add(series3);
                linechart.getData().add(series4);

            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
//List<Consultations> lp=new ArrayList<>();

    public void preview1() {
        preview.setVisible(true);
        //  img1 = ImageIO.read(new ByteArrayInputStream(p.getPhoto()));

        Image image = SwingFXUtils.toFXImage(img1, null);
        ImageView imv = new ImageView(image);
        imv.setFitHeight(200);
        imv.setFitWidth(200);
        photopreview.setGraphic(imv);

    }

    public void hidepreview1() {

        preview.setVisible(false);

    }

    public void deconnecter() {
        LoadView.showView("connexion", "Authentification.fxml", 1);
    }

    public void preview2() {
        preview.setVisible(true);
        //  img1 = ImageIO.read(new ByteArrayInputStream(p.getPhoto()));

        Image image = SwingFXUtils.toFXImage(img2, null);
        ImageView imv = new ImageView(image);
        imv.setFitHeight(200);
        imv.setFitWidth(200);
        photopreview.setGraphic(imv);

    }

    public void hidepreview2() {

        preview.setVisible(false);

    }

    public void preview3() {
        preview.setVisible(true);
        //  img1 = ImageIO.read(new ByteArrayInputStream(p.getPhoto()));

        Image image = SwingFXUtils.toFXImage(img3, null);
        ImageView imv = new ImageView(image);
        imv.setFitHeight(200);
        imv.setFitWidth(200);
        photopreview.setGraphic(imv);

    }

    public void hidepreview3() {

        preview.setVisible(false);

    }
}
