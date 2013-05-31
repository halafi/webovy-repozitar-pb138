package org.cz.muni.fi.pb138.webrep_A;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 
 *
 * @author xmakovic
 */
public class Util {

    /**
     * Extract web.XML from war archive.
     * @param warFile
     * @return web.XML as dom.Document
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */    
    public static Document warExtract(File warFile) throws ParserConfigurationException, 
                                                           SAXException, 
                                                           IOException{
        JarFile jar = new JarFile(warFile);
        Enumeration entries = jar.entries();
        InputStream in = null;
        while (entries.hasMoreElements()) {
           ZipEntry entry = (ZipEntry)entries.nextElement();
           if (entry.getName().toLowerCase().indexOf("web.xml") != -1) {
               in = jar.getInputStream(entry);
               System.out.println("web.xml extracted");
               break;
           }
           if (!entries.hasMoreElements()) {
               throw new IllegalArgumentException("No web.xml file in WAR archive.");
           }
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document output = null;
         try {
             output = builder.parse(in);
         } catch (SAXException ex) {
             throw new SAXException(ex);
         } catch (IOException ex) {
             throw new IOException(ex);
         }
        return output;
    }
    
    /**
     * Strip XML header
     * @param input XML file as String
     * @return XML file as String without XML header
     * @throws TransformerConfigurationException
     * @throws TransformerConfigurationException
     * @throws TransformerException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws IOException 
     */
    public static String stripXMLHeader(String input) throws TransformerConfigurationException, 
                                                             TransformerConfigurationException, 
                                                             TransformerException, 
                                                             SAXException, 
                                                             ParserConfigurationException, 
                                                             IOException {
        Document doc = stringToDoc(input);
        String output = docToString(doc);
        return output;
    }
    
    public static String readFile(File file) throws IOException {
        String content = "";
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null)
        {
            content += "\n" + line;
        }
        // Cut of the first newline;
        content = content.substring(1);
        // Close the reader
        reader.close();
        return content;
    }
    
    /**
     * Convert dom.Document to String
     * @param doc
     * @return XML document as String
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public static String docToString(Document doc) throws TransformerConfigurationException, 
                                                          TransformerException {
        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(domSource, result);
        return writer.toString();
    }
    /**
     * Convert String to dom.Document
     * @param xmlSource
     * @return XML document as dom.Document
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws IOException 
     */
    public static Document stringToDoc(String xmlSource) throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(xmlSource)));
    }
}
