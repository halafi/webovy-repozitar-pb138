package org.cz.muni.fi.pb138.webrep_A;

import java.io.File;
import java.util.List;
import org.basex.core.BaseXException;
import org.basex.core.cmd.Close;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.Open;
import org.basex.core.cmd.XQuery;
import org.basex.query.expr.Context;
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
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(WSDLDocManagerImpl.class);
    
    public WSDLDocManagerImpl()
    {
        dbCtx = new Context() {};
        try
        {
            File yourFile = new File("src/main/resources/xml/wsdlDatab.xml");
            if(!yourFile.exists()) {
            new CreateDB("WSDLDatab", "src/main/resources/xml/wsdlDatab.xml").execute(dbCtx);
	    new Close.execute(dbCtx);
            }
           
        }
        catch (BaseXException ex)
        {
            logger.log(ex);
       } 
    }
     
     /*private void openDB() throws BaseXException
    {
        new Open("src/main/resources/xml/wsdlDatab.xml").execute(dbCtx);
    }
     */
     
     
     
    @Override
    public void createWSDL(WSDLDoc wsdl){
        if (wsdl.getId() != null) {
            throw new IllegalArgumentException("wsdl id is already set");           
        }
            String insertQuery = "insert node {0} into {1}";
        try
        {
		// test query for input
	    new Open(wsdlDatab.xml).execute(dbCtx);
	    new XQuery(
        "declare namespace xhtml='http://www.w3.org/1999/xhtml';" +
        "insert node " +
        " <xhtml:p>I will match the following query because I contain" +
        " the terms 'ARTICLE' and 'EDITABLE'. :-)</xhtml:p> " +
        "into //xhtml:body"
         ).execute(context);

            //new XQuery(MessageFormat.format(insertQuery, wsdl.getDocument(), "/wsdl")).execute(dbCtx);
        }
        catch (BaseXException ex)
        {
           logger.log(ex);
        } finally {
	
	new Close(wsdlDatab.xml).execute(dbCtx);
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