package org.cz.muni.fi.pb138.webrep_A.APIs;

import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;
import org.w3c.dom.Document;

/**
 *
 * @author xmakovic
 */
public interface WSDLDocManager {
    
    /*
     * Saves WSDL into database
     */
    public void createWSDL(WSDLDoc wsdl) throws BaseXException;
    
    /*
     * Gets WSDL from database
     */
    public String getWSDL(Long id) throws BaseXException;
    
    /*Gets all WSDLs from database.
     * 
     */
    public String getAllWSDLs() throws BaseXException; 
    
    /*
     * Finds WSDL by metadata.
     */
    public String findWSDLByData(String definitonsName) throws BaseXException;

    public void createWSDLCollection() throws BaseXException;
}