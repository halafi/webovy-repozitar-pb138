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
                "<war id='"+war.getId().toString()+"' date='"+war.getDate()
                +"' fileName='"+war.getFileName()+"'>"+"<web>"+war.getWebXml()+"</web></war>");
    }
    
    @Override
    public String getWarArchive(Long id)throws BaseXException{
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        String war = this.dm.queryCollection("collection('"+this.warCollection+"')/web[@id='"+id.toString()+"']");
        if (war.equals("")) {
            throw new BaseXException("Desired web.xml does not exist");
        }
        return war;
    }
    
    @Override
    public String getAllArchives() {
        throw new UnsupportedOperationException();
    }
    
    /*
     * Finds desired web.xml by data
     */
    @Override
    public String findWebXMLbyArtefact(String string){
        throw new UnsupportedOperationException();
    }
}
