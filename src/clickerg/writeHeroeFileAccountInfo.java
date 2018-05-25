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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Dragon
 */
public class writeHeroeFileAccountInfo extends TemplateXMLWriter{
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
    protected void writeDocument(ArrayList<AuxiliarHeroe> heroes, int auxiliar){
            
            Element e = null;
            Element heroe;
            
            NodeList heroesData = xml.getElementsByTagName("heroe");

            for(int x = 0;x<heroes.size();x++){
                Node heroeInfo = heroesData.item(x);
                
                Node active = heroeInfo.getChildNodes().item(1);
                
                active.getChildNodes().item(0).setNodeValue(heroes.get(x).getActive());
                
                Node atk = heroeInfo.getChildNodes().item(3);
                
                atk.getChildNodes().item(0).setNodeValue(heroes.get(x).getBase_atk());
                
                Node exp = heroeInfo.getChildNodes().item(5);
                
                exp.getChildNodes().item(0).setNodeValue(heroes.get(x).getExp());
                
                Node id = heroeInfo.getChildNodes().item(7);
                
                id.getChildNodes().item(0).setNodeValue(heroes.get(x).getId());
                
                Node lvl = heroeInfo.getChildNodes().item(9);
                
                lvl.getChildNodes().item(0).setNodeValue(heroes.get(x).getLvl());
                
                Node name = heroeInfo.getChildNodes().item(11);
                
                name.getChildNodes().item(0).setNodeValue(heroes.get(x).getName());
                
               
                /*
                    e = xml.createElement("active");
                    e.appendChild(xml.createTextNode(heroes.get(x).getActive()));
                    heroe.appendChild(e);
                
                
                
                e = xml.createElement("base_atk");
                e.appendChild(xml.createTextNode(heroes.get(x).getBase_atk()));
                heroe.appendChild(e);
                
                e = xml.createElement("exp");
                e.appendChild(xml.createTextNode(heroes.get(x).getExp()));
                heroe.appendChild(e);

                e = xml.createElement("id");
                e.appendChild(xml.createTextNode(heroes.get(x).getId()));
                heroe.appendChild(e);

                e = xml.createElement("lvl");
                e.appendChild(xml.createTextNode(heroes.get(x).getLvl()));
                heroe.appendChild(e);

                e = xml.createElement("name");
                e.appendChild(xml.createTextNode(heroes.get(x).getName()));
                heroe.appendChild(e);

                Node heroeNode = (Node)heroe;
                
                HeroesData.appendChild(heroeNode);
            */
            }
            
           
            
    }
}
