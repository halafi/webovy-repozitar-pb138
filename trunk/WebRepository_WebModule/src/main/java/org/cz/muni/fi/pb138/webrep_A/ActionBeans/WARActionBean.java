/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A.ActionBeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.FileBean;
import org.cz.muni.fi.pb138.webrep_A.APIs.WarManager;
import org.cz.muni.fi.pb138.webrep_A.Entities.WSDLDoc;
import org.cz.muni.fi.pb138.webrep_A.Entities.WarArchive;
import org.cz.muni.fi.pb138.webrep_A.Impl.WarManagerImpl;
import org.cz.muni.fi.pb138.webrep_A.Util.DatabaseManager;
import org.cz.muni.fi.pb138.webrep_A.Util.Filetype;

/**
 *
 * @author Andrej Makovicky
 */
public class WARActionBean implements ActionBean {

    private FileBean warInput;
    private InputStream is;
    private OutputStream os;
    private List<WSDLDoc> wsdls = new ArrayList<WSDLDoc>();
    private ActionBeanContext context;
    private WarArchive singleWAR;
    private DatabaseManager dataMan = new DatabaseManager(Filetype.WAR);
//    private WarManager manager = new WarManagerImpl(dataMan);

    @Override
    public void setContext(ActionBeanContext abc) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools o| Templates.
    }

    @Override
    public ActionBeanContext getContext() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    public FileBean getwarInput() {
        return warInput;
    }

    public void setwarInput(FileBean warInput) {
        this.warInput = warInput;
    }

    public void warUpload() {
        try {
            is = warInput.getInputStream();
            os = new FileOutputStream(new File("/Users/mkyong/Downloads/holder-new.js")); //set File Path  ! ! ! 
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
        } catch (IOException e) {
        } finally {

            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
