package org.cz.muni.fi.pb138.webrep_A.Impl;

import java.util.ArrayList;
import java.util.List;
import org.cz.muni.fi.pb138.webrep_A.APIs.XSDManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.XSD;
import org.cz.muni.fi.pb138.webrep_A.Parser.XSDParser;
import org.cz.muni.fi.pb138.webrep_A.Util.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Util.Util;

/**
 *
 * 
 * @author xmakovic
 */
public class XSDManagerImpl implements XSDManager {
    private DatabaseManager dm;

    public XSDManagerImpl(DatabaseManager dm) {
        this.dm = dm;
    }
    
    @Override
    public void createXSDCollection() {
        this.dm.createCollection("xsd");
    }
    
    @Override
    public Long getNewId() {
        String c = this.dm.queryCollection("count(collection('xsd')/xsd)");
        if(c.equals("0")) {
            return new Long(0);
        }
        else {
            return new Long(c);
        }
    }

    @Override
    public void createXSD(XSD xsd) {
        //collection must be created!
        String xml = "<xsd id='"+xsd.getId().toString()+"' date='"+xsd.getTimestamp()
                +"' fileName='"+xsd.getFileName()+"'>"+xsd.getDocument()+"</xsd>";
        this.dm.addXML("xsd", xsd.getId().toString(),xml);
    }

    @Override
    public XSD getXSD(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        XSD schema = new XSD();
        XSDParser xsdParser = new XSDParser();
        schema.setId(id);
        schema.setDocument(Util.format(this.dm.queryCollection("declare namespace xsd = 'http://www.w3.org/2001/XMLSchema';"
                + "collection('xsd')/xsd[@id='" + id.toString() + "']/xsd:schema")));
        schema.setTimestamp(this.dm.queryCollection("declare namespace xsd = 'http://www.w3.org/2001/XMLSchema';"
                + " for $xsd in collection('xsd')/xsd[@id='" + id.toString() + "']"
                + " return data($xsd/@date)"));
        schema.setFileName(this.dm.queryCollection("declare namespace xsd = 'http://www.w3.org/2001/XMLSchema';"
                + " for $xsd in collection('xsd')/xsd[@id='" + id.toString() + "']"
                + " return data($xsd/@fileName)"));
        schema.setExtract(Util.format(Util.docToString(xsdParser.xsdExtract(Util.stringToDoc(schema.getDocument())))));

        return schema;
    }

    @Override
    public List<XSD> getAllXSDs() {
        List<XSD> output = new ArrayList<XSD>();
        String c = this.dm.queryCollection("count(collection('xsd')/xsd)");
        for(int i=0;i<new Integer(c);i++) {
            output.add(this.getXSD(new Long(i)));
        }
        return output;
    }

    
    @Override
    public List<XSD> findXSDByElementName(String s) {
        List<XSD> output = new ArrayList<XSD>();
        String query =  this.dm.queryCollection("declare namespace xsd = 'http://www.w3.org/2001/XMLSchema';"
                + "distinct-values( for $xsd in collection('xsd')/xsd "
                + " for $nodes in $xsd//*"
                + " for $attr in $nodes/xsd:element/@name"
                + " where fn:contains($attr,'"+s+"')"
                + " return distinct-values($xsd/@id))");
        if(query.equals("")) {
            return output;
        }
        String strarray[] = query.split(" ");
        int intarray[] = new int[strarray.length];
        for (int i=0; i < intarray.length; i++) {
            intarray[i] = Integer.parseInt(strarray[i]);
        }
        for (int x : intarray) {
            output.add(this.getXSD(new Long(x)));
        }
        return output;
    }
}
