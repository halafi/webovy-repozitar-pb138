package org.cz.muni.fi.pb138.webrep_A.APIs;

import java.io.FileOutputStream;
import java.util.List;
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
     */
    public void createWarArchive(WarArchive war);
    
    /**
     * Gets web.XML from database
     * @param id entity id
     * @return single WarArchive
     */
    public WarArchive getWarArchive(Long id);  
    
    /**
     * Gets all Wars from database.
     * @return All Wars from database as String
     */
    public List<WarArchive> getAllArchives();
    
    /*
     * Creates baseX collection for storing Wars.
     */
    public void createWARCollection();
    
    /**
     * Finds War archives by meta data
     * @param filterName is name of filter
     * @return List of WSDLDoc
     */
    public List<WarArchive> findWarByData(String atrName);
       
    /*
     * Simple ID generator.
     * @return new id
     */
    public Long getNewId();
}
