/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A.Impl;

import java.io.IOException;
import java.util.List;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WarArchive;
import org.cz.muni.fi.pb138.webrep_A.APIs.WarManager;

/**
 *
 * @author xmakovic
 */
public class WarManagerImpl implements WarManager {
    
    private String webCollection;
    private DatabaseManager dm;

    public WarManagerImpl(String warCollection, DatabaseManager dm) throws IOException {
        this.dm = dm;
        this.webCollection = warCollection;
    }

    public void setDatabaseManager(DatabaseManager dm) {
        this.dm = dm;
    }
    
    @Override
    public void createWarArchive(WarArchive war, Long id) throws BaseXException {
        //collection must be created!
        this.dm.addXML(this.webCollection, id.toString() , war.getDocument());
    }
    
    @Override
    public String getWarArchive(Long id)throws BaseXException{
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        String war = this.dm.queryCollection("collection('"+this.webCollection+"')/web[@id='"+id.toString()+"']");
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
    public String findWebXMLbyArtefact(String string){
        throw new UnsupportedOperationException();
    }
}
