/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.gacha.persistence;

import clickerg.classes.gacha.logic.AccountHeroe;
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

public class readGachaFileAccountInfo extends TemplateXMLonlyRead{
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
    protected ArrayList<AccountHeroe> readDocument(){
        ArrayList<AccountHeroe> contratos = new ArrayList<AccountHeroe>();
        Element doc = xml.getDocumentElement();
            
            ArrayList<String> id = getTextValue(doc, "id");

            ArrayList<String> name = getTextValue(doc, "name");
            
            ArrayList<String> id_heroe = getTextValue(doc, "id_heroe");

            ArrayList<String> lvl = getTextValue(doc, "lvl");

            ArrayList<String> base_atk = getTextValue(doc, "base_atk");
            
            ArrayList<String> goldInAccount = getTextValue(doc, "gold");
            
            gold = Integer.parseInt(goldInAccount.get(0));
            
            ArrayList<String> active = getTextValue(doc, "active");
            
            ArrayList<String> exp = getTextValue(doc, "exp");
            
            for(int i = 0;i<id.size();i++){
               contratos.add(new AccountHeroe(active.get(i) , id_heroe.get(i), name.get(i), id.get(i), lvl.get(i), base_atk.get(i), exp.get(i)));
            }
            
            return contratos;
    }
    
    
}
