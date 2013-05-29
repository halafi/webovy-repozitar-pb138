package org.cz.muni.fi.pb138.webrep_A;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.Add;
import org.basex.core.cmd.AlterDB;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.Delete;
import org.basex.core.cmd.DropDB;
import org.basex.core.cmd.Open;
import org.basex.core.cmd.Set;
import org.basex.core.cmd.XQuery;
import org.w3c.dom.Document;
/**
 *
 * @author xmakovic
 */
public class WSDLDocManagerImpl implements WSDLDocManager {
    private String basexCollection;
    private DatabaseManager dm;
    
    public WSDLDocManagerImpl(String basexCollection) throws IOException {
        this.dm = new DatabaseManager("wsdl");
        this.basexCollection = basexCollection;
    }
    
    public void setDatabaseManager (DatabaseManager dm) {
        this.dm = dm;
    }

    @Override
    public String getWSDL(String definitionsName) throws BaseXException{
        if (definitionsName == null) {
            throw new IllegalArgumentException("definitions name is null");
        }
        String wsdl = this.dm.queryCollection("collection('"+this.basexCollection+"')/definitions[@name='"+definitionsName+"']");
        if (wsdl.equals("")) {
            throw new BaseXException("WSDL does not exist");
        }
        return wsdl;
    }
     
    @Override
    public String getAllWSDLs() throws BaseXException{
        String query = "for $league in distinct-values(collection('league-list')//name) "
                + "let $seasons:= for $doc in collection('league-list') where data($doc//name)=$league return <season id=\"{data($doc//league/@id)}\">{data($doc//season)}</season> "
                + "order by $league "
                + "return <league><name>{$league}</name>{$seasons}</league>";
        return "<WSDLs>"+this.dm.queryCollection(query)+"</WSDLs>";
    }

    /*
     * Finds WSDL by metadata.
     */
    @Override
    public String findWSDLByData(Document extract) throws BaseXException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void createWSDL(WSDLDoc wsdl) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}