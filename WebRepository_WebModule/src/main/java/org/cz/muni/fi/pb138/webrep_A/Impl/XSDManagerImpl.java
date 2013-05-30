package org.cz.muni.fi.pb138.webrep_A.Impl;

import java.io.IOException;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.APIs.XSDManager;
import org.cz.muni.fi.pb138.webrep_A.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.XSD;

/**
 *
 * @author xmakovic
 */
public class XSDManagerImpl implements XSDManager {

    private String xsdCollection;
    private DatabaseManager dm;

    public XSDManagerImpl(String xsdCollection, DatabaseManager dm) throws IOException {
        this.dm = dm;
        this.xsdCollection = xsdCollection;
    }

    public void setDatabaseManager(DatabaseManager dm) {
        this.dm = dm;
    }

    @Override
    public void createXSD(XSD xsd, Long id) throws BaseXException {
        this.dm.createCollection(this.xsdCollection);
        this.dm.addXML(this.xsdCollection, id.toString(), xsd.getDocument());
    }

    @Override
    public String getXSD(Long id) throws BaseXException {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        String xsd = this.dm.queryCollection("collection('" + this.xsdCollection + "')/web[@id='" + id.toString() + "']");
        if (xsd.equals("")) {
            throw new BaseXException("Desired web.xml does not exist");
        }
        return xsd;
    }

    @Override
    public String getAllXSDs() {
        throw new UnsupportedOperationException();
    }

    /*
     * Finds XSD by data input.
     */
    @Override
    public String findXSDByData(String s) {
        throw new UnsupportedOperationException();
    }
}
