/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author CNSAKS
 */
public class Exp implements Initializable {

    @FXML
    private Label lb_expCount;
    private int expPerClick;
    @FXML
    private Label label_expPerClick;
    @FXML
    private VBox vBox_exp;
    @FXML
    private Pane pn_ExpRoom;
    
    ArrayList<AuxiliarHeroe> contratados = new ArrayList<AuxiliarHeroe>();
    Document xml;
    ArrayList<String> id;
    ArrayList<String> name;
    ArrayList<String> id_heroe;
    ArrayList<String> lvl;
    ArrayList<String> base_atk;
    ArrayList<String> prob;
    ArrayList<String> active;
    ArrayList<String> expList;
    
    int baseLvlExp = 100;
    int nextLvlExp = baseLvlExp;
    int heroeLvl;
    int heroExperience;
    String idActual;
    @FXML
    private ProgressBar exp_bar;
    @FXML
    private Label currentexp;
    @FXML
    private ImageView imageHeroe;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TemplateXMLonlyRead AccountInfoReader = new readBossFileAccountInfo();
        contratados = AccountInfoReader.readXML();
        expPerClick = AccountInfoReader.bossLvl;
        
        label_expPerClick.setText(expPerClick + "");
       
         
          TemplateXMLonlyRead readerExp = new readExpFileAccountInfo();
            contratados = readerExp.readXML();
         for(int x = 0;x<contratados.size();x++){
             if("true".equals(contratados.get(x).getActive())){
                heroExperience = Integer.parseInt(contratados.get(x).getExp());
                idActual = contratados.get(x).getId_heroe();
                heroeLvl = Integer.parseInt(contratados.get(x).getLvl());
             }
        }
        lb_expCount.setText(heroExperience + "");
        nextLvlExp =  (int)( baseLvlExp *  Math.pow(1.16, heroeLvl-1));
        exp_bar.setProgress(0);
        exp_bar.setProgress((exp_bar.getProgress()+ heroExperience) / nextLvlExp );
        currentexp.setText((int) heroExperience +" / "+(int) nextLvlExp);
       
        
        
        
        imageHeroe.sceneProperty().addListener((obs, oldScene, newScene) -> {
        Platform.runLater(() -> {
            Stage stage = (Stage) imageHeroe.getScene().getWindow();
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
    private void moreExpPC(ActionEvent event) {
         expPerClick++;
         label_expPerClick.setText(expPerClick+"");
    }
    
    @FXML
    private void expIncrease(MouseEvent event) {
        exp_bar.setProgress((exp_bar.getProgress() *  nextLvlExp + expPerClick) / nextLvlExp );
        heroExperience += expPerClick;
        lb_expCount.setText(heroExperience + "");
        if(exp_bar.getProgress() * nextLvlExp >= nextLvlExp ){
            heroExperience = 0;
            exp_bar.setProgress(0);        
            lb_expCount.setText("0");
            heroeLvl++;
            nextLvlExp  = (int) (baseLvlExp * Math.pow(1.16, heroeLvl-1));
            nextLvlExp = (int) Math.floor(nextLvlExp);
            
        }
        currentexp.setText(lb_expCount.getText() + " / " + nextLvlExp);
        
    } 

    @FXML
    private void backTown(ActionEvent event) throws IOException {
        closeMethod();
        Parent reserva = FXMLLoader.load(getClass().getResource("main/main.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        stage.setTitle("Town");
        stage.show();
    }
    
    
    

    public void closeMethod(){
        for(int x = 0;x<contratados.size();x++){
             if(contratados.get(x).getId_heroe().equals(idActual)){
                contratados.get(x).setExp(heroExperience+"");
                contratados.get(x).setLvl(heroeLvl+"");
             }     
        }
            TemplateXMLWriter expWriter = new writeExpFileAccountInfo();
            expWriter.modifyXML(contratados, Integer.parseInt(idActual));  

    }
    
}