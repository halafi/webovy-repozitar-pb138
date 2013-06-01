package org.cz.muni.fi.pb138.webrep_A.Impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.APIs.WSDLDocManager;
import org.cz.muni.fi.pb138.webrep_A.Util.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;
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
        try {
            this.dm.createCollection("wsdl");
        } catch (BaseXException ex) {
            logger.log(Level.SEVERE, "Error when creating collection");
        }
    }
    
    @Override
    public void createWSDL(WSDLDoc wsdl) {
        //collection must be created!
        String xml = "<wsdl id='"+wsdl.getId().toString()+"' date='"+wsdl.getDate()
                +"' fileName='"+wsdl.getFileName()+"'>"+wsdl.getDocument()+"</wsdl>";
        try {
            this.dm.addXML("wsdl", wsdl.getId().toString(),xml);
        } catch (BaseXException ex) {
            logger.log(Level.SEVERE, "Error when creating wsdl");
        }
    }

    
    @Override
    public String getWSDL(Long id) throws BaseXException {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        String wsdl = this.dm.queryCollection("declare default element namespace 'http://www.w3.or/2001/XMLSchema'; "
                + " collection('wsdl')/wsdl[@id='"+id.toString()+"']");
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
