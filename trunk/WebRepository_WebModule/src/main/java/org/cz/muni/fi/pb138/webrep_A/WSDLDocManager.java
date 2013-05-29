/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A;

import java.util.List;
import org.basex.core.BaseXException;
import org.w3c.dom.Document;

/**
 *
 * @author xmakovic
 */
public interface WSDLDocManager {
    
    /*
     * Saves WSDL into database
     */
    public void createWSDL(WSDLDoc wsdl) throws Exception;
    
    /*
     * Gets WSDL from database
     */
    public String getWSDL(String definitionsName) throws BaseXException;
    
    /*Gets all WSDLs from database.
     * 
     */
    public String getAllWSDLs() throws BaseXException; 
    
    /*
     * Finds WSDL by metadata.
     */
    public List<WSDLDoc> findWSDLByData(Document extract);
    
       
    
}
