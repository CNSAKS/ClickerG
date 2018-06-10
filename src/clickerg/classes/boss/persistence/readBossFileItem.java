/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.boss.persistence;

import clickerg.classes.others.auxiliars.AuxiliarItem;
import clickerg.classes.others.templates.TemplateXMLonlyRead;
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
public class readBossFileItem extends TemplateXMLonlyRead{
    protected void openDocument(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            xml = db.parse("src/clickerg/items/item.xml");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(readBossFileItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(readBossFileItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(readBossFileItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected ArrayList<AuxiliarItem> readDocument(){
        ArrayList<AuxiliarItem> items = new ArrayList<AuxiliarItem>();
        Element doc = xml.getDocumentElement();
            
            ArrayList<String> base_mult = getTextValue(doc, "base_mult");

            ArrayList<String> name = getTextValue(doc, "name");

            ArrayList<String> id = getTextValue(doc, "id");
            
            for(int i = 0;i<id.size();i++){
                items.add(new AuxiliarItem(id.get(i), name.get(i), base_mult.get(i)));
            }
            return items;
    }
}
