package org.cz.muni.fi.pb138.webrep_A.APIs;

import java.io.FileOutputStream;
import java.util.List;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;

/**
 *
 * @author xmakovic
 */
public interface WSDLDocManager {
    
    /**
     * Saves WSDL into database
     * @param wsdl
     * @throws BaseXException 
     */
    public void createWSDL(WSDLDoc wsdl);
    
    /**
     * Gets WSDL from database
     * @param id
     * @return WSDL file as String
     * @throws BaseXException 
     */
    public WSDLDoc getWSDL(Long id);
    
    /**
     * Gets all WSDLs from database.
     * @return all WSDLs from database as String
     * @throws BaseXException 
     */
    public List<WSDLDoc> getAllWSDLs(); 
    
    /**
     * Finds WSDLs by meta data.
     * @param definitonsName
     * @return All WSDLs satisfactory search.
     * @throws BaseXException 
     */
    public List<WSDLDoc> findWSDLByData(String definitonsName);

    public Long getNewId();
    public void createWSDLCollection();
    public void setLogger(FileOutputStream fs);
}
