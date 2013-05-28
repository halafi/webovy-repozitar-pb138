package org.cz.muni.fi.pb138.webrep_A;

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
    private Map<String,List<String>> elements;
    private List<String> simpleTypes;
    private List<String> complexTypes;
    
    public Document XSDExtract(Document doc) throws ParserConfigurationException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        int i;
        String namespace = "xs:";
        //doimplementovat get z root elementu cist dokud ":"
        Document output;
        DocumentBuilder builder = factory.newDocumentBuilder();
        output = builder.newDocument(); 

        NodeList complexTypeList = doc.getElementsByTagName(namespace+"complexType");
        NodeList simpleTypeList = doc.getElementsByTagName(namespace+"simpleType");
        NodeList elementsList = doc.getElementsByTagName(namespace+"element");
        NodeList attributeList = doc.getElementsByTagName(namespace+"attribute");
        
        Element root = (Element)output.createElement("types"); 
        output.appendChild(root);
        for (i = 0; i < complexTypeList.getLength(); i++) {
            if (complexTypeList.item(i) instanceof Element) {
                Element complexElement = (Element) complexTypeList.item(i);
                Node newcom = doc.createElement("complexType");
                root.appendChild(newcom);
            }
        }
        for (i = 0; i < simpleTypeList.getLength(); i++) {
            if (simpleTypeList.item(i) instanceof Element) {
                Element simpleElement = (Element) simpleTypeList.item(i);
                Node newsim = doc.createElement("simpleType");
                root.appendChild(newsim);
            }
        }
        for (i = 0; i < elementsList.getLength(); i++) {
            if (elementsList.item(i) instanceof Element) {
                Element elementElement = (Element) elementsList.item(i);
                Node newele = doc.createElement("element");
                root.appendChild(newele);
            }
        }
        for (i = 0; i < attributeList.getLength(); i++) {
            if (attributeList.item(i) instanceof Element) {
                Element attElement = (Element) attributeList.item(i);
                Node newatt = doc.createElement("attribute");
                root.appendChild(newatt);
            }
        }
        return output;
    }
    
    private Map<String,List<String>> getElements(Document doc){
        return null;
    }
    
    private List<String> getSimpleTypes(Document doc){
        int i;
        List<String> simpleTypesOut = new ArrayList<String>();
        NodeList simpleTypeList = doc.getElementsByTagName("simpleType");
        for (i = 0; i < simpleTypeList.getLength(); i++) {
            if (simpleTypeList.item(i) instanceof Element) {
                Element simpleElement = (Element) simpleTypeList.item(i);
                Node newsim = doc.createElement("simpleType");
                //simpleTypesOut.add(newsim);
                //to be continued? or deleted
            }
        }
        return simpleTypesOut;
    }
    
    private List<String> getComplexTypes(Document doc){
        return null;
    }

}
