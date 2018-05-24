/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
    int heroDamage = 20;
    int bossLvl = 1;
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
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hp_bar.setProgress(1);
        
        
        TemplateXMLonlyRead bossReader = new readBossFileAccountInfo();
        contratados = bossReader.readXML();
        bossLvl = bossReader.bossLvl;
        
        bossHp = baseHp * Math.pow(1.16, bossLvl-1);
        currenthp.setText((int) bossHp+" / "+(int) bossHp);
        
        for(int x = 0;x<contratados.size();x++){
             if("true".equals(contratados.get(x).getActive())){
                heroDamage = Integer.parseInt(contratados.get(x).getBase_atk());
                System.out.println(heroDamage);
             }
        }
        
        //game loop
        gameBoss = new GameLoop(bossLvl%3, imageBoss, "boss");
        gameBoss.startGame();
        gameBack = new GameLoop(0, imageBack, "background");
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
    }    

    @FXML
    private void damageBoss(MouseEvent event) {
        hp_bar.setProgress((hp_bar.getProgress()*bossHp-heroDamage)/(int)bossHp);
        if(hp_bar.getProgress()<=0){
            hp_bar.setProgress(1);
            bossLvl++;
            bossHp = baseHp * Math.pow(1.16, bossLvl-1);
            bossHp = Math.floor(bossHp);
            gameBoss.setId(bossLvl%3);
        }
        currenthp.setText((int)Math.floor(hp_bar.getProgress()*bossHp)+" / "+(int)bossHp);
    }

    ArrayList<AuxiliarHeroe> contratados = new ArrayList<AuxiliarHeroe>();
    Document xml;
    ArrayList<String> id;
    ArrayList<String> name;
    ArrayList<String> lvl;
    ArrayList<String> base_atk;
    ArrayList<String> prob;
    ArrayList<String> active;
    
    public void loadfromXML(String xmlRoute, int mode) {
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the    
            // XML file
            xml = db.parse(xmlRoute);
            
            Element doc = xml.getDocumentElement();
            
            id = getTextValue(doc, "id");

            name = getTextValue(doc, "name");

            lvl = getTextValue(doc, "lvl");

            base_atk = getTextValue(doc, "base_atk");
            
            active = getTextValue(doc, "active");
            
            for(int i = 0;i<id.size();i++){
                contratados.add(new AuxiliarHeroe(id.get(i), name.get(i), lvl.get(i), base_atk.get(i), "0", active.get(i)));
            }
            
        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
    
    private ArrayList<String> getTextValue(Element doc, String tag) {
        ArrayList<String> value = new ArrayList<String>();
        NodeList nl= doc.getElementsByTagName(tag);
        for(int x = 0;x<nl.getLength();x++){
           value.add(nl.item(x).getFirstChild().getNodeValue());
        }
        return value;
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
        bossWriter.modifyXML(null, bossLvl);
    }
}
