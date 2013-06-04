package org.cz.muni.fi.pb138.webrep_A.Util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.APIs.WSDLDocManager;
import org.cz.muni.fi.pb138.webrep_A.APIs.WarManager;
import org.cz.muni.fi.pb138.webrep_A.APIs.XSDManager;
import org.cz.muni.fi.pb138.webrep_A.Impl.WSDLDocManagerImpl;
import org.cz.muni.fi.pb138.webrep_A.Impl.WarManagerImpl;
import org.cz.muni.fi.pb138.webrep_A.Impl.XSDManagerImpl;
import org.cz.muni.fi.pb138.webrep_A.Parser.WSDLDocParser;
import org.cz.muni.fi.pb138.webrep_A.Parser.WebXMLParser;
import org.cz.muni.fi.pb138.webrep_A.Parser.XSDParser;
import org.xml.sax.SAXException;
/**
 * For testing
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
        WSDLDocManager wsdlManager = new WSDLDocManagerImpl(wsdlDBManager);
        XSDManager xsdManager = new XSDManagerImpl(xsdDBManager);
        WarManager warManager = new WarManagerImpl(warXmlDBManager);
        WSDLDocParser wsdlParser = new WSDLDocParser();
        XSDParser xsdParser = new XSDParser();
        WebXMLParser webParser = new WebXMLParser();

    }
}
