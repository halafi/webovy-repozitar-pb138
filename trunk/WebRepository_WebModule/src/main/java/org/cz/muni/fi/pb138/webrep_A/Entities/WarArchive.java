package org.cz.muni.fi.pb138.webrep_A.Entities;
/**
 *
 * @author xmakovic
 * 
 * This class represents WarArchive.
 */
public class WarArchive {

    private Long id;
    private String timestamp;
    private String webXml;
    private String extract;
    private String fileName;

    /*
     * Returns extracted web.xml.
     */
    public String getWebXml() {
        return webXml;
    }

    /*
     * Returns extract from web.xml.
     */
    public String getExtract() {
        return extract;
    }

    /*
     * Sets extracted data from web.xml into attribute.
     * @param String extract
     */
    public void setExtract(String extract) {
        this.extract = extract;
    }

    /*
     * Sets extracted file into attribute
     * @param String webXml
     */
    public void setWebXml(String webXml) {
        this.webXml = webXml;
    }

    /*
     * Returns Entity ID
     */
    public Long getId() {
        return id;
    }
    /*
     * Setter for Id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /*
     * Getter for FileName.
     */
    public String getFileName() {
        return fileName;
    }
    
    /*
     * Setter for filename.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    /*
     * Version getter.
     */
    public String getTimestamp() {
        return timestamp;
    }
       
    /*
     * Version setter.
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
    /*
     * returns hash of this object
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    /*
     * compares two objects of this types
     * @return true if they are the same
     * @return false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WarArchive other = (WarArchive) obj;
        if (this.id == null || !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /*
     * returns text description of this object
     */
    @Override
    public String toString() {
        return "Type: web.xml\n" + "FileName=" + fileName + "\nId=" + id + "\nVersion=" + timestamp + "\nDocument: \n\n" + webXml + "\n\n\nExtract: \n\n" + extract;
    }


 
}
