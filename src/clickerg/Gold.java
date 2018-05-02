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
public class Gold implements Initializable {
    
    private Label label;
    @FXML
    private Label lb_goldCount;
    private int gold = 0;
    private int goldPerClick = 1;
    @FXML
    private Pane pn_Mine;
    @FXML
    private Label label_goldPerClick;
    @FXML
    private VBox vBox_Gold;
    @FXML
    private ImageView imageContainer_roca;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label_goldPerClick.setText(goldPerClick+"");
        lb_goldCount.setText(gold+"");
    }    

    @FXML
    private void goldIncrease(MouseEvent event) {
        gold += goldPerClick;
        lb_goldCount.setText(gold+"");
    }

    @FXML
    private void moreGPC(ActionEvent event) {
        goldPerClick++;
        label_goldPerClick.setText(goldPerClick+"");
    }
    
}
