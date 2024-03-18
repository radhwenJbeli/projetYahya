/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class BackController implements Initializable {

    @FXML
    private ImageView ImageP;
    @FXML
    private Button Voyage;
    @FXML
    private Button ReserverVoyage;
    @FXML
    private Button Post;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Restaurant;
    @FXML
    private Button ReserverRestaurant;
    @FXML
    private Button Fermer;
    @FXML
    private Button Gestion_User;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void exit(){
        System.exit(0);
    }   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Voyage(ActionEvent event) {
         try {
/*
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/Voyage.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();*/
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Voyage.fxml"));
            Parent root = loader.load();
            VoyageController pc = loader.getController();
            pc.setReserver(false);
            pc.Nom_Voyage_Resrver_text(false);
            pc.Nom_Voyage_Resrver(false);  
            Voyage.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void ReserverVoyage(ActionEvent event) {
         try {

            Parent parent = FXMLLoader.load(getClass().getResource("Reservevoyage.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                //stage.getIcons().add(new Image("/images/logo.png"));
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void Post(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("FXMLPostback.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                //stage.getIcons().add(new Image("/images/logo.png"));
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
    }

    @FXML
    private void Reclamation(ActionEvent event) throws IOException {
         Parent parent = FXMLLoader.load(getClass().getResource("FXMLReclamationback.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                //stage.getIcons().add(new Image("/images/logo.png"));
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
    }

    @FXML
    private void Restaurant(ActionEvent event) {
         try {

             Parent parent = FXMLLoader.load(getClass().getResource("Restaurant.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                //stage.getIcons().add(new Image("/images/logo.png"));
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void ReserverRestaurant(ActionEvent event) {
        try {

              Parent parent = FXMLLoader.load(getClass().getResource("RestaurantReservation.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                //stage.getIcons().add(new Image("/images/logo.png"));
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void Gestion_User(ActionEvent event) {
        try {

              Parent parent = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

   
    
}
