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
        /*try {
            
        }
        catch {
            
        }
        finally {
          close db, etc.
        }*/
    }
    
    @Override
    public XSD getXSD(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        /*try {
            
        }
        catch {
            
        }
        finally {
          close db, etc.
        }*/
        return null;
    }
    
    @Override
    public List<XSD> getAllXSDs() {
        /*try {
            
        }
        catch {
            
        }
        finally {
          close db, etc.
        }*/
        return null;
    }
    
    
     /*
     * Finds XSD by data input.
     */
    @Override
    public List<XSD> findXSDByData(String s){
        return null;
    }
}
