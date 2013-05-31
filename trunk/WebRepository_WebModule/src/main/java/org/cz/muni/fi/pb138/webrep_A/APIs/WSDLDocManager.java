package org.cz.muni.fi.pb138.webrep_A.APIs;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
    public void createWSDL(WSDLDoc wsdl) throws BaseXException;
    
    /**
     * Gets WSDL from database
     * @param id
     * @return WSDL file as String
     * @throws BaseXException 
     */
    public String getWSDL(Long id) throws BaseXException;
    
    /**
     * Gets all WSDLs from database.
     * @return all WSDLs from database as String
     * @throws BaseXException 
     */
    public String getAllWSDLs() throws BaseXException; 
    
    /**
     * Finds WSDLs by meta data.
     * @param definitonsName
     * @return All WSDLs satisfactory search.
     * @throws BaseXException 
     */
    public String findWSDLByData(String definitonsName) throws BaseXException;

    public void createWSDLCollection() throws BaseXException;
}
