/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A;

import java.io.IOException;
import java.util.List;
import org.basex.core.BaseXException;

/**
 *
 * @author xmakovic
 */
public class WarArchiveManagerImpl implements WarArchiveManager {
    
    private String warCollection;
    private DatabaseManager dm;

    public WarArchiveManagerImpl(String warCollection, DatabaseManager dm) throws IOException {
        this.dm = dm;
        this.warCollection = warCollection;
    }

    public void setDatabaseManager(DatabaseManager dm) {
        this.dm = dm;
    }
    
    
    
    @Override
    public void createWarArchive(WarArchive war, Long id) throws BaseXException {
        this.dm.addXML(this.warCollection, id.toString() , war.getDocument());
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
    public String findWebXMLbyArtefact(String string){
        throw new UnsupportedOperationException();
    }
}
