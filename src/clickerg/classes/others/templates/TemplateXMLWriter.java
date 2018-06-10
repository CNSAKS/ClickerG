/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.others.templates;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author cnsak
 */
public abstract class TemplateXMLWriter {
    public Document xml;
    public String route;
    public int gold;
    public int bossLvl;
    public final <T> void modifyXML(ArrayList<T> lista, int[] auxiliar) {
      openDocument();
      writeDocument(lista, auxiliar);
      closeDocument();
    }
    //Template methods
    protected abstract void openDocument();
    
    protected abstract <T> void writeDocument(ArrayList<T> lista, int[] auxiliar);
    
    protected void closeDocument(){
        try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                //tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(xml), 
                                     new StreamResult(new FileOutputStream(route)));

                } catch (TransformerException te) {
                    System.out.println(te.getMessage());
                } catch (IOException ioe) {
                    System.out.println(ioe.getMessage());
                }
    }
    
    protected ArrayList<String> getTextValue(Element doc, String tag) {
        ArrayList<String> value = new ArrayList<String>();
        NodeList nl= doc.getElementsByTagName(tag);
        
        for(int x = 0;x<nl.getLength();x++){
           value.add(nl.item(x).getFirstChild().getNodeValue());
        }
        return value;
    }
}
