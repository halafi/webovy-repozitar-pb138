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
public class WSDLDoc {

    private Long id;
    private Date date;
    private Document document;
    private Document extract;
    private List<String> operations;
    private List<String> messages;

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

    public Document getExtract() {
        return extract;
    }

    public void setExtract(Document extract) {
        this.extract = extract;
    }

    public List<String> getOperations() {
        return operations;
    }

    public void setOperations(List<String> operations) {
        this.operations = operations;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
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
        final WSDLDoc other = (WSDLDoc) obj;
        if (this.id == null || !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WSDLDoc{" + "id=" + id + ", date=" + date + ", document=" + document + ", extract=" + extract + ", operations=" + operations + ", messages=" + messages + '}';
    }
}
