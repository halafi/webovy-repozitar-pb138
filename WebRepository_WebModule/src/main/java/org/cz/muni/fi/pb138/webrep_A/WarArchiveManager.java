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
    
    /*
     * Stores web.xml into database
     */
    public void createWarArchive(WarArchive arch);
    
    /*
     * Gets web.xml file from database
     */
    public WarArchive getWarArchive(Integer id);  
    
    /*
     * gets all web.xml files from database
     */
    public List<WarArchive> getAllArchives();
    
    /*
     * Finds desired web.xml by data
     */
    public List<WarArchive> findWebXMLbyArtefact(String string);
    
}