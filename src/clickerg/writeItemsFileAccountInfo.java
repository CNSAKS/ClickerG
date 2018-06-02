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
 * @author cnsak
 */
public class writeItemsFileAccountInfo extends TemplateXMLWriter{
    @Override 
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
     
    @Override
    protected <T> void writeDocument(ArrayList<T> heroes, int[] auxiliar){
        //auxiliar [0] id_item, auxiliar [1] id_heroe, auxiliar [2] posicion 
        Element e = null;
        Node heroe;
        String item_to_release = "";
        

        NodeList heroesData = xml.getElementsByTagName("heroe");

        for(int i = 0; i<heroesData.getLength(); i++ ){

            Node n = heroesData.item(i);
            Element eleL = (Element) n;
            //limpieza del item
            Element eleX = (Element) eleL.getElementsByTagName("item_1").item(0);
            if(eleX.getFirstChild().getNodeValue().equals(auxiliar[0]+"")){
                eleL.getElementsByTagName("item_1").item(0).setTextContent(-1+"");
            }
            eleX = (Element) eleL.getElementsByTagName("item_2").item(0);
            if(eleX.getFirstChild().getNodeValue().equals(auxiliar[0]+"")){
                eleL.getElementsByTagName("item_2").item(0).setTextContent(-1+"");
            }
            eleX = (Element) eleL.getElementsByTagName("item_3").item(0);
            if(eleX.getFirstChild().getNodeValue().equals(auxiliar[0]+"")){
                eleL.getElementsByTagName("item_3").item(0).setTextContent(-1+"");
            }
            
            //asignacion al heroe
            eleX = (Element) eleL.getElementsByTagName("id_heroe").item(0);
            if(eleX.getFirstChild().getNodeValue().equals(auxiliar[1]+"")){
                item_to_release = eleL.getElementsByTagName("item_"+auxiliar[2]).item(0).getFirstChild().getNodeValue();
                System.out.println(item_to_release);
                eleL.getElementsByTagName("item_"+auxiliar[2]).item(0).setTextContent(auxiliar[0]+"");
            }
        }
        
        NodeList itemsData = xml.getElementsByTagName("item");

        for(int i = 0; i<itemsData.getLength(); i++ ){

            Node n = itemsData.item(i);
            Element eleL = (Element) n;
            //asignacion al item
            Element eleX = (Element) eleL.getElementsByTagName("id_item").item(0);
            if(eleX.getFirstChild().getNodeValue().equals(auxiliar[0]+"")){
                eleL.getElementsByTagName("id_heroe_owner").item(0).setTextContent(auxiliar[1]+"");
            }
            if(eleX.getFirstChild().getNodeValue().equals(item_to_release)){
                eleL.getElementsByTagName("id_heroe_owner").item(0).setTextContent(-1+"");
            }
        }
    }
    
}
