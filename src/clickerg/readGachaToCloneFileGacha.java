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
import org.xml.sax.SAXException;

/**
 *
 * @author cnsak
 */
public class readGachaToCloneFileGacha extends TemplateXMLonlyRead{
    protected void openDocument(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            xml = db.parse("src/clickerg/gacha/gacha.xml");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(readGachaToCloneFileGacha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(readGachaToCloneFileGacha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(readGachaToCloneFileGacha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected ArrayList<AccountHeroe> readDocument(){
        ArrayList<AccountHeroe> contratos = new ArrayList<AccountHeroe>();
        //ArrayList<GachaHeroe> contratos1 = new ArrayList<GachaHeroe>();
        Element doc = xml.getDocumentElement();
            
            ArrayList<String> id = getTextValue(doc, "id");

            ArrayList<String> name = getTextValue(doc, "name");

            ArrayList<String> lvl = getTextValue(doc, "lvl");

            ArrayList<String> base_atk = getTextValue(doc, "base_atk");
            
            
            for(int i = 0;i<id.size();i++){
                contratos.add(new AccountHeroe("false" , "-1", name.get(i), id.get(i), lvl.get(i), base_atk.get(i), "0"));
             //   GachaHeroe gh = new GachaHeroe(id.get(i), name.get(i), lvl.get(i), base_atk.get(i), prob.get(i));
            }
            return contratos;
    }
}
