package org.cz.muni.fi.pb138.webrep_A.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class XSDParser {
    /*private Map<String,List<String>> elements;
    private List<String> simpleTypes;
    private List<String> complexTypes;*/
    
    public Document xsdExtract(Document doc) throws ParserConfigurationException{
        String namespace = null;
        if(doc.getDocumentElement().getNodeName().contains("xs")) {
            namespace = "xs:";
        }
        if(doc.getDocumentElement().getNodeName().contains("xsd")) {
            namespace = "xsd:";
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document output;
        DocumentBuilder builder = factory.newDocumentBuilder();
        output = builder.newDocument(); 

        Element root = (Element) output.createElement("types"); 
        output.appendChild(root);

        NodeList complexTypeList = doc.getElementsByTagName(namespace+"complexType");
        NodeList simpleTypeList = doc.getElementsByTagName(namespace+"simpleType");
        NodeList elementsList = doc.getElementsByTagName(namespace+"element");
        NodeList attributeList = doc.getElementsByTagName(namespace+"attribute");
        
        output.appendChild(root);
        for (int i = 0; i < complexTypeList.getLength(); i++) {
            if (complexTypeList.item(i) instanceof Element) {
                Element operationElement = (Element) complexTypeList.item(i);
                Node nodeToMove = output.importNode(operationElement, true);
                root.appendChild(nodeToMove);
            }
        }
        for (int i = 0; i < simpleTypeList.getLength(); i++) {
            if (simpleTypeList.item(i) instanceof Element) {
                Element operationElement = (Element) simpleTypeList.item(i);
                Node nodeToMove = output.importNode(operationElement, true);
                root.appendChild(nodeToMove);
            }
        }
        for (int i = 0; i < elementsList.getLength(); i++) {
            if (elementsList.item(i) instanceof Element) {
                Element operationElement = (Element) elementsList.item(i);
                Node nodeToMove = output.importNode(operationElement, true);
                root.appendChild(nodeToMove);
            }
        }
        for (int i = 0; i < attributeList.getLength(); i++) {
            if (attributeList.item(i) instanceof Element) {
                Element operationElement = (Element) attributeList.item(i);
                Node nodeToMove = output.importNode(operationElement, true);
                root.appendChild(nodeToMove);
            }
        
        }
        return output;
    }
}
      