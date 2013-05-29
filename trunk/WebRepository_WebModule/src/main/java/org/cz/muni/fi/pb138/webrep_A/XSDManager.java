/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A;

import java.util.List;

/**
 *
 * @author xmakovic
 */
public interface XSDManager {
    
    /*
     * Stores XSD into database.
     */
    public void createXSD(XSD xsd);
    
    /*
     * Gets XSD from database.
     */
    public String getXSD(Long id);  
    
    /*
     * Returns all xsds from database.
     */
    public String getAllXSDs(); 
    
    /*
     * Finds XSD by data input.
     */
    public String findXSDByData(String s);
    
}
