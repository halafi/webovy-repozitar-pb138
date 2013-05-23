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
public interface WSDLDocManager {
    
    public void createWSDL(WSDLDoc wsdl);
    
    public WSDLDoc getWSDL(Integer id);  
    
    public List<WSDLDoc> getAllWSDLs(); 
}
