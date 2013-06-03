package org.cz.muni.fi.pb138.webrep_A.Impl;

import java.util.ArrayList;
import java.util.List;
import org.cz.muni.fi.pb138.webrep_A.APIs.WarManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WarArchive;
import org.cz.muni.fi.pb138.webrep_A.Parser.WebXMLParser;
import org.cz.muni.fi.pb138.webrep_A.Util.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Util.Util;
/**
 *
 * @author xmakovic
 */
public class WarManagerImpl implements WarManager {
    private DatabaseManager dm;
    
    /*
     * Constructor.
     * @param DatabaseManager dm
     */
    public WarManagerImpl(DatabaseManager dm) {
        this.dm = dm;
    }
    
    @Override
    public void createWARCollection() {
        this.dm.createCollection("war");
    }
    
    @Override
    public void createWarArchive(WarArchive war) {
        String xml = "<war id='"+war.getId().toString()+"' date='"+war.getTimestamp()
                +"' fileName='"+war.getFileName()+"'>"+"<web.xml>"+war.getWebXml()+"</web.xml></war>";
        this.dm.addXML("war", war.getId().toString(),xml);

    }
    
    @Override
    public Long getNewId() {
        String c = this.dm.queryCollection("count(collection('war')/war)");
        if(c.equals("0")) {
            return new Long(0);
        }
        else {
            return new Long(c);
        }
    }
    
    @Override
    public WarArchive getWarArchive(Long id){
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        WarArchive war = new WarArchive();
        WebXMLParser webXmlParser = new WebXMLParser();
        war.setId(id);
        war.setTimestamp(this.dm.queryCollection(" "
                + " for $war in collection('war')/war[@id='" + id.toString() + "']"
                + " return data($war/@date)"));
        war.setFileName(this.dm.queryCollection(" "
                + " for $war in collection('war')/war[@id='" + id.toString() + "']"
                + " return data($war/@fileName)"));
        war.setWebXml(Util.format(this.dm.queryCollection("collection('war')/war[@id='"+id.toString()+"']/web.xml")));
        war.setExtract(Util.format(Util.docToString(webXmlParser.webXMLExtract(Util.stringToDoc(war.getWebXml())))));
        return war;
    }
    
    @Override
    public List<WarArchive> getAllArchives() {
        List<WarArchive> output = new ArrayList<WarArchive>();
        String c = this.dm.queryCollection("count(collection('war')/war)");
         for(int i=0;i<new Integer(c);i++) { 
             output.add(this.getWarArchive(new Long(i)));
         }
        return output;
    }
     
    @Override
    public List<WarArchive> findWarByData(String atrName) {
        List<WarArchive> output = new ArrayList<WarArchive>();
        String query = this.dm.queryCollection(" declare namespace def = 'http://java.sun.com/xml/ns/javaee';" 
                + " distinct-values(for $war in collection('war')/war "
                + " for $nodes in $war//*"
                + " let $attr1 := $nodes/def:filter-name"
                + " where fn:contains($attr1,'"+atrName+"')"
                + " return distinct-values($war/@id))");
        if(query.equals("")) {
            return output;
        }
        String strarray[] = query.split(" ");
        int intarray[] = new int[strarray.length];
        for (int i=0; i < intarray.length; i++) {
            intarray[i] = Integer.parseInt(strarray[i]);
        }
        for (int x : intarray) {
            output.add(this.getWarArchive(new Long(x)));
        }
        return output;
    }
}
