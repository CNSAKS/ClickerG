/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author CNSAKS
 */
public class Boss implements Initializable {

    @FXML
    private Pane pn_Mine;
    @FXML
    private ProgressBar hp_bar;
    
    int bossHp = 10000;
    int heroDamage = 2600;
    @FXML
    private Label currenthp;
    @FXML
    private Pane panehp;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hp_bar.setProgress(1);
        currenthp.setText(bossHp+" / "+bossHp);
        
    }    

    @FXML
    private void damageBoss(MouseEvent event) {
        hp_bar.setProgress((hp_bar.getProgress()*bossHp-heroDamage)/bossHp);
        if(hp_bar.getProgress()<=0){
            hp_bar.setProgress(1);
            bossHp*=1.16;
            System.out.println("Para, ya esta muerto, Nuevo boss con "+bossHp+"hp");
        }
        currenthp.setText((int)Math.floor(hp_bar.getProgress()*bossHp)+" / "+bossHp);
    }

    
}
