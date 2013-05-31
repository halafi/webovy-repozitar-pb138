package org.cz.muni.fi.pb138.webrep_A;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.cz.muni.fi.pb138.webrep_A.APIs.WSDLDocManager;
import org.cz.muni.fi.pb138.webrep_A.APIs.WarManager;
import org.cz.muni.fi.pb138.webrep_A.APIs.XSDManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;
import org.cz.muni.fi.pb138.webrep_A.Entities.WarArchive;
import org.cz.muni.fi.pb138.webrep_A.Impl.WSDLDocManagerImpl;
import org.cz.muni.fi.pb138.webrep_A.Impl.WarManagerImpl;
import org.cz.muni.fi.pb138.webrep_A.Impl.XSDManagerImpl;
import org.cz.muni.fi.pb138.webrep_A.Parser.WSDLDocParser;
import org.cz.muni.fi.pb138.webrep_A.Parser.WebXMLParser;
import org.cz.muni.fi.pb138.webrep_A.Parser.XSDParser;
import org.w3c.dom.Document;
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
        WarManager warManager = new WarManagerImpl("web",webXmlDBManager);
        
        WSDLDocParser wsdlParser = new WSDLDocParser();
        XSDParser xsdParser = new XSDParser();
        WebXMLParser webParser = new WebXMLParser();
        
        File testWSDL = new File("C:\\Users\\Filip\\Documents\\NetBeansProjects\\trunk\\"
                + "WebRepository_WebModule\\src\\main\\java\\org\\cz\\muni\\fi\\pb138\\webrep_A\\test_data\\wsdl\\webservice.wsdl");
        
        /*if(testWSDL.exists()) {
            String content = Util.readFile(testWSDL);
            
            WSDLDoc wsdl = new WSDLDoc();
            wsdl.setId(new Long(0));
            wsdl.setDate(null);
            wsdl.setFileName(testWSDL.toString());
            wsdl.setDocument(Util.stripXMLHeader(content));
            wsdl.setExtract(Util.docToString(wsdlParser.wsdlExtract(Util.stringToDoc(content))));
            
            wsdlManager.createWSDLCollection();
            wsdlManager.createWSDL(wsdl);
            System.out.println(wsdlManager.getAllWSDLs());
            
            //String output = wsdlManager.findWSDLByData("EndorsementSearch");
            //System.out.println(output);
        }
        */
        
        File testXSD = new File("C:\\Users\\Filip\\Documents\\NetBeansProjects\\trunk\\"
                + "WebRepository_WebModule\\src\\main\\java\\org\\cz\\muni\\fi\\pb138\\webrep_A\\test_data\\xmlSchema\\test.xsd");
        
        /*if(testXSD.exists()) {
            String content = Util.readFile(testXSD);
            XSD xsd = new XSD();
            xsd.setId(new Long(0));
            xsd.setDate(null);
            xsd.setFileName(testWSDL.toString());
            xsd.setDocument(content);
            xsd.setExtract(Util.docToString(xsdParser.xsdExtract(Util.stringToDoc(content))));
            xsdManager.createXSDCollection();
            xsdManager.createXSD(xsd);
            
            String output = xsdManager.getXSD(new Long(0));
            System.out.println(output);
            
        }*/
        
        File testWAR = new File("C:\\Users\\Filip\\Documents\\NetBeansProjects\\trunk\\"
                + "WebRepository_WebModule\\src\\main\\java\\org\\cz\\muni\\fi\\pb138\\webrep_A\\test_data\\Calendar.war");
        
        /*if(testWAR.exists()) {
            Document testWEB = Util.warExtract(testWAR);
            String content = Util.docToString(testWEB);
            System.out.println(content);
            WarArchive war = new WarArchive();
            war.setId(new Long(0));
            war.setDate(null);
            war.setFileName(testWAR.toString());
            war.setWebXml(content);
            warManager.createWARCollection();
            warManager.createWarArchive(war);
            
            //String output = warManager.getWarArchive(new Long(0));
            //System.out.println(output);
        }*/
    }
}
