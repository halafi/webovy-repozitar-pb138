/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A.Util;

import org.cz.muni.fi.pb138.webrep_A.APIs.WSDLDocManager;
import org.cz.muni.fi.pb138.webrep_A.APIs.WarManager;
import org.cz.muni.fi.pb138.webrep_A.APIs.XSDManager;
import org.cz.muni.fi.pb138.webrep_A.Impl.WSDLDocManagerImpl;
import org.cz.muni.fi.pb138.webrep_A.Impl.WarManagerImpl;
import org.cz.muni.fi.pb138.webrep_A.Impl.XSDManagerImpl;

/**
 *
 * @author Filip
 */
public class InicializeDTB {
    public static void main(String[] args) {
        DatabaseManager wsdlDBManager = new DatabaseManager(Filetype.WSDL);
        DatabaseManager xsdDBManager = new DatabaseManager(Filetype.XSD);
        DatabaseManager warXmlDBManager = new DatabaseManager(Filetype.WAR);
        WSDLDocManager wsdlManager = new WSDLDocManagerImpl(wsdlDBManager);
        XSDManager xsdManager = new XSDManagerImpl(xsdDBManager);
        WarManager warManager = new WarManagerImpl(warXmlDBManager);
        
        wsdlManager.createWSDLCollection();
        xsdManager.createXSDCollection();
        warManager.createWARCollection();
        
    }
}
