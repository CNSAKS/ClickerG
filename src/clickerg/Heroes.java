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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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
         TemplateXMLonlyRead readerAccount = new readGachaFileAccountInfo();
           contratados = readerAccount.readXML();
            int aux = contratados.size()/6;
               if(contratados.size()%6 != 0){
                   aux = aux+ 1;
               }
               int numHeroes = contratados.size();
               for(int i = 0; i<aux; i++){
               
               gridPaneHe.addRow(0);
               }
                int columna = 0;
                int fila = 0;
                for(int i = 0; i< numHeroes; i++){
                    System.out.println(i%6);
                    if(i % 6 == 0 && i!=0){
                        columna = 0;
                        fila = fila + 1;
                    } else if(i!=0){
                        columna = columna + 1;
                    }
                    Button b = new Button();
                    b.setId("bHeroe" + i);
                    b.setMaxWidth(Double.MAX_VALUE);
                    b.setMaxHeight(Double.MAX_VALUE);
                    
                    gridPaneHe.add(b,columna, fila);
               }
           
          
    }
    
}
