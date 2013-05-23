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
    
    public void createXSD(XSD xsd);
    
    public XSD getXSD(Integer id);  
    
    public List<XSD> getAllXSDs(); 
}
