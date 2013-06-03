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
     * @param id id in database
     */
    public void createWarArchive(WarArchive war);
    
    /**
     * Gets web.XML from database
     * @param id
     * @return Single web.XML file as String
     */
    public WarArchive getWarArchive(Long id);  
    
    /**
     * Gets all web.XMLs from database.
     * @return All web.XMLs from database as String
     */
    public List<WarArchive> getAllArchives();
    
    /*
     * Creates baseX collection for storing web.xmls and their extracts.
     */
    public void createWARCollection();
    
    /*
     * Setter for logger
     * @param FileOutputStream fs
     */
    public void setLogger(FileOutputStream fs);
    
    /*
     * Simple ID generator.
     */
    public Long getNewId();
}
