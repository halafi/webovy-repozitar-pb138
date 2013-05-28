/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author xvana
 */
public class XMLSupplier {
    public static int level = 0;
    
    public static void main(String argv[]) {
 
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("company");
            doc.appendChild(rootElement);

            // staff elements
            Element staff = doc.createElement("Staff");
            rootElement.appendChild(staff);

            // set attribute to staff element
            Attr attr = doc.createAttribute("id");
            attr.setValue("1");
            staff.setAttributeNode(attr);

            // shorten way
            // staff.setAttribute("id", "1");

            // firstname elements
            Element firstname = doc.createElement("firstname");
            firstname.appendChild(doc.createTextNode("yong"));
            staff.appendChild(firstname);

            // lastname elements
            Element lastname = doc.createElement("lastname");
            lastname.appendChild(doc.createTextNode("mook kim"));
            staff.appendChild(lastname);

            // nickname elements
            Element nickname = doc.createElement("nickname");
            nickname.appendChild(doc.createTextNode("mkyong"));
            staff.appendChild(nickname);

            // salary elements
            Element salary = doc.createElement("salary");
            salary.appendChild(doc.createTextNode("100000"));
            staff.appendChild(salary);

            // write the content into xml file
        //		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        //		Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
        //		StreamResult result = new StreamResult(new File("C:\\file.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

        //		transformer.transform(source, result);

            printTag(doc.getDocumentElement());

        } catch (ParserConfigurationException pce) {
        }
    }
    
    public static void printTag(Element element) {
        for (int i = 0; i < level; i++) {
            System.out.print("    ");//&nbsp;&nbsp;&nbsp;&nbsp;");
        }
        System.out.print("<" + element.getTagName());
        NamedNodeMap attributesMap = element.getAttributes();
        for (int i = 0; i < attributesMap.getLength(); i++) {
            Node att = attributesMap.item(i);
            System.out.print(" " + att.getNodeName() + "=\"" + att.getNodeValue() + "\"");
        }
        System.out.println(">");
        
        level++;
        NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) item;
                printTag(el);
            }
        }
        level--;
        
        for (int i = 0; i < level; i++) {
            System.out.print("    ");//&nbsp;&nbsp;&nbsp;&nbsp;");
        }
        System.out.println("</" + element.getTagName() + ">"); 
    }
}
