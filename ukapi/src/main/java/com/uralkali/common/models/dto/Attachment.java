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
public class Attachment implements Serializable, WhoModel {

    @Column(name = "attachment_id")
    private Long attachmentId;

    @Column(name = "document_id")
    private Long documentId;

    @Column(name = "category")
    private String category;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "base_file_name")
    private String baseFileName;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "thumbnail")
    @Lob
    private byte[] thumbnail;

    @Column(name = "data")
    @Lob
    private byte[] data;

    @Column(name = "data_size")
    private Long dataSize;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_update_date")
    private Date updatedAt;

    @Column(name = "last_updated_by")
    private Long updatedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "remove_date")
    private Date removedAt;

    @Column(name = "removed_by")
    private Long removedBy;

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBaseFileName() {
        return baseFileName;
    }

    public void setBaseFileName(String baseFileName) {
        this.baseFileName = baseFileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Long getDataSize() {
        return dataSize;
    }

    public void setDataSize(Long dataSize) {
        this.dataSize = dataSize;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public Date getRemovedAt() {
        return removedAt;
    }

    public void setRemovedAt(Date removedAt) {
        this.removedAt = removedAt;
    }

    @Override
    public Long getRemovedBy() {
        return removedBy;
    }

    public void setRemovedBy(Long removedBy) {
        this.removedBy = removedBy;
    }

}
