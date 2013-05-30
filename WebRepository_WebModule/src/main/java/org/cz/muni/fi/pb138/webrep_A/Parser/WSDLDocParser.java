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
public class WSDLDocParser {    
    
    public Document wsdlExtract(Document doc) throws ParserConfigurationException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document output;
        DocumentBuilder builder = factory.newDocumentBuilder();
        output = builder.newDocument(); 

        Element root = (Element) output.createElement("operations_and_messages"); 
        output.appendChild(root);
        
        NodeList operationList = doc.getElementsByTagName("operation");
        NodeList messageList = doc.getElementsByTagName("message");
        
        for (int i = 0; i < operationList.getLength(); i++) {
            if (operationList.item(i) instanceof Element) {
                Element operationElement = (Element) operationList.item(i);
                Node nodeToMove = output.importNode(operationElement, true);
                root.appendChild(nodeToMove);
            }
        }        
        
        for (int i = 0; i <  messageList.getLength(); i++) {
            if (messageList.item(i) instanceof Element) {
                Element messageElement = (Element) messageList.item(i);
                Node nodeToMove = output.importNode(messageElement, true);
                root.appendChild(nodeToMove);
            }
        }
        return output;
    }
}