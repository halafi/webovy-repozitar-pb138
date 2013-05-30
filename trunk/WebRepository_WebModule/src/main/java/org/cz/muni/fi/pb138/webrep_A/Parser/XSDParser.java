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
public class XSDParser {
    public Document xsdExtract(Document doc) throws ParserConfigurationException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document output;
        DocumentBuilder builder = factory.newDocumentBuilder();
        output = builder.newDocument(); 
        String namespace;
        if(doc.getDocumentElement().getNodeName().contains("xsd")) {
            namespace = "xsd:";
        }
        else if(doc.getDocumentElement().getNodeName().contains("xs")) {
            namespace = "xs:";
        }
        else {
            namespace = "";
        }
        System.out.println(namespace);
        namespace = "xsd:";
        
        Element root = (Element) output.createElement("types"); 
        output.appendChild(root);

        NodeList complexTypeList = doc.getElementsByTagName(namespace+"complexType");
        NodeList simpleTypeList = doc.getElementsByTagName(namespace+"simpleType");
        
        for (int i = 0; i < complexTypeList.getLength(); i++) {
            if (complexTypeList.item(i) instanceof Element) {
                Element complexElement = (Element) complexTypeList.item(i);
                Node nodeToMove = output.importNode(complexElement, false);
                NodeList elementList = complexElement.getElementsByTagName(namespace+"element");
                for (int j = 0; j < elementList.getLength(); j++) {
                    Element element = (Element) elementList.item(j);
                    Node subnodeToMove = output.importNode(element, false);
                    nodeToMove.appendChild(subnodeToMove); 
                }
                NodeList attributeList = complexElement.getElementsByTagName(namespace+"attribute"); 
                for (int k = 0; k < attributeList.getLength(); k++) {
                    Element attribute = (Element) attributeList.item(k);
                    Node subnodeToMove = output.importNode(attribute, false);
                    nodeToMove.appendChild(subnodeToMove); 
                }
                root.appendChild(nodeToMove);
            }
        }
        for (int i = 0; i < simpleTypeList.getLength(); i++) {
            if (simpleTypeList.item(i) instanceof Element) {
                Element simpleElement = (Element) simpleTypeList.item(i);
                Node nodeToMove = output.importNode(simpleElement, true);
                root.appendChild(nodeToMove);
            }
        }
        return output;
    }
}
      