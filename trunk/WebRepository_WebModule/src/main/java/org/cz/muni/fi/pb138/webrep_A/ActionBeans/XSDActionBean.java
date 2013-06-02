/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A.ActionBeans;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.cz.muni.fi.pb138.webrep_A.APIs.XSDManager;
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
@UrlBinding("/")
public class XSDActionBean implements ActionBean {

    private ActionBeanContext context;
    private FileBean xsdInput;
    private DatabaseManager xsdDBManager = new DatabaseManager(Filetype.XSD);
    XSDManager manager = new XSDManagerImpl(xsdDBManager);
    XSDParser xsdParser = new XSDParser();
    
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
            Logger.getLogger(WSDLActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ForwardResolution("/showXSD.jsp");
    }
    
}
