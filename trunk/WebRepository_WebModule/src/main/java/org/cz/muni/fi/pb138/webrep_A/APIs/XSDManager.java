package org.cz.muni.fi.pb138.webrep_A.APIs;

import java.io.IOException;
import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.Entities.XSD;
import org.xml.sax.SAXException;

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
    public String getXSD(Long id) throws TransformerConfigurationException, 
                                                             TransformerConfigurationException, 
                                                             TransformerException, 
                                                             SAXException, 
                                                             ParserConfigurationException, 
                                                             IOException,
                                                             BaseXException,
                                                            ParseException;
    
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
    public String findXSDByData(String s) throws BaseXException;
    public void createXSDCollection() throws BaseXException;
}
