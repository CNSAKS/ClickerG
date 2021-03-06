/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.gold.logic;

import clickerg.classes.others.gameLoop.GameLoop;
import clickerg.classes.others.volatiles.AnimationVolatile;
import clickerg.classes.others.templates.TemplateXMLWriter;
import clickerg.classes.others.templates.TemplateXMLonlyRead;
import clickerg.classes.gold.persistence.readGoldFileAccountInfo;
import clickerg.classes.gold.persistence.writeGoldFileAccountInfo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.w3c.dom.Document;

/**
 * FXML Controller class
 *
 * @author CNSAKS
 */
public class Gold implements Initializable {
    
    private Label label;
    private Label lb_goldCount;
    private int gold = 0;
    private int goldPerClick = 1;
    @FXML
    private Pane pn_Mine;
    private Label label_goldPerClick;
    @FXML
    private VBox vBox_Gold;
    @FXML
    private ImageView imageContainer_roca;
    
    AnimationVolatile animation;
    
    
    
    Document xml;
    ArrayList<String> goldInAccount;
    ArrayList<String> id;
    ArrayList<String> name;
    ArrayList<String> lvl;
    ArrayList<String> base_atk;
    ArrayList<String> prob;
    @FXML
    private ImageView imageContainer_roca1;
    GameLoop gameBack;
    GameLoop gamePickaxe;
    @FXML
    private ImageView iv_back;
    @FXML
    private Button bBack;
    @FXML
    private ImageView iv_gold;
    @FXML
    private Label lb_gold;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        TemplateXMLonlyRead AccountInfoReader = new readGoldFileAccountInfo();
        gold = Integer.parseInt(AccountInfoReader.readXML().get(0).toString());
        lb_gold.setText(gold+"");
        
        goldPerClick = AccountInfoReader.bossLvl + ((int) Math.pow(1.08,AccountInfoReader.bossLvl));
        
        imageContainer_roca1.sceneProperty().addListener((obs, oldScene, newScene) -> {
        Platform.runLater(() -> {
            Stage stage = (Stage) imageContainer_roca1.getScene().getWindow();
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
        imageContainer_roca1.setFitHeight(200);
        imageContainer_roca1.setFitWidth(200);
        imageContainer_roca1.setImage(new Image("/clickerg/visualsAndFiles/animations/extra/id0/id0 (1).gif"));
        gameBack = new GameLoop("3", iv_back, "background");
        gameBack.startGame();
        
       // gamePickaxe = new GameLoop("3",imageContainer_roca1 , "boss");
       // gamePickaxe.startGame();
        
        
         iv_gold.setImage(new Image("/clickerg/visualsAndFiles/icons/goldBag.gif"));
        
         ImageView iv = new ImageView();
         iv.setImage(new Image("/clickerg/visualsAndFiles/icons/back.png"));
         iv.setFitHeight(50);
         iv.setFitWidth(80);
          bBack.setStyle("-fx-background-color: transparent;");
        bBack.setGraphic(iv);
        
        lb_gold.setTextFill(Color.web("#FFFFFF"));
        lb_gold.setStyle("-fx-font-weight: bold");
    }    

    @FXML
    private void goldIncrease(MouseEvent event) {
        gold += goldPerClick;
        lb_gold.setText(gold+"");
        animation = new AnimationVolatile(1400, imageContainer_roca1);
        animation.play();
        animation.startTime();
    }
   
    
    
    @FXML
    private void irATown(ActionEvent event) throws IOException {
        closeMethod();
        Parent reserva = FXMLLoader.load(getClass().getResource("/clickerg/visualsAndFiles/main/main.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Town");
        stage.show();
    }
    
     public void closeMethod(){
            gameBack.setClose(true);
            TemplateXMLWriter goldWriter = new writeGoldFileAccountInfo();
            goldWriter.modifyXML(null, new int[]{gold});    

    }
}
