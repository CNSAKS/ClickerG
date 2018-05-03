/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author CNSAKS
 */
public class Exp implements Initializable {

    @FXML
    private Label lb_expCount;
    private int exp = 0;
    private int expPerClick = 1;
    @FXML
    private Label label_expPerClick;
    @FXML
    private VBox vBox_exp;
    @FXML
    private Pane pn_ExpRoom;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         label_expPerClick.setText(expPerClick + "");
         lb_expCount.setText(exp + "");
    }    

    @FXML
    private void moreExpPC(ActionEvent event) {
         expPerClick++;
         label_expPerClick.setText(expPerClick+"");
    }
    
    @FXML
    private void expIncrease(MouseEvent event) {
         exp += expPerClick;
         lb_expCount.setText(exp + "");
    } 
}