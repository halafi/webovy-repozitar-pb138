package org.cz.muni.fi.pb138.webrep_A.Entities;
/**
 *
 * @author xmakovic
 */
public class WarArchive {

    private Long id;
    private String timestamp;
    private String webXml;
    private String extract;
    private String fileName;

    public String getWebXml() {
        return webXml;
    }

    public String getExtract() {
        return extract;
    }

    public void setExtract(String extract) {
        this.extract = extract;
    }

    public void setWebXml(String webXml) {
        this.webXml = webXml;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "WarArchive{" + "id=" + id + ", timestamp=" + timestamp + ", webXml=" + webXml + ", extract=" + extract + ", fileName=" + fileName + '}';
    }


 
}
