package org.cz.muni.fi.pb138.webrep_A;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.cz.muni.fi.pb138.webrep_A.APIs.WSDLDocManager;
import org.cz.muni.fi.pb138.webrep_A.APIs.WarArchiveManager;
import org.cz.muni.fi.pb138.webrep_A.APIs.XSDManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;
import org.cz.muni.fi.pb138.webrep_A.Impl.WSDLDocManagerImpl;
import org.cz.muni.fi.pb138.webrep_A.Impl.WarArchiveManagerImpl;
import org.cz.muni.fi.pb138.webrep_A.Impl.XSDManagerImpl;
import org.cz.muni.fi.pb138.webrep_A.Parser.WSDLDocParser;
import org.cz.muni.fi.pb138.webrep_A.Parser.WarArchiveParser;
import org.cz.muni.fi.pb138.webrep_A.Parser.WebXMLParser;
import org.cz.muni.fi.pb138.webrep_A.Parser.XSDParser;
import org.xml.sax.SAXException;

/**
 *
 * @author Filip
 */
public class TestingClass {
    public static void main(String[] args) throws IOException, TransformerConfigurationException, TransformerException, ParserConfigurationException, SAXException {
        DatabaseManager wsdlDBManager = new DatabaseManager(Filetype.WSDL);
        DatabaseManager xsdDBManager = new DatabaseManager(Filetype.XSD);
        DatabaseManager webXmlDBManager = new DatabaseManager(Filetype.WEB);
        
        WSDLDocManager wsdlManager = new WSDLDocManagerImpl("wsdl",wsdlDBManager);
        XSDManager xsdManager = new XSDManagerImpl("xsd",xsdDBManager);
        WarArchiveManager warManager = new WarArchiveManagerImpl("web",webXmlDBManager);
        
        WSDLDocParser wsdlParser = new WSDLDocParser();
        XSDParser xsdParser = new XSDParser();
        WarArchiveParser warParser = new WarArchiveParser();
        WebXMLParser webParser = new WebXMLParser();
        
        File testWSDL = new File("C:\\Users\\Filip\\Documents\\NetBeansProjects\\trunk\\"
                + "WebRepository_WebModule\\src\\main\\java\\org\\cz\\muni\\fi\\pb138\\webrep_A\\test_data\\wsdl\\test2.wsdl");
        
        if(testWSDL.exists()) {
            String content = "";
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(testWSDL));
            while ((line = reader.readLine()) != null)
            {
                content += "\n" + line;
            }
            // Cut of the first newline;
            content = content.substring(1);
            // Close the reader
            reader.close();
            
            WSDLDoc wsdl = new WSDLDoc();
            wsdl.setId(new Long(0));
            wsdl.setDate(null);
            wsdl.setFileName(testWSDL.toString());
            wsdl.setDocument(content);
            wsdl.setExtract(Util.docToString(wsdlParser.wsdlExtract(Util.stringToDoc(content))));
            
            wsdlManager.createWSDLCollection();
            wsdlManager.createWSDL(wsdl);
            wsdl.setId(new Long(1));
            wsdlManager.createWSDL(wsdl);
            wsdl.setId(new Long(2));
            wsdlManager.createWSDL(wsdl);
            wsdl.setId(new Long(3));
            wsdlManager.createWSDL(wsdl);
            //wsdlManager.getWSDL(wsdl.getId());
        }
    }
}
