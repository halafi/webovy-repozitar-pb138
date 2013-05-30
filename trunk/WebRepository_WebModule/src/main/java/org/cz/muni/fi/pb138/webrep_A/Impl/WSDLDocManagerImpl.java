package org.cz.muni.fi.pb138.webrep_A.Impl;

import java.io.IOException;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;
import org.cz.muni.fi.pb138.webrep_A.APIs.WSDLDocManager;
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

    public void setDatabaseManager(DatabaseManager dm) {
        this.dm = dm;
    }
    
    @Override
    public void createWSDL(WSDLDoc wsdl, Long id) throws BaseXException {
        //this.dm.createCollection(this.wsdlCollection);
        this.dm.addXML(this.wsdlCollection, id.toString() , wsdl.getDocument());
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
        String query = "for $wsdl in distinct-values(collection('wsdl-list')/wsdl) "
                + "let $id := /wsdl/id"
                + "let $name := /wsdl/definions[@name]"
                + "let $version := /wsdl[@version]"
                + "order by $wsdl"
                + "return <wsdl><id>{$id}</id><name>{$name}</name><version>{$version}</version></wsdl>";
        return "<WSDLs>" + this.dm.queryCollection(query) + "</WSDLs>";
    }

    /*
     * Finds WSDL by metadata.
     */
    @Override
    public String findWSDLByData(Document extract) throws BaseXException {
        throw new UnsupportedOperationException();
    }
}
