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
public interface WarArchiveManager {
    
    public void createWarArchive(WarArchive arch);
    
    public WarArchive getWarArchive(Integer id);  
    
    public List<WarArchive> getAllArchives(); 
    
}
