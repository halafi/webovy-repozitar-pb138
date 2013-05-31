package org.cz.muni.fi.pb138.webrep_A.Impl;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.APIs.WSDLDocManager;
import org.cz.muni.fi.pb138.webrep_A.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;
import org.cz.muni.fi.pb138.webrep_A.Util;
import org.xml.sax.SAXException;
/**
 *
 *
 * @author xmakovic
 */
public class WSDLDocManagerImpl implements WSDLDocManager {
    
    private DatabaseManager dm;

    public WSDLDocManagerImpl(DatabaseManager dm) throws IOException {
        this.dm = dm;
    }
    
    @Override
    public void createWSDLCollection() throws BaseXException {
        this.dm.createCollection("wsdl");
    }
    
    @Override
    public void createWSDL(WSDLDoc wsdl) throws BaseXException {
        //collection must be created!
        String xml = "<wsdl id='"+wsdl.getId().toString()+"' date='"+wsdl.getDate()
                +"' fileName='"+wsdl.getFileName()+"'>"+wsdl.getDocument()+"</wsdl>";
        this.dm.addXML("wsdl", wsdl.getId().toString(),xml);
    }

    
    @Override
    public String getWSDL(Long id) throws BaseXException {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        String wsdl = this.dm.queryCollection("collection('wsdl')/wsdl[@id='"+id.toString()+"']/definitions");
        if (wsdl.equals("")) {
            throw new BaseXException("WSDL does not exist");
        }
        return wsdl;
    }

   @Override
    public String getAllWSDLs() throws BaseXException {
        String query = "for $wsdl in (collection('wsdl')/wsdl) "
                + "return $wsdl";
        return "<WSDLs> " + this.dm.queryCollection(query) + " </WSDLs>";
    }

    /*
     * Finds WSDL by metadata.
     */    
    @Override
    public String findWSDLByData(String definitonsName) throws BaseXException {
        //bitch wont work
        String query = "for $wsdl in collection('wsdl')/wsd) "
                + " let $name := $wsdl/definitions/@name"
                + " where $name='"+definitonsName+"'"
                + " return $wsdl";
        return "<WSDLs> " + this.dm.queryCollection(query) + " </WSDLs>";
    }
  
}
