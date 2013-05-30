package org.cz.muni.fi.pb138.webrep_A.Impl;

import java.io.IOException;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.APIs.XSDManager;
import org.cz.muni.fi.pb138.webrep_A.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.XSD;

/**
 *
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

    @Override
    public void createXSDCollection() throws BaseXException {
        this.dm.createCollection(this.xsdCollection);
    }

    @Override
    public void createXSD(XSD xsd) throws BaseXException {
        //collection must be created!
        this.dm.addXML(this.xsdCollection, xsd.getId().toString(),
                "<xsd id='"+xsd.getId().toString()+"' date='"+xsd.getDate()
                +"' fileName='"+xsd.getFileName()+"'>"+xsd.getDocument()+"</xsd>");
    }

    @Override
    public String getXSD(Long id) throws BaseXException {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        String xsd = this.dm.queryCollection("collection('" + this.xsdCollection + "')/xsd[@id='" + id.toString() + "']");
        if (xsd.equals("")) {
            throw new BaseXException("Desired xml schema does not exist");
        }
        return xsd;
    }

    @Override
    public String getAllXSDs() throws BaseXException {
        String query = "for $xsd in (collection('xsd')/xsd) "
                + "return $xsd";
        return "<XSDs> " + this.dm.queryCollection(query) + " </XSDs>";
    }

    /*
     * Finds XSD by data input.
     */
    @Override
    public String findXSDByData(String s) {
        //needs to be written, but it wont work
        throw new UnsupportedOperationException();
    }
}
