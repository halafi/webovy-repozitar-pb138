package org.cz.muni.fi.pb138.webrep_A.APIs;

import java.util.List;
import org.cz.muni.fi.pb138.webrep_A.Entities.XSD;

/**
 * Interface with work with XSDs
 * @author xmakovic
 */
public interface XSDManager {
    
    /**
     * Saves XSD into database
     * @param xsd XSD to be created
     */
    public void createXSD(XSD xsd);
    
    /**
     * Gets XSD from database
     * @param id
     * @return XML file as String
     */
    public XSD getXSD(Long id);
    
    /**
     * Gets all XSDs from database.
     * @return all XSDs from database
     */
    public List<XSD> getAllXSDs(); 
    
    /**
     * Finds XSD by element name.
     * @param name of XSD element
     * @return All XSDs satisfactory search.
     */
    public List<XSD> findXSDByElementName(String s);
    
    /**
     * Creates BaseX collection for storing XSD schemas.
     */
    public void createXSDCollection();
    
    /**
     * Simple ID generator.
     * @return new unique id
     */
    public Long getNewId();
}
