/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cz.muni.fi.pb138.webrep_A.Util;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Andrej Makovicky
 */
public class OutputTransformer {

    private static final Logger logger = Logger.getLogger(OutputTransformer.class.getName());
    private static String stylesheet = "/IdTransformation";

    /**
     * This method transforms the XML database file to the output file using the
     * specified XSLT stylesheet.
     *
     * @param input Path to the database file (XML)
     * @param stylesheet Path to the selected XSLT stylesheet
     * @param output Name of the transformed file
     *
     * @throws ExporterException on error during the transformation
     */
    public void export(String input, String output) {


        if (!isFile(input)) {
            throw new IllegalArgumentException("Database file not found");
        }


        if (output == null || output.isEmpty()) {
            throw new IllegalArgumentException("No output file specified");
        }

        Source source = new StreamSource(new File(input));
        Source sheet = new StreamSource(new File(stylesheet));

        TransformerFactory factory = TransformerFactory.newInstance();

        Transformer transformer = null;
        try {
            transformer = factory.newTransformer(sheet);
        } catch (TransformerConfigurationException ex) {
            logger.log(Level.SEVERE, null, ex);

        }

        try {
            transformer.transform(source, new StreamResult(new File(output)));
        } catch (TransformerException ex) {
            logger.log(Level.SEVERE, null, ex);

        }
    }

    /**
     * This method tests a string for being a valid file path
     *
     * @param file File path
     * @return True if file is a valid file path, false otherwise
     */
    private boolean isFile(String file) {
        if (file == null || file.isEmpty()) {
            return false;
        }
        File f = new File(file);

        return f.exists();
    }
}
