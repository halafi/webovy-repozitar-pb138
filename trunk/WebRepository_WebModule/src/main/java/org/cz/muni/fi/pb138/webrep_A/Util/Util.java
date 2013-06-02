package org.cz.muni.fi.pb138.webrep_A.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public static Document warExtract(File warFile) {
        try {
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
            output = builder.parse(in);
            return output;
        } catch (SAXException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
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
    public static String stripXMLHeader(String input) {
        Document doc = stringToDoc(input);
        String output = docToString(doc);
        return output;
    }
    
    public static String readFile(File file) {
        BufferedReader reader = null;
        try {
            String content = "";
            String line;
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null)
            {
                content += "\n" + line;
            }
            // Cut of the first newline;
            content = content.substring(1);
            // Close the reader
            reader.close();
            return content;
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    /**
     * Convert dom.Document to String
     * @param doc
     * @return XML document as String
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public static String docToString(Document doc) {
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(domSource, result);
            return writer.toString();
        } catch (TransformerException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Convert String to dom.Document
     * @param xmlSource
     * @return XML document as dom.Document
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws IOException 
     */
    public static Document stringToDoc(String xmlSource) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xmlSource)));
        } catch (SAXException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}