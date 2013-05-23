package org.cz.muni.fi.pb138.webrep_A;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Document;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xmakovic
 */
public class XSD {

    private Long id;
    private Date date;
    private Document document;
    private String fileName;
    private Document extract;
    private Map<String, List<String>> elements;
    private List<String> simpleTypes;
    private List<String> complexTypes;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    public Document getExtract() {
        return extract;
    }

    public void setExtract(Document extract) {
        this.extract = extract;
    }

    public Map<String, List<String>> getElements() {
        return elements;
    }

    public void setElements(Map<String, List<String>> elements) {
        this.elements = elements;
    }

    public List<String> getSimpleTypes() {
        return simpleTypes;
    }

    public void setSimpleTypes(List<String> simpleTypes) {
        this.simpleTypes = simpleTypes;
    }

    public List<String> getComplexTypes() {
        return complexTypes;
    }

    public void setComplexTypes(List<String> complexTypes) {
        this.complexTypes = complexTypes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final XSD other = (XSD) obj;
        if (this.id == null || !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "XSD{" + "id=" + id + ", date=" + date + ", document=" + document + ", fileName=" + fileName + ", extract=" + extract + ", elements=" + elements + ", simpleTypes=" + simpleTypes + ", complexTypes=" + complexTypes + '}';
    }
}
