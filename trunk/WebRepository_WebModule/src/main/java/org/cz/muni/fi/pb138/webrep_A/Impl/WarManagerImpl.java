package org.cz.muni.fi.pb138.webrep_A.Impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.basex.core.BaseXException;
import org.cz.muni.fi.pb138.webrep_A.APIs.WarManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;
import org.cz.muni.fi.pb138.webrep_A.Entities.WarArchive;
import org.cz.muni.fi.pb138.webrep_A.Entities.XSD;
import org.cz.muni.fi.pb138.webrep_A.Parser.WebXMLParser;
import org.cz.muni.fi.pb138.webrep_A.Util.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Util.Util;
import org.xml.sax.SAXException;
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
        String xml = "<war id='"+war.getId().toString()+"' date='"+war.getTimestamp()
                +"' fileName='"+war.getFileName()+"'>"+"<web.xml>"+war.getWebXml()+"</web.xml></war>";
        this.dm.addXML("war", war.getId().toString(),xml);

    }
    
    @Override
    public WarArchive getWarArchive(Long id)throws BaseXException{
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        WarArchive war = new WarArchive();
        WebXMLParser webXmlParser = new WebXMLParser();
        war.setId(id);
        war.setTimestamp(this.dm.queryCollection(" "
                + " for $war in collection('war')/war[@id='" + id.toString() + "']"
                + " return data($war/@date)"));
        war.setFileName(this.dm.queryCollection(" "
                + " for $war in collection('war')/war[@id='" + id.toString() + "']"
                + " return data($war/@fileName)"));
        war.setWebXml(this.dm.queryCollection("collection('war')/war[@id='"+id.toString()+"']/web.xml"));
        try {
           war.setExtract(Util.docToString(webXmlParser.webXMLExtract(Util.stringToDoc(war.getWebXml()))));
        } catch (SAXException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return war;
    }
    
    @Override
    public List<WarArchive> getAllArchives() throws BaseXException {
        List<WarArchive> output = new ArrayList<WarArchive>();
        String c = this.dm.queryCollection("count(collection('war')/war)");
         for(int i=0;i<new Integer(c);i++) { 
             output.add(this.getWarArchive(new Long(i)));
         }
        return output;
    }
       
    //Maybe work
//    /**
//     * Find by meta data
//     * @param metaData is name of meta data e.g. message
//     * @param atributeName is name of meta Data
//     * @return List of WSDLDoc
//     * @throws BaseXException 
//     */
//    public List<WSDLDoc> findWSDLByMetaData(String metaData, String atributeName) throws BaseXException {
//        String input;
//        if (metaData == "listener")
//            input = "listener-class";
//        else if (metaData == "filter")
//            input  =  "filter-name";
//        List<WSDLDoc> output = new ArrayList<WSDLDoc>();
//        String query = this.dm.queryCollection(" declare namespace def = 'http://schemas.xmlsoap.org/wsdl';" 
//                + " distinct-values(for $war in collection('war')/war "
//                + " for $nodes in $war//*"
//                + " for $attr in $nodes/def:"+metaData
//                + " where fn:contains($attr,'"+atributeName+"')"
//                + " return distinct-values($wsdl/@id))");
//        String strarray[] = query.split(" ");
//        int intarray[] = new int[strarray.length];
//        for (int i=0; i < intarray.length; i++) {
//            intarray[i] = Integer.parseInt(strarray[i]);
//        }
//        for (int x : intarray) {
//            output.add(this.getWarArchive(new Long(x)));
//        }
//        return output;
//    }
}
