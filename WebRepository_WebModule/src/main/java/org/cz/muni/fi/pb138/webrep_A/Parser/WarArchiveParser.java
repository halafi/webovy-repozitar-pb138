package org.cz.muni.fi.pb138.webrep_A.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author xfasian
 */
public class WarArchiveParser { 

    public Document WARExtract(JarFile warFile) throws ParserConfigurationException, SAXException, IOException{
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
}
