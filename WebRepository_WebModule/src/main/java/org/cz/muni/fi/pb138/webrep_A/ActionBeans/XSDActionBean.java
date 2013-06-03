package org.cz.muni.fi.pb138.webrep_A.ActionBeans;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.cz.muni.fi.pb138.webrep_A.APIs.XSDManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;
import org.cz.muni.fi.pb138.webrep_A.Entities.WarArchive;
import org.cz.muni.fi.pb138.webrep_A.Entities.XSD;
import org.cz.muni.fi.pb138.webrep_A.Impl.XSDManagerImpl;
import org.cz.muni.fi.pb138.webrep_A.Parser.XSDParser;
import org.cz.muni.fi.pb138.webrep_A.Util.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Util.Filetype;
import org.cz.muni.fi.pb138.webrep_A.Util.Util;

/**
 *
 * @author Andrej Makovicky
 */
@UrlBinding("/xsd/{$event}")
public class XSDActionBean implements ActionBean {

    private FileBean xsdInput;
    private ActionBeanContext context;
    private DatabaseManager dm = new DatabaseManager(Filetype.XSD);
    private XSDManager manager = new XSDManagerImpl(dm);
    private XSDParser xsdParser = new XSDParser();
    private XSD result = new XSD();
    private List<XSD> resultList = new ArrayList<XSD>();
    
    @Override
    public ActionBeanContext getContext() { return context; }
    
    @Override
    public void setContext(ActionBeanContext context) { this.context = context; }


    public FileBean getxsdInput() {
        return xsdInput;
    }

    public void setxsdInput(FileBean xsdInput) {
        this.xsdInput = xsdInput;
    }
    /*
    @DefaultHandler
    public Resolution all() {
        return new ForwardResolution("/showPlayers.jsp");
    }
    */
    public Resolution xsdUpload() {
        try {
            File toFile = new File(System.getProperty("user.home")+File.separator+xsdInput.getFileName());
            xsdInput.save(toFile);
            String content = Util.readFile(toFile);

            XSD xsd = new XSD();
            xsd.setId(manager.getNewId());
            xsd.setTimestamp(Util.getTimeStamp());
            xsd.setFileName(xsdInput.getFileName());
            xsd.setDocument(Util.stripXMLHeader(content));
            xsd.setExtract(Util.docToString(xsdParser.xsdExtract(Util.stringToDoc(content))));
            manager.createXSD(xsd);
            
            toFile.delete();
            xsdInput.delete();
        } catch (IOException ex) {
            Logger.getLogger(XSDActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ForwardResolution("/showXSD.jsp");
    }
    
    public XSD getDocument() {
        return manager.getXSD(result.getId());
    }
    
    public String getId(){
        return result.getId().toString();
    }
    
    public Resolution searchId() {
        Long searchId = Long.parseLong(context.getRequest().getParameter("idInput"));
        result = manager.getXSD(searchId);
        return new ForwardResolution("/showSingleXSD.jsp");
    }
    
    public List<XSD> getXSDs(){
        return manager.getAllXSDs() ;
    }
    
    public Resolution searchData() {
        String searchData = context.getRequest().getParameter("dataInput");
        resultList = manager.findXSDByData(searchData);
        return new ForwardResolution("/showMultipleXSD.jsp");
    }
    
}
