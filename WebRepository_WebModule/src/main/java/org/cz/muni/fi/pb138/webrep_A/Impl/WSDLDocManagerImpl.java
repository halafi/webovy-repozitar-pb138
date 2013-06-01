package org.cz.muni.fi.pb138.webrep_A.Impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.APIs.WSDLDocManager;
import org.cz.muni.fi.pb138.webrep_A.Util.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;
import org.cz.muni.fi.pb138.webrep_A.Entities.XSD;
import org.cz.muni.fi.pb138.webrep_A.Parser.WSDLDocParser;
import org.cz.muni.fi.pb138.webrep_A.Util.Util;
import org.xml.sax.SAXException;
/**
 *
 *
 * @author xmakovic
 */
public class WSDLDocManagerImpl implements WSDLDocManager {
    public static final Logger logger = Logger.getLogger(WSDLDocManagerImpl.class.getName());
    private DatabaseManager dm;

    @Override
    public void setLogger(FileOutputStream fs) {
        logger.addHandler(new StreamHandler(fs, new SimpleFormatter()));
    }
    
    public WSDLDocManagerImpl(DatabaseManager dm) throws IOException {
        this.dm = dm;
    }
    
    @Override
    public void createWSDLCollection() {
        this.dm.createCollection("wsdl");
    }
    
    @Override
    public void createWSDL(WSDLDoc wsdl) {
        //collection must be created!
        String xml = "<wsdl id='"+wsdl.getId().toString()+"' date='"+wsdl.getTimestamp()
                +"' fileName='"+wsdl.getFileName()+"'>"+wsdl.getDocument()+"</wsdl>";
        this.dm.addXML("wsdl", wsdl.getId().toString(),xml);

    }

    
    @Override
    public WSDLDoc getWSDL(Long id) throws BaseXException {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        WSDLDoc wsdl = new WSDLDoc();
        WSDLDocParser wsdlParser = new WSDLDocParser();
        wsdl.setId(id);
        
        wsdl.setFileName(this.dm.queryCollection("declare namespace def = 'http://schemas.xmlsoap.org/wsdl';"
                +" declare namespace soap = 'http://schemas.xmlsoap.org/wsdl/soap/';"
                +" declare namespace tns = 'http://www.examples.com/wsdl/HelloService.wsdl';"
                +" declare namespace xsd = 'http://www.w3.org/2001/XMLSchema';"
                +" for $ wsdl in collection('wsdl')/wsdl[@id='" + id.toString() + "']"
                +" return data($wsdl/@fileName)"));
        wsdl.setTimestamp(this.dm.queryCollection("declare namespace def = 'http://schemas.xmlsoap.org/wsdl';"
                +" declare namespace soap = 'http://schemas.xmlsoap.org/wsdl/soap/';"
                +" declare namespace tns = 'http://www.examples.com/wsdl/HelloService.wsdl';"
                +" declare namespace xsd = 'http://www.w3.org/2001/XMLSchema';"
                +" for $ wsdl in collection('wsdl')/wsdl[@id='" + id.toString() + "']"
                +" return data($wsdl/@date)"));
        wsdl.setDocument(this.dm.queryCollection("declare namespace def = 'http://schemas.xmlsoap.org/wsdl';"
                +" declare namespace soap = 'http://schemas.xmlsoap.org/wsdl/soap/';"
                +" declare namespace tns = 'http://www.examples.com/wsdl/HelloService.wsdl';"
                +" declare namespace xsd = 'http://www.w3.org/2001/XMLSchema';"
                +" collection('wsdl')/wsdl[@id='"+id.toString()+"']/def:definitions"));
        try {
            wsdl.setExtract(Util.docToString(wsdlParser.wsdlExtract(Util.stringToDoc(wsdl.getDocument()))));
        } catch (ParserConfigurationException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return wsdl;
    }

   @Override
    public List<WSDLDoc> getAllWSDLs() throws BaseXException {
        List<WSDLDoc> output = new ArrayList<WSDLDoc>();
        String c = this.dm.queryCollection("count(collection('wsdl')/wsdl)");
        for(int i=0;i<new Integer(c);i++) {
            output.add(this.getWSDL(new Long(i)));
        }
        return output;
    }

    /*
     * Finds WSDL by metadata.
     */    
    @Override
    public List<WSDLDoc> findWSDLByData(String definitonsName) throws BaseXException {
        List<WSDLDoc> output = new ArrayList<WSDLDoc>();
        String query = this.dm.queryCollection(" declare namespace def = 'http://schemas.xmlsoap.org/wsdl';"                 
                + " for $wsdl in collection('wsdl')/wsdl "
                + " let $nameDef := $wsdl/def:definitions/@name"
                + " let $name := $wsdl/definitions/@name"               
                + " let $ret := fn:concat($name,$nameDef)"
                + " where fn:contains($ret,'"+definitonsName+"')"
                + " return distinct-values($wsdl/@id)");
        String strarray[] = query.split(" ");
        int intarray[] = new int[strarray.length];
        for (int i=0; i < intarray.length; i++) {
            intarray[i] = Integer.parseInt(strarray[i]);
        }
        for (int x : intarray) {
            output.add(this.getWSDL(new Long(x)));
        }
        return output;
    }
  
}
