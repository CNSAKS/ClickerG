/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private Pane pn_Mine;
    @FXML
    private ProgressBar hp_bar;
    
    int bossHp = 100;
    int heroDamage = 0;
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
        
        loadfromXML("C:\\Users\\cnsak\\Documents\\NetBeansProjects\\ClickerG-master\\src\\clickerg\\gacha\\have.xml", 0);
        for(int x = 0;x<contratados.size();x++){
             if("true".equals(contratados.get(x).getActive())){
                heroDamage = Integer.parseInt(contratados.get(x).getBase_atk());
             }
        }
               
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
}
