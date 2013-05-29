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
    public String getWSDL(Long id) throws BaseXException{
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        String wsdl = this.dm.queryCollection("collection('"+this.wsdlCollection+"')/wsdl[@id='"+id+"']");
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

    @Override
    public void createWSDL(WSDLDoc wsdl) throws BaseXException {
        this.dm.createCollection(this.wsdlCollection);
        this.dm.addXML("wsdl-list", this.wsdlCollection, wsdl.getDocument());
    }
}
