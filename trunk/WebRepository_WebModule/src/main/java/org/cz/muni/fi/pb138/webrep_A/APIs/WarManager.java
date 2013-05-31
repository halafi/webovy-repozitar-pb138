package org.cz.muni.fi.pb138.webrep_A.APIs;

import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.Entities.WarArchive;

/**
 *
 * @author xmakovic
 */
public interface WarManager {
    
    /**
     * Saves web.XML into database
     * @param war is war archive
     * @param id id in database
     * @throws BaseXException 
     */
    public void createWarArchive(WarArchive war) throws BaseXException;
    
    /**
     * Gets web.XML from database
     * @param id
     * @return Single web.XML file as String
     * @throws BaseXException 
     */
    public String getWarArchive(Long id)throws BaseXException;  
    
    /**
     * Gets all web.XMLs from database.
     * @return All web.XMLs from database as String
     * @throws BaseXException 
     */
    public String getAllArchives() throws BaseXException;
    
    public void createWARCollection() throws BaseXException;
}
