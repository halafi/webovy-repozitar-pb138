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
        Document output;
        DocumentBuilder builder = factory.newDocumentBuilder();
        output = builder.newDocument(); 

        NodeList complexTypeList = doc.getElementsByTagName("complexType");
        NodeList simpleTypeList = doc.getElementsByTagName("simpleType");

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
