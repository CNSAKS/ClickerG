/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author cnsak
 */
public class writeBossFileAccountInfo extends TemplateXMLWriter{
    @Override
    protected void openDocument(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            xml = db.parse("src/clickerg/main/accountInfo.xml");
            route = "src/clickerg/main/accountInfo.xml";
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(readGachaFileGacha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(readGachaFileGacha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(readGachaFileGacha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected <T> void writeDocument(ArrayList<T> items, int auxiliar){
        Element e = null;
        Node itemNode;  
        
        Node ItemsData = xml.getElementsByTagName("items").item(0);
        int itemsNumber = xml.getElementsByTagName("item").getLength();
        
        for(T elem : items) {
                
                itemNode = xml.createElement("item");
                
                e = xml.createElement("base_mult");
                e.appendChild(xml.createTextNode(((AuxiliarItem) elem).getBase_mult()));
                itemNode.appendChild(e);
                
                e = xml.createElement("name_item");
                e.appendChild(xml.createTextNode(((AuxiliarItem) elem).getName()));
                itemNode.appendChild(e);
                
                e = xml.createElement("id_item");
                e.appendChild(xml.createTextNode(itemsNumber+""));
                itemNode.appendChild(e);
                
                e = xml.createElement("id_heroe_owner");
                e.appendChild(xml.createTextNode(-1+""));
                itemNode.appendChild(e);
                
                ItemsData.appendChild(itemNode);
                
                itemsNumber++;
        }
    
        Node bossEle = xml.getElementsByTagName("bossLvl").item(0);

        bossEle.setTextContent(auxiliar + "");
        }
            
            
    

}