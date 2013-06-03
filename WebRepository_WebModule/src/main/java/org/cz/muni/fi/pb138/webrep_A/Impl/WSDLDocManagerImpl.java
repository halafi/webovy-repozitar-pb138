package org.cz.muni.fi.pb138.webrep_A.Impl;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.APIs.WSDLDocManager;
import org.cz.muni.fi.pb138.webrep_A.Util.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;
import org.cz.muni.fi.pb138.webrep_A.Parser.WSDLDocParser;
import org.cz.muni.fi.pb138.webrep_A.Util.Util;
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
    
    /*
     * Constructor. 
     * @param DatabaseManager dm
     */
    public WSDLDocManagerImpl(DatabaseManager dm) {
        this.dm = dm;
    }
    
    /*
     * Creates BaseX collection for storing WSDLs.
     */
    @Override
    public void createWSDLCollection() {
        this.dm.createCollection("wsdl");
    }
    
    
    /**
     * Saves WSDL into database
     * @param wsdl
     */
    @Override
    public void createWSDL(WSDLDoc wsdl) {
        //collection must be created!
        String xml = "<wsdl id='"+wsdl.getId().toString()+"' date='"+wsdl.getTimestamp()
                +"' fileName='"+wsdl.getFileName()+"'>"+wsdl.getDocument()+"</wsdl>";
        this.dm.addXML("wsdl", wsdl.getId().toString(),xml);

    }
    
    /*
     * Simple ID generator.
     */
    @Override
    public Long getNewId() {
        String c = this.dm.queryCollection("count(collection('wsdl')/wsdl)");
        if(c.equals("0")) {
            return new Long(0);
        }
        else {
            return new Long(c);
        }
    }

    /**
     * Gets WSDL from database
     * @param id
     * @return WSDL file as String
     */
    @Override
    public WSDLDoc getWSDL(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        WSDLDoc wsdl = new WSDLDoc();
        WSDLDocParser wsdlParser = new WSDLDocParser();
        wsdl.setId(id);
        
        wsdl.setFileName(this.dm.queryCollection("declare namespace def = 'http://schemas.xmlsoap.org/wsdl';"
                +" for $ wsdl in collection('wsdl')/wsdl[@id='" + id.toString() + "']"
                +" return data($wsdl/@fileName)"));
        wsdl.setTimestamp(this.dm.queryCollection("declare namespace def = 'http://schemas.xmlsoap.org/wsdl';"
                +" for $ wsdl in collection('wsdl')/wsdl[@id='" + id.toString() + "']"
                +" return data($wsdl/@date)"));
        wsdl.setDocument(this.dm.queryCollection("declare namespace def = 'http://schemas.xmlsoap.org/wsdl';"
                +" collection('wsdl')/wsdl[@id='"+id.toString()+"']/def:definitions"));
        wsdl.setExtract(Util.docToString(wsdlParser.wsdlExtract(Util.stringToDoc(wsdl.getDocument()))));

        return wsdl;
    }

    /**
     * Gets all WSDLs from database.
     * @return all WSDLs from database.
     */
    @Override
    public List<WSDLDoc> getAllWSDLs() {
        List<WSDLDoc> output = new ArrayList<WSDLDoc>();
        String c = this.dm.queryCollection("count(collection('wsdl')/wsdl)");
        for(int i=0;i<new Integer(c);i++) {
            output.add(this.getWSDL(new Long(i)));
        }
        return output;
    }
   

    /**
     * Finds WSDLs by meta data.
     * @param definitonsName
     * @return All WSDLs which are search query compliant.
     */   
    @Override
    public List<WSDLDoc> findWSDLByData(String definitonsName){
        List<WSDLDoc> output = new ArrayList<WSDLDoc>();
        String query = this.dm.queryCollection(" declare namespace def = 'http://schemas.xmlsoap.org/wsdl';" 
                + " for $wsdl in collection('wsdl')/wsdl "
                + " let $name := $wsdl/def:definitions/@name"
                + " where $name='"+definitonsName+"'"
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
  
    /**
     * Find by meta data
     * @param metaData is name of meta data e.g. message
     * @param atributeName is name of meta Data
     * @return List of WSDLDoc
     * @throws BaseXException 
     */
    public List<WSDLDoc> findWSDLByMetaData(String metaData, String atributeName) {
        List<WSDLDoc> output = new ArrayList<WSDLDoc>();
        String query = this.dm.queryCollection(" declare namespace def = 'http://schemas.xmlsoap.org/wsdl';" 
                + " distinct-values(for $wsdl in collection('wsdl')/wsdl "
                + " for $nodes in $wsdl//*"
                + " for $attr in $nodes/xsd:"+metaData+"/@name"
                + " where fn:contains($attr,'"+atributeName+"')"
                + " return distinct-values($wsdl/@id))");
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
