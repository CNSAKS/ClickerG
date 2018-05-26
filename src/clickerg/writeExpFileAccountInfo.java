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
public class writeExpFileAccountInfo extends TemplateXMLWriter{
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
        Node heroe;
        String exp = "0";
        String lvl = "0";
        
        for(int i = 0; i<heroes.size(); i++){
        
            if(heroes.get(i).getId_heroe().equals(auxiliar+"")){
                
                exp = heroes.get(i).getExp();
                lvl = heroes.get(i).getLvl();
                System.out.println("Experiencia : " + exp + ". Nivel : " + lvl);
            }
        }
        NodeList heroesData = xml.getElementsByTagName("heroe");

        for(int i = 0; i<heroesData.getLength(); i++ ){

            Node n = heroesData.item(i);
            Element eleL = (Element) n;
           // eleL.getElementsByTagName("active").item(0).setTextContent("false");
           
            Element eleX = (Element) eleL.getElementsByTagName("id_heroe").item(0);
            
            if(eleX.getFirstChild().getNodeValue().equals(auxiliar+"")){
                eleL.getElementsByTagName("lvl").item(0).setTextContent(lvl);
                eleL.getElementsByTagName("exp").item(0).setTextContent(exp);
                
            }



        }
            
          
    }
            
}