package com.uralkali.common.models.dto;

import com.swlibs.common.database.models.WhoModel;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Вложение/Приложение/Документ
 * @author brzsmg
 */
public class Document implements Serializable {

    @Column(name = "document_id")
    private Long documentId;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "data")
    @Lob
    private byte[] data;

    @Column(name = "url")
    private String url;

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
