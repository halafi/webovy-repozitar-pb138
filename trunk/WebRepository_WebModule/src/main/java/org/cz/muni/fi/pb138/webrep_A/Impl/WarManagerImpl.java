package org.cz.muni.fi.pb138.webrep_A.Impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.APIs.WarManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WarArchive;
import org.cz.muni.fi.pb138.webrep_A.Util.DatabaseManager;
/**
 *
 * @author xmakovic
 */
public class WarManagerImpl implements WarManager {
    public static final Logger logger = Logger.getLogger(WarManagerImpl.class.getName());
    private DatabaseManager dm;

    public WarManagerImpl(DatabaseManager dm) throws IOException {
        this.dm = dm;
    }
    
    @Override
    public void setLogger(FileOutputStream fs) {
        logger.addHandler(new StreamHandler(fs, new SimpleFormatter()));
    }
    
    @Override
    public void createWARCollection() {
        this.dm.createCollection("war");
    }
    
    @Override
    public void createWarArchive(WarArchive war) {
        //collection must be created!
        String xml = "<war id='"+war.getId().toString()+"' date='"+war.getDate()
                +"' fileName='"+war.getFileName()+"'>"+"<web.xml>"+war.getWebXml()+"</web.xml></war>";
        this.dm.addXML("war", war.getId().toString(),xml);

    }
    
    @Override
    public String getWarArchive(Long id)throws BaseXException{
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        String war = this.dm.queryCollection("collection('war')/war[@id='"+id.toString()+"']");
        if (war.equals("")) {
            throw new BaseXException("Desired war does not exist");
        }
        return war;
    }
    
    @Override
    public String getAllArchives() throws BaseXException {
        /*String query = "for $war in collection('war')/war "
                + " let $id := $war/@id"
                + " let $fileName := $war/@fileName"
                + " let $date := $war/@date"
                + " return <war id ='{$id}' date = '{$date}' fileName = '{$fileName}'>"
                + " </war>";*/
        String query = "for $war in collection('war')/war "
                + " return $war";
        return "<WARs> " + this.dm.queryCollection(query) + " </WARs>";
    }
}
