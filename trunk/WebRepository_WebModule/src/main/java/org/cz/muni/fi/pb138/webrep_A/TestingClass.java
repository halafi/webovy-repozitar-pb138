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
import org.cz.muni.fi.pb138.webrep_A.Entities.XSD;
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
        
        /*if(testWSDL.exists()) {
            String content =Util.readFile(testWSDL);
            
            WSDLDoc wsdl = new WSDLDoc();
            wsdl.setId(new Long(0));
            wsdl.setDate(null);
            wsdl.setFileName(testWSDL.toString());
            wsdl.setDocument(content);
            wsdl.setExtract(Util.docToString(wsdlParser.wsdlExtract(Util.stringToDoc(content))));
            
            wsdlManager.createWSDLCollection();
            wsdlManager.createWSDL(wsdl);
            //String output = wsdlManager.findWSDLByData("EndorsementSearch");
            //System.out.println(output);
        }*/
        
        File testXSD = new File("C:\\Users\\Filip\\Documents\\NetBeansProjects\\trunk\\"
                + "WebRepository_WebModule\\src\\main\\java\\org\\cz\\muni\\fi\\pb138\\webrep_A\\test_data\\xmlSchema\\test.xsd");
        if(testXSD.exists()) {
            String content = Util.readFile(testXSD);
            XSD xsd = new XSD();
            xsd.setId(new Long(0));
            xsd.setDate(null);
            xsd.setFileName(testWSDL.toString());
            xsd.setDocument(content);
            xsd.setExtract(Util.docToString(xsdParser.xsdExtract(Util.stringToDoc(content))));
            System.out.println(xsd.getExtract());
            xsdManager.createXSDCollection();
            xsdManager.createXSD(xsd);
            
            String output = xsdManager.getAllXSDs();
            //System.out.println(output);
            
        }
    }
}
