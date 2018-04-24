/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CNSAKS
 */
public class Main extends Application {

    @FXML
    private Button bt_gold;
    @FXML
    private Button bt_boss;
    @FXML
    private Button bt_exp;
    @FXML
    private VBox vBox_Main;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main/main.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("main/main.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Ventana principal");
        stage.show();
    }
     
    public static void main(String[] args) {
        launch(args);
    }
    
    @FXML
    private void clickGold(ActionEvent event) throws IOException {
        Parent reserva = FXMLLoader.load(getClass().getResource("gold/gold.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Reserva de entradas");
        stage.show();
    }

    @FXML
    private void clickBoss(ActionEvent event) throws IOException {
        Parent reserva = FXMLLoader.load(getClass().getResource("boss/boss.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Reserva de entradas");
        stage.show();
    }

    @FXML
    private void clickExp(ActionEvent event) throws IOException {
        Parent reserva = FXMLLoader.load(getClass().getResource("exp/exp.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Reserva de entradas");
        stage.show();
    }
    
}
