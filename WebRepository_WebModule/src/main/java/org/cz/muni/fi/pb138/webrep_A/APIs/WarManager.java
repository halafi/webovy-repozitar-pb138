package org.cz.muni.fi.pb138.webrep_A.APIs;

import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.Entities.WarArchive;

/**
 *
 * @author xmakovic
 */
public interface WarManager {
    
    /*
     * Stores web.xml into database
     */
    public void createWarArchive(WarArchive war) throws BaseXException;
    
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
    public void createWARCollection() throws BaseXException;
}
