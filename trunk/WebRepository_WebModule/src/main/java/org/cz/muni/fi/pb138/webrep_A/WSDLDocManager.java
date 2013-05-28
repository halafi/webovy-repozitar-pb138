/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A;

import java.util.List;
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
    public WSDLDoc getWSDL(Integer id);
    
    /*Gets all WSDLs from database.
     * 
     */
    public List<WSDLDoc> getAllWSDLs(); 
    
    /*
     * Finds WSDL by metadata.
     */
    public List<WSDLDoc> findWSDLByData(Document extract);
    
       
    
}
