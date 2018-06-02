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
public class writeGachaFileAccountInfo extends TemplateXMLWriter{
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
    protected <T> void writeDocument(ArrayList<T> heroes, int[] auxiliar){
            
        
        Element e = null;
        Node heroe;
        
           Node HeroesData = xml.getElementsByTagName("heroes").item(0);
            Node goldEle = xml.getElementsByTagName("gold").item(0);
            
            goldEle.setTextContent(auxiliar[0] + "");
            
            int heroesNumber = xml.getElementsByTagName("heroe").getLength();
            
            for(int x = 0;x<heroes.size();x++){
                heroe = xml.createElement("heroe");
                
                //borrar esta linea solo pruebas
                if(heroesNumber == 0){
                    if(x==0){
                        e = xml.createElement("active");
                        e.appendChild(xml.createTextNode("true"));
                        heroe.appendChild(e);
                    }else{
                        e = xml.createElement("active");
                        e.appendChild(xml.createTextNode("false"));
                        heroe.appendChild(e);
                    }
                }else{
                    e = xml.createElement("active");
                    e.appendChild(xml.createTextNode("false"));
                    heroe.appendChild(e);
                }
                
                
                e = xml.createElement("base_atk");
                e.appendChild(xml.createTextNode(((AccountHeroe)heroes.get(x)).getBase_atk()));
                heroe.appendChild(e);
                
                e = xml.createElement("exp");
                e.appendChild(xml.createTextNode(((AccountHeroe)heroes.get(x)).getExp()));
                heroe.appendChild(e);

                e = xml.createElement("id");
                e.appendChild(xml.createTextNode(((AccountHeroe)heroes.get(x)).getId()));
                heroe.appendChild(e);
                
                e = xml.createElement("id_heroe");
                e.appendChild(xml.createTextNode(heroesNumber+""));
                heroe.appendChild(e);

                e = xml.createElement("lvl");
                e.appendChild(xml.createTextNode(((AccountHeroe)heroes.get(x)).getLvl()));
                heroe.appendChild(e);

                e = xml.createElement("name");
                e.appendChild(xml.createTextNode(((AccountHeroe)heroes.get(x)).getName()));
                heroe.appendChild(e);
                
                e = xml.createElement("item_1");
                e.appendChild(xml.createTextNode(-1+""));
                heroe.appendChild(e);
                
                e = xml.createElement("item_2");
                e.appendChild(xml.createTextNode(-1+""));
                heroe.appendChild(e);
                
                e = xml.createElement("item_3");
                e.appendChild(xml.createTextNode(-1+""));
                heroe.appendChild(e);

                Node heroeNode = (Node)heroe;
                
                HeroesData.appendChild(heroeNode);
                
                heroesNumber++;
            
          
    }
            
          
            
          
    }
            
           
}
