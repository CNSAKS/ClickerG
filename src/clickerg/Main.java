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
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author CNSAKS
 */
public class Main extends Application {
         
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main/main.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("main/main.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Town");
        stage.show();
        
    }
     
    public static void main(String[] args) {
        launch(args);
      
    }
    
}
