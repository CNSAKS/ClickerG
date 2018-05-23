/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        heroesInfo = Heroes.selectedHeroe;
        lb_name.setText(heroesInfo.getName());
    }
    
    
    
}
