/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A.ActionBeans;

import com.sun.xml.internal.ws.api.server.SDDocument.WSDL;
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
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 *
 * @author Andrej Makovicky
 */
public class WSDLActionBean implements ActionBean {

    private FileBean wsdlInput;
    private InputStream is;
    private OutputStream os;
    private List<WSDL> wsdls = new ArrayList<WSDL>();

    @Override
    public void setContext(ActionBeanContext abc) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools o| Templates.
    }

    @Override
    public ActionBeanContext getContext() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    public FileBean getwsdlInput() {
        return wsdlInput;
    }

    public void setwsdlInput(FileBean wsdlInput) {
        this.wsdlInput = wsdlInput;
    }
    
//    public ForwardResolution wsdlUpload() {
//        try {
//            is = wsdlInput.getInputStream();
//            os = new FileOutputStream(new File("/Users/mkyong/Downloads/holder-new.js")); //set File Path  ! ! ! 
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            while ((read = is.read(bytes)) != -1) {
//                os.write(bytes, 0, read);
//            }
//        } catch (IOException e) {
//            
//        } finally {
//
//            try {
//                is.close();
//                os.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return new ForwardResolution("/WEB-INF/showWSDL.jsp");
//        
//    }
    
    public Resolution wsdlUpload(){
        try {
            is = wsdlInput.getInputStream();
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
        return new ForwardResolution("/WEB-INF/showWSDL.jsp");
    }

//    public ForwardResolution wsdlUpload() {
//        try {
//            is = wsdlInput.getInputStream();
//            os = new FileOutputStream(new File("/Users/mkyong/Downloads/holder-new.js")); //set File Path  ! ! ! 
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            while ((read = is.read(bytes)) != -1) {
//                os.write(bytes, 0, read);
//            }
//        } catch (IOException e) {
//            
//        } finally {
//
//            try {
//                is.close();
//                os.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return new ForwardResolution("/WEB-INF/showWSDL.jsp");
//    }
}