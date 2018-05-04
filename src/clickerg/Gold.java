/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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
    
    
     ArrayList<AuxiliarHeroe> contratados = new ArrayList<AuxiliarHeroe>();
    Document xml;
    ArrayList<String> goldInAccount;
    ArrayList<String> id;
    ArrayList<String> name;
    ArrayList<String> lvl;
    ArrayList<String> base_atk;
    ArrayList<String> prob;
    @FXML
    private ImageView imageContainer_roca1;
    
    
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
   
    @FXML
    private void pruebaActu(ActionEvent event){
        try{
        String filepath = "src/clickerg/main/accountInfo.xml";
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(filepath);
        
        Node data = doc.getFirstChild();
        
        Node goldData = doc.getElementsByTagName("gold").item(0);
        
        goldData.setTextContent(gold + "");
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filepath));
        transformer.transform(source, result);
        
        }catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        // TODO Auto-generated catch block
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

