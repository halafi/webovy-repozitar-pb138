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
import org.w3c.dom.NodeList;

/**
 *
 * @author xfasian
 */
public class WebXMLParser {
    public Document WebXMLExtract(Document doc) throws ParserConfigurationException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        int i;
                
        Document output;
        DocumentBuilder builder = factory.newDocumentBuilder();
        output = builder.newDocument(); 

        NodeList operationList = doc.getElementsByTagName("listener");
        NodeList messageList = doc.getElementsByTagName("filter");
        
        Element root = (Element)output.createElement("filters_and_listeners"); 
        output.appendChild(root);
        for (i = 0; i < operationList.getLength(); i++) {
            if (operationList.item(i) instanceof Element) {
                Element listenerElement = (Element) operationList.item(i);
                //Node newListener = doc.createElement("listener");
                //root.appendChild(newListener);
                root.appendChild(listenerElement);
            }
        }
        for (i = 0; i <  messageList.getLength(); i++) {
            if (messageList.item(i) instanceof Element) {
                Element filterElement = (Element) messageList.item(i);
                //Node newMessage = doc.createElement("filter");
                //root.appendChild(newMessage);
                root.appendChild(filterElement);
            }
        }
        return output;
    }
}
