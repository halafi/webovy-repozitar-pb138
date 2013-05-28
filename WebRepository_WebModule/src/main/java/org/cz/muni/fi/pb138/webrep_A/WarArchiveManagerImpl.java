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
        /*try {
            
        }
        catch {
            
        }
        finally {
          close db, etc.
        }*/
    }
    
    @Override
    public WarArchive getWarArchive(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        /*try {
            
        }
        catch {
            
        }
        finally {
          close db, etc.
        }*/
        return null;
    }
    
    @Override
    public List<WarArchive> getAllArchives() {
        
        /*try {
            
        }
        catch {
            
        }
        finally {
          close db, etc.
        }*/
        return null;
    }
    
    /*
     * Finds desired web.xml by data
     */
    public List<WarArchive> findWebXMLbyArtefact(String string){
        return null;
    }
}
