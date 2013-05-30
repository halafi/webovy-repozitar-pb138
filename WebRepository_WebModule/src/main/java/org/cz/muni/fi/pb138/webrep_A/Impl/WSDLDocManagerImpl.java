package org.cz.muni.fi.pb138.webrep_A.Impl;

import java.io.IOException;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.APIs.WSDLDocManager;
import org.cz.muni.fi.pb138.webrep_A.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;
import org.w3c.dom.Document;
/**
 *
 *
 * @author xmakovic
 */
public class WSDLDocManagerImpl implements WSDLDocManager {
    
    private String wsdlCollection;
    private DatabaseManager dm;

    public WSDLDocManagerImpl(String wsdlCollection, DatabaseManager dm) throws IOException {
        this.dm = dm;
        this.wsdlCollection = wsdlCollection;
    }
    
    @Override
    public void createWSDLCollection() throws BaseXException {
        this.dm.createCollection(this.wsdlCollection);
    }
    
    @Override
    public void createWSDL(WSDLDoc wsdl) throws BaseXException {
        this.dm.addXML(this.wsdlCollection, wsdl.getId().toString(),
                "<wsdl id='"+wsdl.getId().toString()+"' date='"+wsdl.getDate()
                +"' fileName='"+wsdl.getFileName()+"'>"+wsdl.getDocument()+"</wsdl>");
    }

    @Override
    public String getWSDL(Long id) throws BaseXException{
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        String wsdl = this.dm.queryCollection("collection('"+this.wsdlCollection+"')/wsdl[@id='"+id.toString()+"']");
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
        String query = "for $wsdl in (collection('wsdl')/wsdl) "
                + " let $name := $wsdl/definitions/@name"
                + " where $name='"+definitonsName+"'"
                + " return $wsdl";
        return "<WSDLs> " + this.dm.queryCollection(query) + " </WSDLs>";
    }
}
