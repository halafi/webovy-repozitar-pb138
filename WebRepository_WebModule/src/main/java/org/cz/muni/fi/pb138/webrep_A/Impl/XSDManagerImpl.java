package org.cz.muni.fi.pb138.webrep_A.Impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.APIs.XSDManager;
import org.cz.muni.fi.pb138.webrep_A.Util.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.XSD;
import org.cz.muni.fi.pb138.webrep_A.Parser.XSDParser;
import org.cz.muni.fi.pb138.webrep_A.Util.Util;
import org.xml.sax.SAXException;

/**
 *
 * 
 * @author xmakovic
 */
public class XSDManagerImpl implements XSDManager {
    public static final Logger logger = Logger.getLogger(XSDManagerImpl.class.getName());
    private DatabaseManager dm;

    public XSDManagerImpl(DatabaseManager dm) throws IOException {
        this.dm = dm;
    }
    
    @Override
    public void setLogger(FileOutputStream fs) {
        logger.addHandler(new StreamHandler(fs, new SimpleFormatter()));
    }

    @Override
    public void createXSDCollection() {
        this.dm.createCollection("xsd");
    }

    @Override
    public void createXSD(XSD xsd) {
        //collection must be created!
        String xml = "<xsd id='"+xsd.getId().toString()+"' date='"+xsd.getDate()
                +"' fileName='"+xsd.getFileName()+"'>"+xsd.getDocument()+"</xsd>";
        this.dm.addXML("xsd", xsd.getId().toString(),xml);
    }

    @Override
    public XSD getXSD(Long id) throws TransformerConfigurationException, 
                                                             TransformerConfigurationException, 
                                                             TransformerException, 
                                                             SAXException, 
                                                             ParserConfigurationException, 
                                                             IOException,
                                                             BaseXException,
                                                             ParseException{
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        XSD schema = new XSD();
        XSDParser xsdParser = new XSDParser();
        String xsd = this.dm.queryCollection("declare namespace xsd = 'http://www.w3.org/2001/XMLSchema';"
                + "collection('xsd')/xsd[@id='" + id.toString() + "']/xsd:schema");
        String date = this.dm.queryCollection("declare namespace xsd = 'http://www.w3.org/2001/XMLSchema';"
                + " for $xsd in collection('xsd')/xsd[@id='" + id.toString() + "']"
                + " return data($xsd/@date)");
        String fileName = this.dm.queryCollection("declare namespace xsd = 'http://www.w3.org/2001/XMLSchema';"
                + " for $xsd in collection('xsd')/xsd[@id='" + id.toString() + "']"
                + " return data($xsd/@fileName)");
        if (xsd.equals("")) {
            throw new BaseXException("Desired xml schema does not exist");
        }
        
        schema.setDocument(xsd);
        schema.setExtract(Util.docToString(xsdParser.xsdExtract(Util.stringToDoc(xsd))));     
        schema.setDate(date);
        schema.setId(id);
        schema.setFileName(fileName);
        return schema;
    }

    @Override
    public String getAllXSDs() throws BaseXException {
        String query = "declare namespace xsd = 'http://www.w3.org/2001/XMLSchema';"
                + "for $xsd in (collection('xsd')/xsd) "
                + "return $xsd";
        return "<XSDs> " + this.dm.queryCollection(query) + " </XSDs>";
    }

    /*
     * Finds XSD by data input.
     */
    @Override
    public String findXSDByData(String s) throws BaseXException{
        String query = "declare namespace xsd = 'http://www.w3.org/2001/XMLSchema';"
                + " for $xsd in collection('xsd')/xsd "
                + " for $nodes in $xsd//*"
                + " $attr in $nodes/xsd:element/@name"
                + " where fn:contains($attr,'"+s+"')"
                + " return $xsd";
        return "<XSDs> " + this.dm.queryCollection(query) + " </XSDs>";
    }
}
