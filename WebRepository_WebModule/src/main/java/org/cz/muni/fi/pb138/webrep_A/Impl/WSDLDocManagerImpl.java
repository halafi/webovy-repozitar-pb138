package org.cz.muni.fi.pb138.webrep_A.Impl;

import java.util.ArrayList;
import java.util.List;
import org.cz.muni.fi.pb138.webrep_A.APIs.WSDLDocManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;
import org.cz.muni.fi.pb138.webrep_A.Parser.WSDLDocParser;
import org.cz.muni.fi.pb138.webrep_A.Util.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Util.Util;
/**
 *
 *
 * @author xmakovic
 */
public class WSDLDocManagerImpl implements WSDLDocManager {
    private DatabaseManager dm;

    /*
     * Constructor. 
     * @param DatabaseManager dm
     */
    public WSDLDocManagerImpl(DatabaseManager dm) {
        this.dm = dm;
    }
   
    @Override
    public void createWSDLCollection() {
        this.dm.createCollection("wsdl");
    }
    
    @Override
    public void createWSDL(WSDLDoc wsdl) {
        String xml = "<wsdl id='"+wsdl.getId().toString()+"' date='"+wsdl.getTimestamp()
                +"' fileName='"+wsdl.getFileName()+"'>"+wsdl.getDocument()+"</wsdl>";
        this.dm.addXML("wsdl", wsdl.getId().toString(),xml);
    }
    
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

    @Override
    public WSDLDoc getWSDL(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        WSDLDoc wsdl = new WSDLDoc();
        WSDLDocParser wsdlParser = new WSDLDocParser();
        wsdl.setId(id);
        wsdl.setDocument(this.dm.queryCollection("declare namespace def = 'http://schemas.xmlsoap.org/wsdl/';"
                +" collection('wsdl')/wsdl[@id='"+id.toString()+"']/def:definitions"));
        if(wsdl.getDocument().equals("")) {
            wsdl.setDocument(this.dm.queryCollection("declare namespace def = 'http://schemas.xmlsoap.org/wsdl/';"
                +" collection('wsdl')/wsdl[@id='"+id.toString()+"']/def:definitions"));
        }
        wsdl.setFileName(this.dm.queryCollection("declare namespace def = 'http://schemas.xmlsoap.org/wsdl/';"
                +" for $ wsdl in collection('wsdl')/wsdl[@id='" + id.toString() + "']"
                +" return data($wsdl/@fileName)"));
        wsdl.setTimestamp(this.dm.queryCollection("declare namespace def = 'http://schemas.xmlsoap.org/wsdl/';"
                +" for $ wsdl in collection('wsdl')/wsdl[@id='" + id.toString() + "']"
                +" return data($wsdl/@date)"));
        wsdl.setExtract(Util.format(Util.docToString(wsdlParser.wsdlExtract(Util.stringToDoc(wsdl.getDocument())))));

        return wsdl;
    }

    @Override
    public List<WSDLDoc> getAllWSDLs() {
        List<WSDLDoc> output = new ArrayList<WSDLDoc>();
        String c = this.dm.queryCollection("count(collection('wsdl')/wsdl)");
        for(int i=0;i<new Integer(c);i++) {
            output.add(this.getWSDL(new Long(i)));
        }
        return output;
    }
   
    @Override
    public List<WSDLDoc> findWSDLByData(String definitonsName){
        List<WSDLDoc> output = new ArrayList<WSDLDoc>();
        String query = this.dm.queryCollection(" declare namespace def = 'http://schemas.xmlsoap.org/wsdl/';" 
                + " for $wsdl in collection('wsdl')//* "
                + " let $name := $wsdl/def:definitions/@name"
                + " where fn:contains($name,'"+definitonsName+"')"
                + " return distinct-values($wsdl/@id)");
        if(query.equals("")) {
            return output;
        }
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
    
    /*
     * Not working for some reason.
     * 
     */
     
//    @Override
//    public List<WSDLDoc> findWSDLByMetaData(String messageName) {
//        List<WSDLDoc> output = new ArrayList<WSDLDoc>();
//        String query = this.dm.queryCollection(" declare namespace def = 'http://schemas.xmlsoap.org/wsdl/';" 
//                + " for $wsdl in collection('wsdl')//* "
//                + " let $msg := $wsdl/def:message/@name"
//                + " where fn:contains($msg,'"+messageName+"')"
//                + " return distinct-values($wsdl/@id)");
//        if(query.equals("")) {
//            return output;
//        }
//        String strarray[] = query.split(" ");
//        int intarray[] = new int[strarray.length];
//        for (int i=0; i < intarray.length; i++) {
//            intarray[i] = Integer.parseInt(strarray[i]);
//        }
//        for (int x : intarray) {
//            output.add(this.getWSDL(new Long(x)));
//        }
//        return output;
//    }
    
}
