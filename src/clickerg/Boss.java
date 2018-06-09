/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
public class Boss implements Initializable {

    @FXML
    private ProgressBar hp_bar;
    
    int baseHp = 100;
    double bossHp;
    int heroDamage = 5;
    int bossLvl = 1;
    int numberOfBosses;
    int randomNumberGenerated;
    Random random = new Random();
    
    @FXML
    private Label currenthp;
    @FXML
    private Pane panehp;
    @FXML
    private VBox VBox_Boss;
    @FXML
    private ImageView imageBack;
    @FXML
    private ImageView imageBoss;
    @FXML
    private Pane containerPane;
    
    GameLoop gameBoss;
    GameLoop gameBack;
    @FXML
    private Button backButton;
    @FXML
    private Label label_bossName;
   
    
    ArrayList<AuxiliarHeroe> contratados = new ArrayList<AuxiliarHeroe>();
    ArrayList<PatternBoss> bosses = new ArrayList<PatternBoss>();
    ArrayList<AuxiliarItem> items = new ArrayList<AuxiliarItem>();
    ArrayList<AuxiliarItem> itemsOwned = new ArrayList<AuxiliarItem>();
    ArrayList<AuxiliarItem> itemsToSave = new ArrayList<AuxiliarItem>();
    
    static final double escalado_por_nivel = 1.16;
    
    Document xml;
    ArrayList<String> id;
    ArrayList<String> name;
    ArrayList<String> lvl;
    ArrayList<String> base_atk;
    ArrayList<String> prob;
    ArrayList<String> active;
    @FXML
    private Label label_msg;
    LabelTextVolatile labelDe1Segundo;
    boolean firstTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstTime = true;
        hp_bar.setProgress(1);
        
        
        TemplateXMLonlyRead AccountInfoReader = new readBossFileAccountInfo();
        contratados = AccountInfoReader.readXML();
        bossLvl = AccountInfoReader.bossLvl;
        
        try {
            Class.forName("clickerg.KindredBoss");
            Class.forName("clickerg.SylvanasBoss");
            Class.forName("clickerg.MacarraBoss");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Boss.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TemplateXMLonlyRead bossReader = new readBossFileBoss();
        bosses = bossReader.readXML();
        
        TemplateXMLonlyRead itemReader = new readBossFileItem();
        items = itemReader.readXML();
        
        TemplateXMLonlyRead itemsOwnedReader = new readBossItemFileAccountInfo();
        itemsOwned = itemsOwnedReader.readXML();
        
        
        numberOfBosses = bosses.size();
        
        randomNumberGenerated = random.nextInt(numberOfBosses);
        
        baseHp = Integer.parseInt(bosses.get(randomNumberGenerated).getBase_hp());
        label_bossName.setText(bosses.get(randomNumberGenerated).getName()+" Lvl:"+bossLvl);
        
        bossHp = baseHp * Math.pow(escalado_por_nivel, bossLvl-1);
        currenthp.setText((int) bossHp+" / "+(int) bossHp);
        
        for(int x = 0;x<contratados.size();x++){
             if("true".equals(contratados.get(x).getActive())){
                HeroeDamageCalculator HDC = new HeroeDamageCalculatorBase(contratados.get(x));
                HDC = new HeroeDamageCalculatorSlot1(HDC,contratados.get(x),itemsOwned);
                HDC = new HeroeDamageCalculatorSlot2(HDC,contratados.get(x),itemsOwned);
                HDC = new HeroeDamageCalculatorSlot3(HDC,contratados.get(x),itemsOwned);
                heroDamage = (int) HDC.calcularAtaque();
             }
        }
        label_bossName.setTextFill(Color.web("#FFFFFF"));
        label_bossName.setStyle("-fx-font-weight: bold");
        //game loop
        gameBoss = new GameLoop(bosses.get(randomNumberGenerated).getId(), imageBoss, "boss");
        gameBoss.startGame();
        gameBack = new GameLoop("0", imageBack, "background");
        gameBack.startGame();
        
        bossHp = baseHp *  Math.pow(1.16, bossLvl-1);
        
        imageBack.sceneProperty().addListener((obs, oldScene, newScene) -> {
        Platform.runLater(() -> {
            Stage stage = (Stage) hp_bar.getScene().getWindow();
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
        
        ImageView iv = new ImageView();
         iv.setImage(new Image("/clickerg/icons/back.png"));
         iv.setFitHeight(50);
         iv.setFitWidth(80);
          backButton.setStyle("-fx-background-color: transparent;");
        backButton.setGraphic(iv);
    }    

    @FXML
    private void damageBoss(MouseEvent event) {
        hp_bar.setProgress((hp_bar.getProgress()*bossHp-heroDamage)/(int)bossHp);
        if(hp_bar.getProgress()<=0){
            hp_bar.setProgress(1);
            bossLvl++;
            if(random.nextInt(10)<=200){
                searchItem(randomNumberGenerated);
                
            }
            randomNumberGenerated = random.nextInt(numberOfBosses);
            baseHp = Integer.parseInt(bosses.get(randomNumberGenerated).getBase_hp());
            label_bossName.setText(bosses.get(randomNumberGenerated).getName()+" Lvl:"+bossLvl);
            bossHp = baseHp * Math.pow(escalado_por_nivel, bossLvl-1);
            bossHp = Math.floor(bossHp);
            gameBoss.setId(bosses.get(randomNumberGenerated).getId());
        }
        currenthp.setText((int)Math.floor(hp_bar.getProgress()*bossHp)+" / "+(int)bossHp);
    }

    
  
    @FXML
    private void irATown(ActionEvent event) throws IOException {
        closeMethod();
        Parent reserva = FXMLLoader.load(getClass().getResource("main/main.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Town");
        stage.show();
    }
    
    public void closeMethod(){
        gameBack.setClose(true);
        gameBoss.setClose(true);
        TemplateXMLWriter bossWriter = new writeBossFileAccountInfo();
        bossWriter.modifyXML(itemsToSave, new int[]{bossLvl});
    }
    
    public void searchItem(int id){
        if(!firstTime){
            labelDe1Segundo.closeThread();
        }
        for(int counter = 0; counter < items.size();  counter++){
            if(items.get(counter).getId().equals(gameBoss.getId())){
                itemsToSave.add(new AuxiliarItem(items.get(counter).getId(), items.get(counter).getName(), (String.format("%.2f",Double.parseDouble(items.get(counter).getBase_mult())*(random.nextInt(bossLvl)+1)))));
                labelDe1Segundo = new LabelTextVolatile(2500, label_msg, "Te ha tocado el item "+itemsToSave.get(itemsToSave.size()-1).getName()+" que te otorga un multiplicador de "+itemsToSave.get(itemsToSave.size()-1).getBase_mult());
                labelDe1Segundo.startTime();
                firstTime = false;
            }
        }
    }
}
