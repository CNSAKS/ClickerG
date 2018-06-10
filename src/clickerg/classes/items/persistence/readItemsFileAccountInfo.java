/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.items.persistence;

import clickerg.classes.others.auxiliars.AuxiliarItem;
import clickerg.classes.others.templates.TemplateXMLonlyRead;
import clickerg.classes.gacha.persistence.readGachaFileGacha;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author cnsak
 */

public class readItemsFileAccountInfo extends TemplateXMLonlyRead{
    protected void openDocument(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            xml = db.parse("src/clickerg/main/accountInfo.xml");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(readGachaFileGacha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(readGachaFileGacha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(readGachaFileGacha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected ArrayList<AuxiliarItem> readDocument(){
        ArrayList<AuxiliarItem> items = new ArrayList<AuxiliarItem>();
        Element doc = xml.getDocumentElement();
            
            ArrayList<String> id = getTextValue(doc, "id_item");

            ArrayList<String> name = getTextValue(doc, "name_item");
            
            ArrayList<String> base_mult = getTextValue(doc, "base_mult");
            
            ArrayList<String> equipado = getTextValue(doc, "id_heroe_owner");
            
            for(int i = 0;i<id.size();i++){
                items.add(new AuxiliarItem(id.get(i), name.get(i), base_mult.get(i), equipado.get(i)));
            }
            
            return items;
    }
    
    
}
