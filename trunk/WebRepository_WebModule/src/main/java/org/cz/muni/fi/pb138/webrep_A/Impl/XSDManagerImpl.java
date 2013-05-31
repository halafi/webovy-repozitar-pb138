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

    private DatabaseManager dm;

    public XSDManagerImpl(DatabaseManager dm) throws IOException {
        this.dm = dm;
    }

    @Override
    public void createXSDCollection() throws BaseXException {
        this.dm.createCollection("xsd");
    }

    @Override
    public void createXSD(XSD xsd) throws BaseXException {
        //collection must be created!
        this.dm.addXML("xsd", xsd.getId().toString(),
                "<xsd id='"+xsd.getId().toString()+"' date='"+xsd.getDate()
                +"' fileName='"+xsd.getFileName()+"'>"+xsd.getDocument()+"</xsd>");
    }

    @Override
    public String getXSD(Long id) throws BaseXException {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        String xsd = this.dm.queryCollection("collection('xsd')/xsd[@id='" + id.toString() + "']");
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
    public String findXSDByData(String s) throws BaseXException{
        String query = "for $xsd in collection('xsd')//xsd) "
                + " let $name := $xsd//element/@name"
                + " let $nameElement := " + s
                + " where $name= $nameElement"
                + " return $xsd";
        return "<XSDs> " + this.dm.queryCollection(query) + " </XSDs>";
    }
}
