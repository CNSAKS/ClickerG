/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.ProcessBuilder.Redirect.Type;
import static java.lang.System.in;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

/**
 * FXML Controller class
 *
 * @author cnsak
 */
public class Gacha implements Initializable {

    ArrayList<AuxiliarHeroe> contratos = new ArrayList<AuxiliarHeroe>();
    ArrayList<AuxiliarHeroe> contratados = new ArrayList<AuxiliarHeroe>();
    Document xml;
    ArrayList<String> id;
    ArrayList<String> name;
    ArrayList<String> lvl;
    ArrayList<String> base_atk;
    ArrayList<String> prob;
    @FXML
    private VBox vBox_Gacha;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadfromXML("C:\\Users\\cnsak\\Documents\\NetBeansProjects\\ClickerG-master\\src\\clickerg\\gacha\\gacha.xml", 0);
        loadfromXML("C:\\Users\\cnsak\\Documents\\NetBeansProjects\\ClickerG-master\\src\\clickerg\\gacha\\have.xml", 1);
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
    private void summon(MouseEvent event) {
        double value = Math.random()*100;
        double actualSearch = 0;
        for(int x = 0; x<contratos.size();x++){
            if(actualSearch+Integer.parseInt(contratos.get(x).getProb())>value){
                System.out.println("Te toco "+contratos.get(x).toString());
                contratados.add(contratos.get(x));
                return;
            }
            actualSearch+=Integer.parseInt(contratos.get(x).getProb());
        }
    }
    
    public void saveToXML(String xmlRoute) {
        Document dom;
        Element e = null;
        Element heroe;

        // instance of a DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();

            dom = db.newDocument();

            Element rootEle = dom.createElement("tienes");

            for(int x =0;x<contratados.size();x++){
                heroe = dom.createElement("heroe");

                e = dom.createElement("base_atk");
                e.appendChild(dom.createTextNode(contratados.get(x).getBase_atk()));
                heroe.appendChild(e);

                e = dom.createElement("id");
                e.appendChild(dom.createTextNode(contratados.get(x).getId()));
                heroe.appendChild(e);

                e = dom.createElement("lvl");
                e.appendChild(dom.createTextNode(contratados.get(x).getLvl()));
                heroe.appendChild(e);

                e = dom.createElement("name");
                e.appendChild(dom.createTextNode(contratados.get(x).getName()));
                heroe.appendChild(e);

                rootEle.appendChild(heroe);
            }

            dom.appendChild(rootEle);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                //tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom), 
                                     new StreamResult(new FileOutputStream(xmlRoute)));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }

    
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
            
            if(mode==0){
                prob = getTextValue(doc, "prob");
                for(int i = 0;i<id.size();i++){
                    contratos.add(new AuxiliarHeroe(id.get(i), name.get(i), lvl.get(i), base_atk.get(i), prob.get(i)));
                }
            }else{
                for(int i = 0;i<id.size();i++){
                    contratados.add(new AuxiliarHeroe(id.get(i), name.get(i), lvl.get(i), base_atk.get(i), prob.get(i)));
                }
            }
            
        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
    @FXML
    private void savePrueba(ActionEvent event) {
        saveToXML("C:\\Users\\cnsak\\Documents\\NetBeansProjects\\ClickerG-master\\src\\clickerg\\gacha\\have.xml");
    }
        
  
    
}
