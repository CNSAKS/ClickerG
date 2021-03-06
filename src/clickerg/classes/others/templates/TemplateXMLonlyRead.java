/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.others.templates;

import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author cnsak
 */
public abstract class TemplateXMLonlyRead {
    public Document xml;
    public int gold;
    public int bossLvl;
    public final <T> ArrayList<T> readXML() {
      openDocument();
      return readDocument();
    }
    //Template methods
    protected abstract void openDocument();
    protected abstract <T> ArrayList<T> readDocument();
    
    protected ArrayList<String> getTextValue(Element doc, String tag) {
        ArrayList<String> value = new ArrayList<String>();
        NodeList nl= doc.getElementsByTagName(tag);
        
        for(int x = 0;x<nl.getLength();x++){
           value.add(nl.item(x).getFirstChild().getNodeValue());
        }
        return value;
    }
}
