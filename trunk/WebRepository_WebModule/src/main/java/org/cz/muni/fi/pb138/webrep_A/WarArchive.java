package org.cz.muni.fi.pb138.webrep_A;

import java.util.Date;
import java.util.List;
import org.w3c.dom.Document;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xmakovic
 */
public class WarArchive {

    private Long id;
    private Date date;
    private Document document;
    private String fileName;
    private Document extract;
    private List<String> filters;
    private List<String> listeners;

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

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Document getExtract() {
        return extract;
    }

    public void setExtract(Document extract) {
        this.extract = extract;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public List<String> getListeners() {
        return listeners;
    }

    public void setListeners(List<String> listeners) {
        this.listeners = listeners;
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
        return "WarArchive{" + "id=" + id + ", date=" + date + ", document=" + document + ", fileName=" + fileName + ", extract=" + extract + ", filters=" + filters + ", listeners=" + listeners + '}';
    }

    
}
