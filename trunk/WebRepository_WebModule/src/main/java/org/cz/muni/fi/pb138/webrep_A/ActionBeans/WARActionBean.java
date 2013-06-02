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
import org.cz.muni.fi.pb138.webrep_A.APIs.WarManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WarArchive;
import org.cz.muni.fi.pb138.webrep_A.Impl.WarManagerImpl;
import org.cz.muni.fi.pb138.webrep_A.Parser.WebXMLParser;
import org.cz.muni.fi.pb138.webrep_A.Util.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Util.Filetype;
import org.cz.muni.fi.pb138.webrep_A.Util.Util;

/**
 *
 * @author Andrej Makovicky
 */
@UrlBinding("/")
public class WARActionBean implements ActionBean {

    private FileBean warInput;
    private ActionBeanContext context;
    private DatabaseManager dm = new DatabaseManager(Filetype.WAR);
    private WarManager manager = new WarManagerImpl(dm);
    private WebXMLParser webParser = new WebXMLParser();

    @Override
    public ActionBeanContext getContext() { return context; }
    
    @Override
    public void setContext(ActionBeanContext context) { this.context = context; }

    public FileBean getwarInput() {
        return warInput;
    }

    public void setwarInput(FileBean warInput) {
        this.warInput = warInput;
    }

    public Resolution warUpload() {
        try {
            File toFile = new File(System.getProperty("user.home")+File.separator+warInput.getFileName());
            warInput.save(toFile);
            String content = Util.docToString(Util.warExtract(toFile));;

            WarArchive war = new WarArchive();
            war.setId(manager.getNewId());
            war.setTimestamp(Util.getTimeStamp());
            war.setFileName(warInput.getFileName());
            war.setWebXml(Util.stripXMLHeader(content));
            war.setExtract(Util.docToString(webParser.webXMLExtract(Util.stringToDoc(content))));
            manager.createWarArchive(war);
            
            toFile.delete();
            warInput.delete();
        } catch (IOException ex) {
            Logger.getLogger(WSDLActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ForwardResolution("/showWSDL.jsp");
    }
}
