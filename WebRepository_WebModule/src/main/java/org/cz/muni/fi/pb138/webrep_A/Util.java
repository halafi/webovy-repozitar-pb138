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
    
    public static Document warExtract(JarFile warFile) throws ParserConfigurationException, SAXException, IOException{
        Enumeration entries = warFile.entries();
        InputStream in = null;
        while (entries.hasMoreElements()) {
           ZipEntry entry = (ZipEntry)entries.nextElement();
           if (entry.getName().equals("web.xml")) {
               in = warFile.getInputStream(entry);
               break;
               //prohledava podslozky?
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
    public static String docToString(Document doc) throws TransformerConfigurationException, TransformerException {
        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);
        return writer.toString();
    }
    
    public static Document stringToDoc(String xmlSource) throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(xmlSource)));
    }
}
