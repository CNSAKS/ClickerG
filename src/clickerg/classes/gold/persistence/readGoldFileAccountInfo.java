/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.gold.persistence;

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
public class readGoldFileAccountInfo extends TemplateXMLonlyRead{
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
    protected ArrayList<String> readDocument(){
        Element doc = xml.getDocumentElement();
        
        ArrayList<String> goldInAccount = getTextValue(doc, "gold");
                      

            ArrayList<String> bossLvlInAccount = getTextValue(doc, "bossLvl");
            
            bossLvl = Integer.parseInt(bossLvlInAccount.get(0));
            
            return goldInAccount;
    }
}
