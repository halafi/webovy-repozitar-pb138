package org.cz.muni.fi.pb138.webrep_A.ActionBeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.cz.muni.fi.pb138.webrep_A.APIs.WSDLDocManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;
import org.cz.muni.fi.pb138.webrep_A.Impl.WSDLDocManagerImpl;
import org.cz.muni.fi.pb138.webrep_A.Parser.WSDLDocParser;
import org.cz.muni.fi.pb138.webrep_A.Util.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Util.Filetype;
import org.cz.muni.fi.pb138.webrep_A.Util.Util;
import org.xml.sax.SAXException;

/**
 *
 * @author Andrej Makovicky
 */
@UrlBinding("/")
public class WSDLActionBean implements ActionBean {

    private FileBean wsdlInput;
    private InputStream is;
    private OutputStream os;
    private List<WSDLDoc> wsdls = new ArrayList<WSDLDoc>();
    private ActionBeanContext context;
    private WSDLDoc singleWSDL;
    private DatabaseManager dataMan = new DatabaseManager(Filetype.WSDL);
    private WSDLDocManager manager = new WSDLDocManagerImpl(dataMan);
    private WSDLDocParser wsdlParser = new WSDLDocParser();
    

    @Override
    public ActionBeanContext getContext() { return context; }
    
    @Override
    public void setContext(ActionBeanContext context) { this.context = context; }

    public FileBean getwsdlInput() {
        return wsdlInput;
    }

    public void setwsdlInput(FileBean wsdlInput) {
        this.wsdlInput = wsdlInput;
    }
    
    @DefaultHandler
    public Resolution all() {
        return new ForwardResolution("/showPlayers.jsp");
    }

    
    public Resolution wsdlUpload(){
            /*
            try {
                    is = wsdlInput.getInputStream();
                    File file = new File(wsdlInput.getFileName());
                    os = new FileOutputStream(file); //set File Path  ! ! ! 
                    int read = 0;
                    byte[] bytes = new byte[1024];

                    while ((read = is.read(bytes)) != -1) {
                        os.write(bytes, 0, read);
                    }
                } catch (IOException e) {
                      e.printStackTrace();
                } finally {
                    try {
                        is.close();
                        os.close();
                        wsdlInput.delete();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }*/
            
        
        try {
            is = wsdlInput.getInputStream();
            File file = new File(wsdlInput.getFileName());
            String content = Util.readFile(file);
            
            WSDLDoc wsdl = new WSDLDoc();
            wsdl.setId(new Long(0));
            wsdl.setTimestamp(Util.getTimeStamp());
            wsdl.setFileName(file.toString());
            wsdl.setDocument(Util.stripXMLHeader(content));
            wsdl.setExtract(Util.docToString(wsdlParser.wsdlExtract(Util.stringToDoc(content))));
            manager.createWSDLCollection();
            manager.createWSDL(wsdl);
            
            wsdlInput.delete();
        } catch (IOException ex) {
            
        }
        return new ForwardResolution("/showWSDL.jsp");
    }
    
    public Resolution showSingle(){
        
        
        // wsdl print doc
        //wsdl print extract
        
       return new ForwardResolution("/showSingleWSDL.jsp");
    }
    
    public Resolution showAll(){
        
        
        // wsdl iterate through wsdls, print into table.
        
       return new ForwardResolution("/showWSDL.jsp");
    }
    
    public Resolution find(){
        String def = "TBD";
        
        wsdls = manager.findWSDLByData(def);
        // wsdl print doc
        //wsdl print extract
        
       return new ForwardResolution("/showSingleWSDL.jsp");
    }
    
    

    
}
