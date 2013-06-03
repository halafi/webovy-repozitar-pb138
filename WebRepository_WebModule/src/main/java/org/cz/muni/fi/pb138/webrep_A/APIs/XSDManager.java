package org.cz.muni.fi.pb138.webrep_A.APIs;

import java.io.FileOutputStream;
import java.util.List;
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
    public void createXSD(XSD xsd);
    
    /**
     * Gets XSD from database
     * @param id
     * @return XML file as String
     * @throws BaseXException 
     */
    public XSD getXSD(Long id);
    
    /**
     * Gets all XSDs from database.
     * @return all XSDs from database as String
     * @throws BaseXException 
     */
    public List<XSD> getAllXSDs(); 
    
    /**
     * Finds XSD by meta data.
     * @param definitonsName
     * @return All XSDs satisfactory search.
     * @throws BaseXException 
     */
    public List<XSD> findXSDByData(String s);
    
    /*
     * Creates BaseX collection for storing XSD schemas.
     */
    public void createXSDCollection();
    
    /*
     * Setter for Logger
     * @param FileOutputStream fs
     */
    public void setLogger(FileOutputStream fs);
    
    /*
     * Simple ID generator.
     */
    public Long getNewId();
}
