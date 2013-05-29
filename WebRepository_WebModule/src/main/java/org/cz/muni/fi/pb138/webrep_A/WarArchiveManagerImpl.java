/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A;

import java.util.List;

/**
 *
 * @author xmakovic
 */
public class WarArchiveManagerImpl implements WarArchiveManager {
    @Override
    public void createWarArchive(WarArchive war) {
        if (war.getId() != null) {
            throw new IllegalArgumentException("war id is already set");            
        }
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String getWarArchive(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        throw new UnsupportedOperationException();
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
