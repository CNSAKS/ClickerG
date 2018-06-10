/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.boss.persistence;

import clickerg.classes.boss.logic.BossFactory;
import clickerg.classes.boss.logic.PatternBoss;
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
public class readBossFileBoss extends TemplateXMLonlyRead{
    protected void openDocument(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            xml = db.parse("src/clickerg/visualsAndFiles/boss/boss.xml");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(readBossFileBoss.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(readBossFileBoss.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(readBossFileBoss.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected ArrayList<PatternBoss> readDocument(){
        BossFactory bossFactory = new BossFactory();
        ArrayList<PatternBoss> bosses = new ArrayList<PatternBoss>();
        Element doc = xml.getDocumentElement();
            
           // ArrayList<String> base_hp = getTextValue(doc, "base_hp");

            //ArrayList<String> name = getTextValue(doc, "name");
            
            ArrayList<String> id = getTextValue(doc, "id");

            for(int i = 0;i<id.size();i++){

                bosses.add(bossFactory.createBoss(id.get(i)));
            }
            return bosses;
    }
}
