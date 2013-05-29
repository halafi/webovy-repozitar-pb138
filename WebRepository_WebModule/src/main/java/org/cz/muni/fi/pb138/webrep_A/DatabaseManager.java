package org.cz.muni.fi.pb138.webrep_A;

import java.io.File;
import java.io.IOException;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.Add;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.Delete;
import org.basex.core.cmd.DropDB;
import org.basex.core.cmd.Open;
import org.basex.core.cmd.Set;
import org.basex.core.cmd.XQuery;
/**
 *
 * @author xmakovic
 */
public class DatabaseManager {
    private String DBPath;
    /*private static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    private static final String OPENING_TAG = "<webxmls>";
    private static final String CLOSING_TAG = "</webxmls>";*/
    //private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DatabaseManager.class);
    
    public DatabaseManager(String fileType) throws IOException {
        if(!"wsdl".equals(fileType) || !"xsd".equals(fileType) || !"web".equals(fileType)) {
            throw new IllegalArgumentException(fileType + "is not used baseX database");
            
        }
        
        File yourFile = new File("src/main/resources/xml/"+fileType+"Datab.xml");
        if(yourFile.exists()) {
            this.DBPath = "src/main/resources/xml/"+fileType+"Datab.xml";
        }
        else {
            yourFile.createNewFile();
            this.DBPath = "src/main/resources/xml/"+fileType+"Datab.xml";
            /*new CreateDB("WSDLDatab", "src/main/resources/xml/wsdlDatab.xml").execute(context);
            new Close.execute(context);*/
        }
    }
    
    public void setDBPath(String path) {
        this.DBPath = path;
    }
    
    /**
    * Adds a xml file to a collection
    * 
    * @param collection collection name
    * @param name name of the xml file (without extension)
    * @param xml well-formed XML string
    * @throws BaseXException on database error
    */
    public void addXML(String collection, String name, String xml) throws BaseXException {
        Context context = new Context();
        new Set("dbpath", this.DBPath).execute(context);
        new Open(collection).execute(context);
        new Add(xml, name+".xml").execute(context);
        context.close();
    }

    /**
    * Removes a xml file from a collection
    * 
    * @param collection collection name
    * @param name name of the xml file (without extension)
    * @throws BaseXException if a database command fails
    */
    public void removeXML(String collection, String name) throws BaseXException {
        Context context = new Context();
        new Set("dbpath", this.DBPath).execute(context);
        new Open(collection).execute(context);
        new Delete(name+".xml").execute(context);
        context.close();
    }
    
    /**
    * Runs a XPath or XQuery query on a collection
    * @param query the query as a String
    * @return String
    * @throws BaseXException on database error
    */
    public String queryCollection(String query) throws BaseXException {
        String toReturn;
        Context context = new Context();
        new Set("dbpath", this.DBPath).execute(context);
        toReturn = new XQuery(query).execute(context).toString();
        context.close();
        return toReturn;
    }
    
    /**
    * Creates a collection
    * @param collection name of the collection
    * @throws BaseXException on database error
    */
    public void createCollection(String collection) throws BaseXException {
        Context context = new Context();
        new Set("dbpath", this.DBPath).execute(context);
        new CreateDB(collection).execute(context);
        context.close();
    }

    /**
    * Removes an existing collection
    * @param collection name of the collection
    * @throws BaseXException on database error
    */
    public void removeCollection(String collection) throws BaseXException {
        Context context = new Context();
        new Set("dbpath", this.DBPath).execute(context);
        //new Open(collection).execute(context);
        new DropDB(collection).execute(context);
        context.close();
    }
    
    /*private void openDB() throws BaseXException
    {
        new Open("src/main/resources/xml/wsdlDatab.xml").execute(context);
    }*/
}
