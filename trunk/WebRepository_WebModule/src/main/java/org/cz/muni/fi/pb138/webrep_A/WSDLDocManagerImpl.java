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
public class WSDLDocManagerImpl {
    
    public void createWSDL(WSDLDoc wsdl) {
        if (wsdl.getId() != null) {
            throw new IllegalArgumentException("wsdl id is already set");            
        }
        /*try {
            
        }
        catch {
            
        }
        finally {
          close db, etc.
        }*/
    }
    
    public WSDLDoc getWSDL(Integer id) {
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
    
    public List<WSDLDoc> getAllWSDLs() {
        
        /*try {
            
        }
        catch {
            
        }
        finally {
          close db, etc.
        }*/
        return null;
    }
}
