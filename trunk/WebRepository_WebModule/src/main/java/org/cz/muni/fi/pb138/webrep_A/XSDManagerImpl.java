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
public class XSDManagerImpl implements XSDManager {
    @Override
    public void createXSD(XSD xsd) {
        if (xsd.getId() != null) {
            throw new IllegalArgumentException("xsd id is already set");            
        }
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String getXSD(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        throw new UnsupportedOperationException();
        
    }
    
    @Override
    public String getAllXSDs() {
        throw new UnsupportedOperationException();
    }
    
    
     /*
     * Finds XSD by data input.
     */
    @Override
    public String findXSDByData(String s){
        throw new UnsupportedOperationException();
    }
}
