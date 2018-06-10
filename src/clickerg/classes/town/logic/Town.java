/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.town.logic;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
import org.xml.sax.SAXException;

/**
 *
 * @author adpeijar
 */
public class Town implements Initializable{
    @FXML
    private Button bt_gold;
    @FXML
    private Button bt_boss;
    @FXML
    private Button bt_exp;
    @FXML
    private Button bt_gacha;
    @FXML
    private Button bt_heroes;
    Stage dialog;
    @FXML
    private Pane pane_main;
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        
        File f = new File("src/clickerg/visualsAndFiles/main/accountInfo.xml");
         if(!f.exists())
         {
            try {

                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                    // root elements
                    Document doc = docBuilder.newDocument();
                    Element rootElement = doc.createElement("accountInfo");
                    doc.appendChild(rootElement);

                    // staff elements
                    Element goldXm = doc.createElement("gold");

                    goldXm.appendChild(doc.createTextNode("500"));

                    rootElement.appendChild(goldXm);

                    // staff elements
                    Element bossLvl = doc.createElement("bossLvl");

                    bossLvl.appendChild(doc.createTextNode("1"));

                    rootElement.appendChild(bossLvl);

                    // firstname elements
                    Element heroesXm = doc.createElement("heroes");
                    rootElement.appendChild(heroesXm);

                    Element itemXm = doc.createElement("items");
                    rootElement.appendChild(itemXm);
                    // write the content into xml file
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new File("src/clickerg/visualsAndFiles/main/accountInfo.xml"));


                    transformer.transform(source, result);


              } catch (ParserConfigurationException pce) {
                    pce.printStackTrace();
              } catch (TransformerException tfe) {
                    tfe.printStackTrace();
              }
        
         }
         
         if(nuevaPartida()){
             
            bt_gold.setDisable(true);
            bt_boss.setDisable(true);
            bt_exp.setDisable(true);
            bt_heroes.setDisable(true);
          
            dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            dialog.initModality(Modality.APPLICATION_MODAL);
            HBox dialogHbox = new HBox(20);
            ImageView imgV = new ImageView(new Image("/clickerg/visualsAndFiles/icons/fairy.png"));
            imgV.setFitHeight(100);
            imgV.setFitWidth(100);
            dialogHbox.getChildren().add(imgV);
            dialogHbox.getChildren().add(new Text("¡Hey! Parece que eres nuevo \n por aqui. \n \n Antes que nada deberías \n reclutar algún guerrero."));
            Scene dialogScene = new Scene(dialogHbox, 300, 130);
            
            dialog.setScene(dialogScene);
            dialog.setAlwaysOnTop(true);
            showDialog();
            
         }
         
         
         ImageView iv1 = new ImageView();
         iv1.setImage(new Image("/clickerg/visualsAndFiles/icons/boss1.png"));
         iv1.setFitHeight(50);
         iv1.setFitWidth(80);
         bt_boss.setStyle("-fx-background-color: transparent;");
        bt_boss.setGraphic(iv1);
        
        ImageView iv2 = new ImageView();
        iv2.setImage(new Image("/clickerg/visualsAndFiles/icons/goldIcon.png"));
         iv2.setFitHeight(50);
         iv2.setFitWidth(80);
         bt_gold.setStyle("-fx-background-color: transparent;");
        bt_gold.setGraphic(iv2);
        
         ImageView iv3 = new ImageView();
        iv3.setImage(new Image("/clickerg/visualsAndFiles/icons/gacha1.png"));
         iv3.setFitHeight(80);
         iv3.setFitWidth(80);
         bt_gacha.setStyle("-fx-background-color: transparent;");
        bt_gacha.setGraphic(iv3);
        
         ImageView iv4 = new ImageView();
        iv4.setImage(new Image("/clickerg/visualsAndFiles/icons/exp1.png"));
         iv4.setFitHeight(100);
         iv4.setFitWidth(100);
         bt_exp.setStyle("-fx-background-color: transparent;");
        bt_exp.setGraphic(iv4);
        
         ImageView iv5 = new ImageView();
        iv5.setImage(new Image("/clickerg/visualsAndFiles/icons/heroes.png"));
         iv5.setFitHeight(80);
         iv5.setFitWidth(80);
         bt_heroes.setStyle("-fx-background-color: transparent;");
        bt_heroes.setGraphic(iv5);
    }

    @FXML
    private void clickGold(ActionEvent event) throws IOException {
        Parent reserva = FXMLLoader.load(getClass().getResource("/clickerg/visualsAndFiles/gold/gold.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Cave");
        stage.show();
    }

    @FXML
    private void clickBoss(ActionEvent event) throws IOException {
        Parent reserva = FXMLLoader.load(getClass().getResource("/clickerg/visualsAndFiles/boss/boss.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Boss");
        stage.show();
    }

    @FXML
    private void clickExp(ActionEvent event) throws IOException {
        Parent reserva = FXMLLoader.load(getClass().getResource("/clickerg/visualsAndFiles/exp/exp.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Exp");
        stage.show();
    }

    @FXML
    private void clickGacha(ActionEvent event) throws IOException {
        if(nuevaPartida()){
        dialog.close();}
        Parent reserva = FXMLLoader.load(getClass().getResource("/clickerg/visualsAndFiles/gacha/gacha.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Gacha");
        stage.show();
    }
    
    private boolean nuevaPartida(){
        Boolean nueva = false;
        
        try{
        String filepath = "src/clickerg/visualsAndFiles/main/accountInfo.xml";
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(filepath);
        
        
        int heroesData = doc.getElementsByTagName("heroes").item(0).getChildNodes().getLength();
        
        
        if(heroesData == 0){
            nueva = true;
        }
        
        
        
        }catch (ParserConfigurationException pce) {
                    pce.printStackTrace();
        }catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
        
            return nueva;
        }
         
    
    }

    @FXML
    private void clickHeroes(ActionEvent event) throws IOException {
         Parent reserva = FXMLLoader.load(getClass().getResource("/clickerg/visualsAndFiles/heroes/heroes.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Heroes");
        stage.show();
    }

    private void showDialog() {
        dialog.show();
    }

}
