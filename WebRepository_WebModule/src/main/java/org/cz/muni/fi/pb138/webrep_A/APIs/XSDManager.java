/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A.APIs;

import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.Entities.XSD;

/**
 *
 * @author xmakovic
 */
public interface XSDManager {
    
    /*
     * Stores XSD into database.
     */
    public void createXSD(XSD xsd, Long id) throws BaseXException;
    
    /*
     * Gets XSD from database.
     */
    public String getXSD(Long id) throws BaseXException;  
    
    /*
     * Returns all xsds from database.
     */
    public String getAllXSDs(); 
    
    /*
     * Finds XSD by data input.
     */
    public String findXSDByData(String s);
    
}