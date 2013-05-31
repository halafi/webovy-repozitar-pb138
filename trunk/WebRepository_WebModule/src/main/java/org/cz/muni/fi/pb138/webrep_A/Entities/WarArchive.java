package org.cz.muni.fi.pb138.webrep_A.Entities;

import java.util.Date;
import org.cz.muni.fi.pb138.webrep_A.Util;
/**
 *
 * @author xmakovic
 */
public class WarArchive {

    private Long id;
    private Date date;
    private String webXml;
    private String fileName;

    public String getWebXml() {
        return webXml;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "WarArchive{" + "id=" + id + ", date=" + date + ", webXml=" + webXml + ", fileName=" + fileName + '}';
    }

 
}
