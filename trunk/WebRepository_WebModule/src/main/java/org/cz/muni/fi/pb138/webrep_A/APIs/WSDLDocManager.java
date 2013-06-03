package org.cz.muni.fi.pb138.webrep_A.APIs;

import java.util.List;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;

/**
 *
 * @author xmakovic
 */
public interface WSDLDocManager {
    
    /**
     * Saves WSDL into database
     * @param wsdl
     */
    public void createWSDL(WSDLDoc wsdl);
    
    /**
     * Gets WSDL from database
     * @param id
     * @return WSDL file as String
     */
    public WSDLDoc getWSDL(Long id);
    
    /**
     * Gets all WSDLs from database.
     * @return all WSDLs from database as String
     */
    public List<WSDLDoc> getAllWSDLs(); 
    
    /**
     * Finds WSDLs by meta data.
     * @param definitonsName
     * @return All WSDLs satisfactory search.
     */
    public List<WSDLDoc> findWSDLByData(String definitonsName);

    /*
     * Simple ID generator.
     */
    public Long getNewId();
    
    /*
     * Creates BaseX collection for storing WSDLs.
     */
    public void createWSDLCollection();
}
