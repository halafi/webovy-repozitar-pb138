/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.Open;
import org.basex.core.cmd.XQuery;
import org.w3c.dom.Document;
/**
 *
 * @author xmakovic
 */
public class WSDLDocManagerImpl implements WSDLDocManager {
    
    private Context dbCtx;
    private static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    private static final String OPENING_TAG = "<webxmls>";
    private static final String CLOSING_TAG = "</webxmls>";
    
    public WSDLDocManagerImpl() throws Exception
    {
        dbCtx = new Context();
        try 
        {
            File yourFile = new File("src/main/resources/xml/wsdlDatab.xml");
            if(!yourFile.exists()) {
            new CreateDB("WSDLDatab", "src/main/resources/xml/wsdlDatab.xml").execute(dbCtx);
            }
            openDB();
        } 
        catch (BaseXException ex) 
        {
            throw new Exception("Database could not be opened or created", ex);
       }
    }
    
     private void openDB() throws BaseXException
    {
        new Open("src/main/resources/xml/wsdlDatab.xml").execute(dbCtx);
    }
    
    
    
    
    @Override
    public void createWSDL(WSDLDoc wsdl) throws Exception {
        if (wsdl.getId() != null) {
            throw new IllegalArgumentException("wsdl id is already set");            
        }
            String insertQuery = "insert node {0} into {1}";
        try
        {
            new XQuery(MessageFormat.format(insertQuery, wsdl.getDocument(), "/wsdl")).execute(dbCtx);
        }
        catch (BaseXException ex)
        {
           throw new Exception("error while inserting data into database", ex);
        }
        
    }
    
    @Override
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
    
    @Override
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
    
    /*
     * Finds WSDL by metadata.
     */
    @Override
    public List<WSDLDoc> findWSDLByData(Document extract){
        
        return null;
    }
}
