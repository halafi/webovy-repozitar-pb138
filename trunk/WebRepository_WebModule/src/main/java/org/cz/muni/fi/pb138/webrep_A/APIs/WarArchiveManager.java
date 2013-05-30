/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A.APIs;

import java.util.List;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.Entities.WarArchive;

/**
 *
 * @author xmakovic
 */
public interface WarArchiveManager {
    
    /*
     * Stores web.xml into database
     */
    public void createWarArchive(WarArchive war, Long id) throws BaseXException;
    
    /*
     * Gets web.xml file from database
     */
    public String getWarArchive(Long id)throws BaseXException;  
    
    /*
     * gets all web.xml files from database
     */
    public String getAllArchives();
    
    /*
     * Finds desired web.xml by data
     */
    public String findWebXMLbyArtefact(String string);
    
}
