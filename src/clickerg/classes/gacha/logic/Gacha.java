/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.gacha.logic;

import clickerg.classes.others.auxiliars.AuxiliarHeroe;
import clickerg.classes.others.gameLoop.GameLoop;
import clickerg.classes.others.volatiles.LabelTextVolatile;
import clickerg.classes.others.templates.TemplateXMLWriter;
import clickerg.classes.others.templates.TemplateXMLonlyRead;
import clickerg.classes.gacha.persistence.readGachaFileAccountInfo;
import clickerg.classes.gacha.persistence.writeGachaFileAccountInfo;
import clickerg.classes.gacha.persistence.readGachaFileGacha;
import clickerg.classes.gacha.persistence.readGachaToCloneFileGacha;
import clickerg.classes.others.volatiles.DisablerVolatile;
import clickerg.classes.others.volatiles.ImageVolatile;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.w3c.dom.Document;

/**
 * FXML Controller class
 *
 * @author cnsak
 */
public class Gacha implements Initializable {

    ArrayList<AuxiliarHeroe> contratos = new ArrayList<AuxiliarHeroe>();
    ArrayList<AccountHeroe> contratosToClone = new ArrayList<AccountHeroe>();
    ArrayList<AccountHeroe> contratados = new ArrayList<AccountHeroe>();
    ArrayList<AccountHeroe> contratadosToSave = new ArrayList<AccountHeroe>();
    Document xml;
    ArrayList<String> goldInAccount;
    ArrayList<String> id;
    ArrayList<String> name;
    ArrayList<String> lvl;
    ArrayList<String> base_atk;
    ArrayList<String> prob;
    ArrayList<String> exp;
    private int gold;
    @FXML
    private ImageView iv_gold;
    @FXML
    private Label lb_gold;
    @FXML
    private ImageView iw_heroe;
    @FXML
    private Label lb_summon;
    @FXML
    private ImageView iv_back;
    
    GameLoop gameBack;
    @FXML
    private Button bBack;
    @FXML
    private Label lb_noGold;
    
    LabelTextVolatile noGold;
    
    LabelTextVolatile summonHero;
    @FXML
    private Pane paneGacha;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        readFromXML();
        
        initializeTextAndStyles();
        
        initializeStaticImages();
        
        initializeAnimations();
        
        initializeOnClose();

    }
    
    private void readFromXML(){
    
        TemplateXMLonlyRead readerGacha = new readGachaFileGacha();
        contratos = readerGacha.readXML();
        
        TemplateXMLonlyRead readerGachaToClone = new readGachaToCloneFileGacha();
        contratosToClone = readerGachaToClone.readXML();
        
        TemplateXMLonlyRead readerAccount = new readGachaFileAccountInfo();
        contratados = readerAccount.readXML();
    
        gold = readerAccount.gold;
    }
    
    private void initializeTextAndStyles(){
    
        lb_gold.setText(""+getGold());
        lb_gold.setTextFill(Color.web("#FFFFFF"));
        lb_gold.setStyle("-fx-font-weight: bold");

        lb_noGold.setTextFill(Color.web("#FFFFFF"));
        lb_noGold.setStyle("-fx-font-weight: bold");

        lb_summon.setTextFill(Color.web("#FFFFFF"));
        lb_summon.setStyle("-fx-font-weight: bold");
    
    }
    
    private int getGold(){
    
        return gold;
    }
    
    private void setGold(int goldAmmount){
    
        this.gold = goldAmmount;
    }
    private void initializeAnimations(){
    
        gameBack = new GameLoop("1", iv_back, "background");
        gameBack.startGame();
    
    
    }
    private void initializeStaticImages(){
    
        iv_gold.setImage(new Image("/clickerg/visualsAndFiles/icons/goldBag.gif"));
        
        ImageView iv = new ImageView();
        iv.setImage(new Image("/clickerg/visualsAndFiles/icons/back.png"));
        iv.setFitHeight(50);
        iv.setFitWidth(80);
        
        bBack.setStyle("-fx-background-color: transparent;");
        bBack.setGraphic(iv);
    
    }
    private void initializeOnClose(){
    
        bBack.sceneProperty().addListener((obs, oldScene, newScene) -> {
        Platform.runLater(() -> {
            Stage stage = (Stage) bBack.getScene().getWindow();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                closeMethod();
                Platform.exit();
                System.exit(0);
                }       
            });
        });
    });
    
    }
 
    @FXML
    private void obtainRandomHero(MouseEvent event) throws CloneNotSupportedException   {
        if(checkGold()){
            randomHeroPicker();
        }
    }
    
    private boolean checkGold(){
    
        if(getGold()<500){
            
            showVolatileLabelNoGold();
            return false;
            
        }
        
        return true;
    
    }
    
    private void randomHeroPicker() throws CloneNotSupportedException{
    
        double value = Math.random()*100;
        double actualSearch = 0;
        for(int x = 0; x<contratos.size();x++){
            if(actualSearch+Integer.parseInt(contratos.get(x).getProb())>value){
                searchClone:
                for(AccountHeroe heroe : contratosToClone){
                    if(heroe.getId().equals(contratos.get(x).getId())){
                        cloneHero(heroe);
                        break searchClone;
                    }
                }
                actualizeGold();
                showSummon(x);
                
                return;
            }
            actualSearch+=Integer.parseInt(contratos.get(x).getProb());
        }
    }
    
    private void showSummon(int x){
        showImageVolatile(x);
        showLabelVolatileObtainedHero(x);
        disableEventPane();
    
    }
    private void actualizeGold(){
    
        setGold(getGold()-500);
        lb_gold.setText(""+getGold());
    }
    private void cloneHero(AccountHeroe heroe)throws CloneNotSupportedException{

        contratadosToSave.add((AccountHeroe) heroe.cloneObject());
    }
    
    
    private void showVolatileLabelNoGold(){
       
        noGold = new LabelTextVolatile(2500, lb_summon, "¡No tienes suficiente oro! Te falta " + (500-getGold()));
        noGold.startTime();
    }
    
    private void showLabelVolatileObtainedHero(int x){
        
        summonHero = new LabelTextVolatile(2500, lb_summon, "¡"+ contratos.get(x).getName() + " se ha unido a tu equipo!");
        summonHero.startTime();
    }
    
    private void showImageVolatile(int x){
        
        ImageVolatile img = new ImageVolatile(2500, iw_heroe, "/clickerg/visualsAndFiles/heroes/images/id_" + contratos.get(x).getId()+".png");
        img.startTime();
    }
    
    private void disableEventPane(){
        
        DisablerVolatile paneDisabler = new DisablerVolatile(2500, paneGacha);
        paneDisabler.startTime();
    }


    @FXML
    private void backToPreviousScene(ActionEvent event) throws IOException {
        closeMethod();
        
        Parent parent = FXMLLoader.load(getClass().getResource("/clickerg/visualsAndFiles/main/main.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));

        stage.setTitle("Town");
        stage.show();
    }
    
        
    private void closeMethod(){
        closeGameLoops();
        saveData();
             
    }
  
    private void closeGameLoops(){

        gameBack.setClose(true);
        
    }
    
    private void saveData(){
    
        TemplateXMLWriter gachaWriter = new writeGachaFileAccountInfo();
        gachaWriter.modifyXML(contratadosToSave, new int[]{getGold()});  
        
    }

    
}
