package org.cz.muni.fi.pb138.webrep_A.Util;

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
import org.basex.query.QueryProcessor;
/**
 *
 * @author xmakovic
 */
public class DatabaseManager {
    private String DBPath;

    public DatabaseManager(Filetype fileType) throws IOException {
        if((Filetype.WSDL.equals(fileType) && !Filetype.XSD.equals(fileType) && !Filetype.WAR.equals(fileType))
                ||(!Filetype.WSDL.equals(fileType) && Filetype.XSD.equals(fileType) && !Filetype.WAR.equals(fileType))
                ||(!Filetype.WSDL.equals(fileType) && !Filetype.XSD.equals(fileType) && Filetype.WAR.equals(fileType))) {
            String path = DatabaseManager.class.getClassLoader().getResource(".").getPath()+"../../BaseXDB/";
            File basexdbDir = new File(path);
            if(!basexdbDir.exists()) {
                basexdbDir.mkdir();
            }
            
            File yourFile = new File(path+fileType+"Database");
            if(yourFile.exists()) {
                System.out.println("found:"+path+fileType+"Database");
                this.DBPath = path+fileType+"Database";
            }
            else {
                System.out.println("mkdir:"+path+fileType+"Database");
                yourFile.mkdir();
                this.DBPath = path+fileType+"Database";
            }
        }
        else {
            throw new IllegalArgumentException(fileType + " is not used baseX database");
        }
    }
    
    public void setDBPath(String path) {
        this.DBPath = path;
    }
    
    /**
    public DatabaseManager(String file)
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
        new Add(name, xml).execute(context);
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
}
