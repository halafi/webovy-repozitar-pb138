package org.cz.muni.fi.pb138.webrep_A.ActionBeans;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
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

/**
 *
 * @author Andrej Makovicky
 */
@UrlBinding("/wsdl/{$event}")
public class WSDLActionBean implements ActionBean {

    private FileBean wsdlInput;
    private ActionBeanContext context;
    private DatabaseManager dm = new DatabaseManager(Filetype.WSDL);
    private WSDLDocManager manager = new WSDLDocManagerImpl(dm);
    private WSDLDocParser wsdlParser = new WSDLDocParser();
    private WSDLDoc result = new WSDLDoc();
    private List<WSDLDoc> resultList = new ArrayList<WSDLDoc>();
    private Long id;
    

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
    /*
    @DefaultHandler
    public Resolution all() {
        return new ForwardResolution("/index.jsp");
    }
    */
    public void setId(String id) {
        this.id = new Long(id);
    }
    
    public WSDLDoc getDoc() {
        return manager.getWSDL(this.id);
    }

    public List<WSDLDoc> getResultList() {
        return resultList;
    }
    
    
    
    public Resolution wsdlUpload() {
        try {
            File toFile = new File(System.getProperty("user.home")+File.separator+wsdlInput.getFileName());
            wsdlInput.save(toFile);
            String content = Util.readFile(toFile);

            WSDLDoc wsdl = new WSDLDoc();
            wsdl.setId(manager.getNewId());
            wsdl.setTimestamp(Util.getTimeStamp());
            wsdl.setFileName(wsdlInput.getFileName());
            wsdl.setDocument(Util.stripXMLHeader(content));
            wsdl.setExtract(Util.docToString(wsdlParser.wsdlExtract(Util.stringToDoc(content))));
            manager.createWSDL(wsdl);
            
            toFile.delete();
            wsdlInput.delete();
        } catch (IOException ex) {
            Logger.getLogger(WSDLActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ForwardResolution("/showWSDL.jsp");
    }
    
    public Resolution showSingle(){
       return new ForwardResolution("/showSingleWSDL.jsp");
    }
    
    public List<WSDLDoc> getWSDLs(){
        return manager.getAllWSDLs();
    }
    
    public WSDLDoc getDocument() {
        WSDLDoc wsdl;
        wsdl = manager.getWSDL(result.getId());
        wsdl.setDocument(wsdl.getDocument().replaceAll("<", "&lt;").replaceAll(">","&gt; "));
        wsdl.setExtract(wsdl.getExtract().replaceAll("<", "&lt;").replaceAll(">","&gt; "));
        return wsdl;
    }

    
    public String getId(){
        return result.getId().toString();
    }
    
    public Resolution searchId() {
        Long searchId = Long.parseLong(context.getRequest().getParameter("idInput"));
        result = manager.getWSDL(searchId);
        return new ForwardResolution("/showSingleWSDL.jsp");
    }
    
    public Resolution searchData() {
        String searchData = context.getRequest().getParameter("dataInput");
        resultList = manager.findWSDLByData(searchData);
        return new ForwardResolution("/showMultipleWSDL.jsp");
    }
    
    

    
}
