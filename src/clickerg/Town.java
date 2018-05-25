/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    private AnchorPane AnchorPane_main;
    @FXML
    private Button bt_gacha;
    @FXML
    private Button bt_heroes;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        File f = new File("src/clickerg/main/accountInfo.xml");
         if(!f.exists())
         {
            System.out.println("He");
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

                    // write the content into xml file
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new File("src/clickerg/main/accountInfo.xml"));

                    // Output to console for testing
                    // StreamResult result = new StreamResult(System.out);

                    transformer.transform(source, result);

                    System.out.println("File saved!");

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
         
         }
    }

    @FXML
    private void clickGold(ActionEvent event) throws IOException {
        Parent reserva = FXMLLoader.load(getClass().getResource("gold/gold.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Cave");
        stage.show();
    }

    @FXML
    private void clickBoss(ActionEvent event) throws IOException {
        Parent reserva = FXMLLoader.load(getClass().getResource("boss/boss.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Reserva de entradas");
        stage.show();
    }

    @FXML
    private void clickExp(ActionEvent event) throws IOException {
        Parent reserva = FXMLLoader.load(getClass().getResource("exp/exp.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Exp");
        stage.show();
    }

    @FXML
    private void clickGacha(ActionEvent event) throws IOException {
        Parent reserva = FXMLLoader.load(getClass().getResource("gacha/gacha.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Gacha");
        stage.show();
    }
    
    private boolean nuevaPartida(){
        Boolean nueva = false;
        
        try{
        String filepath = "src/clickerg/main/accountInfo.xml";
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
         Parent reserva = FXMLLoader.load(getClass().getResource("heroes/heroes.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Heroes");
        stage.show();
    }

}
