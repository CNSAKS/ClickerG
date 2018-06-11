/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author CNSAKS
 */
public class Main extends Application {
         
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/clickerg/visualsAndFiles/main/main.fxml"));
        stage.getIcons().add(new Image(this.getClass().getResource("/clickerg/visualsAndFiles/main/icon.png").toString()));
        stage.setResizable(false);
        stage.setMaxHeight(430);
        stage.setMaxWidth(604);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Town");
        stage.show();
 
        
    }
     
    public static void main(String[] args) {
        launch(args);
      
    }
    
}
