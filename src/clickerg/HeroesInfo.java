/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Dragon
 */


public class HeroesInfo implements Initializable{

    @FXML
    private Label lb_name;
    @FXML
    private Label lb_lvl;
    
    public static AuxiliarHeroe heroesInfo;
    @FXML
    private Label lb_ataque;
    @FXML
    private Label lb_experiencia;
    @FXML
    private Button b_activar;
    @FXML
    private ImageView img_heroe;
    
    ArrayList<AuxiliarHeroe> contratados = new ArrayList<AuxiliarHeroe>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        heroesInfo = Heroes.selectedHeroe;
        lb_name.setText(heroesInfo.getName());
        lb_lvl.setText("Nivel : " + heroesInfo.getLvl());
        lb_experiencia.setText("Experiencia : " + heroesInfo.getExp());
        lb_ataque.setText("Ataque : " + heroesInfo.getBase_atk());
        Image image = new Image("/clickerg/heroes/images/Vargas.png");
        if(heroesInfo.getId().equals("3")){
            image = new Image("/clickerg/heroes/images/Vargas.png");
                }
        if(heroesInfo.getId().equals("1")){
            image = new Image("/clickerg/heroes/images/Elza.png");
                }
        if(heroesInfo.getId().equals("2")){
            image = new Image("/clickerg/heroes/images/Zephu.png");
                }
        if(heroesInfo.getId().equals("4")){
            image = new Image("/clickerg/heroes/images/Selena.png");
                }
        img_heroe.setImage(image);
        TemplateXMLonlyRead heroeReader = new readGachaFileAccountInfo();
        contratados = heroeReader.readXML();
        
        
        img_heroe.sceneProperty().addListener((obs, oldScene, newScene) -> {
        Platform.runLater(() -> {
            Stage stage = (Stage) img_heroe.getScene().getWindow();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                closeMethod();
                Platform.exit();
                System.exit(0);
            }
        });
        });
    });
        
        
    }
    
    @FXML
    private void irATown(ActionEvent event) throws IOException {
        
        closeMethod();
        Parent reserva = FXMLLoader.load(getClass().getResource("heroes/heroes.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Heroes");
        stage.show();
    }

    @FXML
    private void setActive(ActionEvent event) {
        for(int x = 0;x<contratados.size();x++){
             if("true".equals(contratados.get(x).getActive())){
                contratados.get(x).setActive("false");
             }     
        }
        for(int x = 0;x<contratados.size();x++){
             if(contratados.get(x).getId().equals(heroesInfo.getId())){
                contratados.get(x).setActive("true");
             }     
        }
    }
    
    
    public void closeMethod(){
        TemplateXMLWriter heroeWriter = new writeHeroeFileAccountInfo();
        heroeWriter.modifyXML(contratados, 0);
    }
    
}
