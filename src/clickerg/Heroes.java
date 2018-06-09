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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author adpeijar
 */
public class Heroes implements Initializable{

    @FXML
    private Button butBack;
    @FXML
    private GridPane gridPaneHe;

    ArrayList<AuxiliarHeroe> contratados = new ArrayList<AuxiliarHeroe>();
    
    public static AuxiliarHeroe selectedHeroe;
    @FXML
    private VBox vBox_heroes;
    @FXML
    private ImageView iv_back;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ProgressIndicator loaderIndicator;
    @FXML
    private StackPane stakingPane;
    @FXML
    private Pane waitingPane;
    
    @FXML
    private void irATown(ActionEvent event) throws IOException {
        Parent reserva = FXMLLoader.load(getClass().getResource("main/main.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Town");
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iv_back.setImage(new Image("/clickerg/heroes/heroeBackground1.png"));
        Task<ArrayList<AuxiliarHeroe>> taskHeroeCharger = new Task<ArrayList<AuxiliarHeroe>>() {
            @Override protected ArrayList<AuxiliarHeroe> call() throws Exception {
                stakingPane.getChildren().get(0).setDisable(true);
                waitingPane.setVisible(true);
                TemplateXMLonlyRead readerAccount = new readHeroeFileAccountInfo();
                ArrayList<AuxiliarHeroe> contratados = readerAccount.readXML();
                return contratados;
            }
        };
        taskHeroeCharger.setOnSucceeded((WorkerStateEvent event) -> {
            contratados = taskHeroeCharger.getValue();
            int aux = contratados.size()/6;
            if(contratados.size()%6 != 0){
                aux = aux+ 1;
            }
            gridPaneHe.setPrefHeight(aux*97);
            int numHeroes = contratados.size();
            for(int i = 0; i<aux; i++){

            gridPaneHe.getRowConstraints().add(new RowConstraints(97));
            }
            int columna = 0;
            int fila = 0;
            for(int i = 0; i< numHeroes; i++){
                if(i % 6 == 0 && i!=0){
                    columna = 0;
                    fila = fila + 1;
                } else if(i!=0){
                    columna = columna + 1;
                }
                Button b = new Button();
                b.setId("bHeroe-" + i);
                b.setMinWidth(97);
                b.setMinHeight(97);
                
                b.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event){
                    try{
                     String idHeroe =(b.getId().replaceAll("[A-z]*-", ""));
                     selectedHeroe = contratados.get(Integer.parseInt(""+idHeroe,10));
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("heroesInfo/heroesInfo.fxml"));
                     Parent parent = (Parent) loader.load();
                     loader.<HeroesInfo>getController().initData(selectedHeroe);
                     Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                     stage.setScene(new Scene(parent));
                     stage.setTitle("HeroesInfo");
                     stage.show();
                     } catch(IOException e){}
                }
                });

                ImageView iv = new ImageView();
                try{
                iv.setImage(new Image("/clickerg/heroes/images/id_" + contratados.get(i).getId()+".png"));
                } catch(Exception e){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error al cargar las imagenes");
                    alert.setHeaderText("Ha habido un error al intentar cargar las imagenes");
                    alert.setContentText("El programa volvera a la anterior ventana, si el problema persiste reinicie el juego");

                    alert.showAndWait();
                }

                iv.setFitHeight(97);
                iv.setFitWidth(97);
                b.setGraphic(iv);
                b.setStyle("-fx-background-color: transparent;");


                gridPaneHe.add(b,columna, fila);
            }
            stakingPane.getChildren().get(0).setDisable(false);
            waitingPane.setVisible(false);
        });

        
        
        
        
        Thread th = new Thread(taskHeroeCharger);
        th.setDaemon(true);
        th.start();
        
        scrollPane.setStyle("-fx-control-inner-background: transparent");
        scrollPane.setStyle("-fx-background-color: transparent");
        gridPaneHe.setStyle("-fx-background-color: transparent");

        ImageView iv = new ImageView();
         iv.setImage(new Image("/clickerg/icons/back.png"));
         iv.setFitHeight(50);
         iv.setFitWidth(80);
          butBack.setStyle("-fx-background-color: transparent;");
        butBack.setGraphic(iv);
           
          
    }

    
}
