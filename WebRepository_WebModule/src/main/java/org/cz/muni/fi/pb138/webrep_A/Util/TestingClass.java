package org.cz.muni.fi.pb138.webrep_A.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import org.basex.core.BaseXException;
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
    public static void main(String[] args) throws IOException, TransformerConfigurationException, TransformerException, ParserConfigurationException, SAXException, BaseXException, BaseXException, ParseException {
        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream("main.log", true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestingClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        DatabaseManager wsdlDBManager = new DatabaseManager(Filetype.WSDL);
        DatabaseManager xsdDBManager = new DatabaseManager(Filetype.XSD);
        DatabaseManager warXmlDBManager = new DatabaseManager(Filetype.WAR);
        wsdlDBManager.setLogger(fs);
        xsdDBManager.setLogger(fs);
        warXmlDBManager.setLogger(fs);
        WSDLDocManager wsdlManager = new WSDLDocManagerImpl(wsdlDBManager);
        XSDManager xsdManager = new XSDManagerImpl(xsdDBManager);
        WarManager warManager = new WarManagerImpl(warXmlDBManager);
        wsdlManager.setLogger(fs);
        xsdManager.setLogger(fs);
        warManager.setLogger(fs);
        WSDLDocParser wsdlParser = new WSDLDocParser();
        XSDParser xsdParser = new XSDParser();
        WebXMLParser webParser = new WebXMLParser();
        

        File testWSDL = new File("C:\\Users\\Filip\\Documents\\NetBeansProjects\\trunk\\"
                + "WebRepository_WebModule\\src\\main\\java\\org\\cz\\muni\\fi\\pb138\\webrep_A\\test_data\\wsdl\\IRSACatalogSearchServices.wsdl");
        if(testWSDL.exists()) {
            String content = Util.readFile(testWSDL);
            
            WSDLDoc wsdl = new WSDLDoc();
            wsdl.setId(wsdlManager.getNewId());
            wsdl.setTimestamp(Util.getTimeStamp());
            wsdl.setFileName(testWSDL.toString());
            wsdl.setDocument(Util.stripXMLHeader(content));
            wsdl.setExtract(Util.docToString(wsdlParser.wsdlExtract(Util.stringToDoc(content))));

            StringReader reader = new StringReader(wsdl.getDocument());
            StringWriter writer = new StringWriter();
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource("C:\\Users\\Filip\\Documents\\NetBeansProjects\\trunk\\WebRepository_WebModule\\src\\main\\java\\xmlTransform.xsl"));
            transformer.transform(new javax.xml.transform.stream.StreamSource(reader), new javax.xml.transform.stream.StreamResult(writer));
            wsdl.setDocument(writer.toString());
            reader = new StringReader(wsdl.getExtract());
            writer = new StringWriter();
            transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource("C:\\Users\\Filip\\Documents\\NetBeansProjects\\trunk\\WebRepository_WebModule\\src\\main\\java\\xmlTransform.xsl"));
            transformer.transform(new javax.xml.transform.stream.StreamSource(reader), new javax.xml.transform.stream.StreamResult(writer));
            wsdl.setExtract(writer.toString());

            wsdlManager.createWSDL(wsdl);
            
            System.out.println(wsdlManager.getAllWSDLs());
        }
        
        /*
            File testXSD = new File("C:\\Users\\Filip\\Documents\\NetBeansProjects\\trunk\\"
                + "WebRepository_WebModule\\src\\main\\java\\org\\cz\\muni\\fi\\pb138\\webrep_A\\test_data\\xmlSchema\\test.xsd");
         if(testXSD.exists()) {
            String content = Util.readFile(testXSD);
            XSD xsd = new XSD();
            xsd.setId(xsdManager.getNewId());
            xsd.setTimestamp(Util.getTimeStamp());
            xsd.setFileName(testXSD.toString());
            xsd.setDocument(Util.stripXMLHeader(content));
            xsd.setExtract(Util.docToString(xsdParser.xsdExtract(Util.stringToDoc(content))));
            //xsdManager.createXSDCollection();
            System.out.println(xsd.getId());
            xsdManager.createXSD(xsd);
            xsd.setId(xsdManager.getNewId());
            xsdManager.createXSD(xsd);
            System.out.println(xsd.getId());
            //XSD xsd2 = xsdManager.getXSD(new Long(0));
            //System.out.println(xsdManager.getAllXSDs());
            
        }
        */
        
        
        File testWAR = new File("C:\\Users\\Filip\\Documents\\NetBeansProjects\\trunk\\"
                + "WebRepository_WebModule\\src\\main\\java\\org\\cz\\muni\\fi\\pb138\\webrep_A\\test_data\\jop.war");
        if(testWAR.exists()) {
            /*Document testWEB = Util.warExtract(testWAR);
            String content = Util.docToString(testWEB);
            WarArchive war = new WarArchive();
            war.setId(warManager.getNewId());
            war.setTimestamp(Util.getTimeStamp());
            war.setFileName(testWAR.toString());
            war.setWebXml(content);
            war.setExtract(Util.docToString(webParser.webXMLExtract(Util.stringToDoc(content))));
            warManager.createWarArchive(war);*/
            
            List<WarArchive> output = warManager.findWarByData("Filter1");
            System.out.println(output);
        }
    }
}
