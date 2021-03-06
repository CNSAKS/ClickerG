/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.exp.persistence;

import clickerg.classes.others.auxiliars.AuxiliarHeroe;
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
 * @author Dragon
 */
public class readExpFileAccountInfo extends TemplateXMLonlyRead{
     protected void openDocument(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            xml = db.parse("src/clickerg/visualsAndFiles/main/accountInfo.xml");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(readGachaFileGacha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(readGachaFileGacha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(readGachaFileGacha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected ArrayList<AuxiliarHeroe> readDocument(){
        ArrayList<AuxiliarHeroe> contratos = new ArrayList<AuxiliarHeroe>();
        Element doc = xml.getDocumentElement();
            
            ArrayList<String> id = getTextValue(doc, "id");

            ArrayList<String> name = getTextValue(doc, "name");
            
            ArrayList<String> id_heroe = getTextValue(doc, "id_heroe");

            ArrayList<String> lvl = getTextValue(doc, "lvl");

            ArrayList<String> base_atk = getTextValue(doc, "base_atk");
       
            ArrayList<String> active = getTextValue(doc, "active");
            
            ArrayList<String> exp = getTextValue(doc, "exp");
            
            for(int i = 0;i<id.size();i++){
                contratos.add(new AuxiliarHeroe(id.get(i), name.get(i), lvl.get(i), base_atk.get(i),"not nedeed", active.get(i), exp.get(i), id_heroe.get(i)));
            }
            
            return contratos;
    }
}
