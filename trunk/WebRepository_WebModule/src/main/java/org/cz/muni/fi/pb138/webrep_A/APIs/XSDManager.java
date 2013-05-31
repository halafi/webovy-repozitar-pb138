package org.cz.muni.fi.pb138.webrep_A.APIs;

import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.Entities.XSD;

/**
 *
 * @author xmakovic
 */
public interface XSDManager {
    
    /**
     * Saves XSD into database
     * @param xsd
     * @throws BaseXException 
     */
    public void createXSD(XSD xsd) throws BaseXException;
    
    /**
     * Gets XSD from database
     * @param id
     * @return XML file as String
     * @throws BaseXException 
     */
    public String getXSD(Long id) throws BaseXException;  
    
    /**
     * Gets all XSDs from database.
     * @return all XSDs from database as String
     * @throws BaseXException 
     */
    public String getAllXSDs() throws BaseXException; 
    
    /**
     * Finds XSD by meta data.
     * @param definitonsName
     * @return All XSDs satisfactory search.
     * @throws BaseXException 
     */
    public String findXSDByData(String s) ;
    public void createXSDCollection() throws BaseXException;
}
