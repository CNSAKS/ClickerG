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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 *
 * @author adpeijar
 */
public class Heroes implements Initializable{

    @FXML
    private Button butBack;
    @FXML
    private Button butSave;
    @FXML
    private GridPane gridPaneHe;

    ArrayList<AuxiliarHeroe> contratados = new ArrayList<AuxiliarHeroe>();
    
    public static AuxiliarHeroe selectedHeroe;
    
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
         TemplateXMLonlyRead readerAccount = new readHeroeFileAccountInfo();
           contratados = readerAccount.readXML();
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
                   /* b.setOnAction(e -> {
                        try {
                            irAHeroInfo();
                        } catch (IOException ex) {
                           
                        }
                    });*/
                   b.setOnAction(new EventHandler<ActionEvent>(){
                   
                   @Override
                   public void handle(ActionEvent event){
                       try{
                        //selectedHeroe = contratados.get(b.getId().charAt(b.getId().length()-1));
                        String idHeroe =(b.getId().toString().replaceAll("[A-z]*-", ""));
                        System.out.print(idHeroe);
                        selectedHeroe = contratados.get(Integer.parseInt(""+idHeroe,10));
                        Parent reserva = FXMLLoader.load(getClass().getResource("heroesInfo/heroesInfo.fxml"));
                        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(reserva));
                        //Preguntar por cierre
                        stage.setTitle("HeroesInfo");
                        stage.show();
                        } catch(IOException e){}
                   }
                   });
                   
                   ImageView iv = new ImageView();
                   iv.setImage(new Image("/clickerg/heroes/images/id_" + contratados.get(i).getId()+".png"));
                   
                   
                   iv.setFitHeight(97);
                   iv.setFitWidth(97);
                   b.setGraphic(iv);
                   b.setStyle("-fx-background-color: transparent;");

                    
                    gridPaneHe.add(b,columna, fila);
               }
           
          
    }
    
    private void irAHeroInfo() throws IOException{
         
    
    }
    
}
