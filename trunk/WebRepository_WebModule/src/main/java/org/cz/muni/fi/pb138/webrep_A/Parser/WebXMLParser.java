/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A.Parser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author xfasian
 */
public class WebXMLParser {
    public Document webXMLExtract(Document doc) throws ParserConfigurationException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document output;
        DocumentBuilder builder = factory.newDocumentBuilder();
        output = builder.newDocument(); 

        Element root = (Element) output.createElement("filters_and_listeners"); 
        output.appendChild(root);
        
        NodeList filterList = doc.getElementsByTagName("filter");
        for (int i = 0; i < filterList.getLength(); i++) {
            if (filterList.item(i) instanceof Element) {
                Element operationElement = (Element) filterList.item(i);
                Node nodeToMove = output.importNode(operationElement, true);
                root.appendChild(nodeToMove);
            }
        }
        
        NodeList listenerList = doc.getElementsByTagName("listener");        
        for (int i = 0; i <  listenerList.getLength(); i++) {
            if (listenerList.item(i) instanceof Element) {
                Element messageElement = (Element) listenerList.item(i);
                Node nodeToMove = output.importNode(messageElement, true);
                root.appendChild(nodeToMove);
            }
        }
        return output;
    }
}
