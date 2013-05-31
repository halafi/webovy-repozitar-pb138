/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A.Impl;

import java.io.IOException;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WarArchive;
import org.cz.muni.fi.pb138.webrep_A.APIs.WarManager;
import org.cz.muni.fi.pb138.webrep_A.Util;

/**
 *
 * @author xmakovic
 */
public class WarManagerImpl implements WarManager {
    
    private String warCollection;
    private DatabaseManager dm;

    public WarManagerImpl(String warCollection, DatabaseManager dm) throws IOException {
        this.dm = dm;
        this.warCollection = warCollection;
    }
    
    @Override
    public void createWARCollection() throws BaseXException {
        this.dm.createCollection(this.warCollection);
    }
    
    @Override
    public void createWarArchive(WarArchive war) throws BaseXException {
        //collection must be created!
        this.dm.addXML(this.warCollection, war.getId().toString(),
                "<war id='"+war.getId().toString()+"' date='"+Util.getDate(war.getDate())
                +"' fileName='"+war.getFileName()+"'>"+"<web.xml>"+war.getWebXml()+"</web.xml></war>");
    }
    
    @Override
    public String getWarArchive(Long id)throws BaseXException{
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        String war = this.dm.queryCollection("collection('"+this.warCollection+"')/war[@id='"+id.toString()+"']");
        if (war.equals("")) {
            throw new BaseXException("Desired war does not exist");
        }
        return war;
    }
    
    @Override
    public String getAllArchives() throws BaseXException {
        /*String query = "for $war in collection('war')/war "
                + " let $id := $war/@id"
                + " let $fileName := $war/@fileName"
                + " let $date := $war/@date"
                + " return <war id ='{$id}' date = '{$date}' fileName = '{$fileName}'>"
                + " </war>";*/
        String query = "for $war in collection('war')/war "
                + " return $war";
        return "<WARs> " + this.dm.queryCollection(query) + " </WARs>";
    }
    
    /*
     * Finds desired web.xml by data
     */
    @Override
    public String findWebXMLbyArtefact(String string){
        throw new UnsupportedOperationException();
    }
}
