/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uralkali.server.models.entities;

import com.swlibs.common.database.models.WhoModel;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "XXEAM.XXMOBILE_ALL_ATTACHMENTS")
public class UAttachmentEntity implements Serializable, WhoModel{
    @Column(name = "sync_at")
    private Date sync_at;

    @Id
    @Column(name = "local_attachment_id")
    private Long local_attachment_id;

    @Column(name = "attachment_id")
    private Long attachment_id;
    
    @Column(name = "document_id")
    private Long document_id;

    @Column(name = "attachment_type")
    private String attachment_type;

    @Column(name = "entity_name")
    private String entity_name;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creation_date;

    @Column(name = "app_id")
    private Long app_id;

    @Column(name = "path")
    private String file_path;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "thumbnail")
    private byte[] thumbnail;

    @Column(name = "attribute_0")
    protected String attribute_0;

    @Column(name = "attribute_1")
    protected String attribute_1;

    @Column(name = "attribute_2")
    protected String attribute_2;

    @Column(name = "attribute_3")
    protected String attribute_3;

    @Column(name = "attribute_4")
    protected String attribute_4;

    @Column(name = "attribute_5")
    protected String attribute_5;

    @Column(name = "attribute_6")
    protected String attribute_6;

    @Column(name = "attribute_7")
    protected String attribute_7;

    @Column(name = "attribute_8")
    protected String attribute_8;

    @Column(name = "attribute_9")
    protected String attribute_9;

    @Column(name = "data")
    protected byte[] data;
    
    @Column(name = "description")
    protected String description;


    public void setDataByPath(){

    }

    public void setAppId(Long app_id) {
        this.app_id = app_id;
    }

    public void setAttachmentId(Long attachment_id) {
        this.attachment_id = attachment_id;
    }

    public void setAttachmentType(String attachment_type) {
        this.attachment_type = attachment_type;
    }

    public String getAttachmentType() {
        return attachment_type;
    }


    public void setCreationDate(Date creation_date) {
        this.creation_date = creation_date;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    
    public void setDescription(String description){
        this.description = description;
    }

    public void setEntityName(String entity_name) {
        this.entity_name = entity_name;
    }

    public void setFilePath(String file_path) {
        this.file_path = file_path;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public byte[] getData() {
        return data;
    }
    
    public String getDescription(){
        return this.description;
    }

    public Date getCreationDate() {
        return creation_date;
    }

    public Date getSync_at() {
        return sync_at;
    }

    public Long getAppId() {
        return app_id;
    }

    public Long getAttachmentId() {
        return attachment_id;
    }

    public String getEntityName() {
        return entity_name;
    }

    public String getFilePath() {
        return file_path;
    }

    public Date getCreatedAt() {
        return this.creation_date;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Date getUpdatedAt() {
        return creation_date;
    }

    public Long getUpdatedBy() {
        return createdBy;
    }

    public Date getRemovedAt() {
        return null;
    }

    public Long getRemovedBy() {
        return null;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getAttribute_1() {
        return attribute_1;
    }

    public String getAttribute_2() {
        return attribute_2;
    }

    public String getAttribute_3() {
        return attribute_3;
    }

    public String getAttribute_4() {
        return attribute_4;
    }

    public String getAttribute_5() {
        return attribute_5;
    }

    public String getAttribute_6() {
        return attribute_6;
    }

    public String getAttribute_7() {
        return attribute_7;
    }

    public String getAttribute_8() {
        return attribute_8;
    }

    public String getAttribute_9() {
        return attribute_9;
    }

    public String getAttribute_0() {
        return attribute_0;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setAttribute_1(String attribute_1) {
        this.attribute_1 = attribute_1;
    }

    public void setAttribute_2(String attribute_2) {
        this.attribute_2 = attribute_2;
    }

    public void setAttribute_3(String attribute_3) {
        this.attribute_3 = attribute_3;
    }

    public void setAttribute_4(String attribute_4) {
        this.attribute_4 = attribute_4;
    }

    public void setAttribute_5(String attribute_5) {
        this.attribute_5 = attribute_5;
    }

    public void setAttribute_6(String attribute_6) {
        this.attribute_6 = attribute_6;
    }

    public void setAttribute_7(String attribute_7) {
        this.attribute_7 = attribute_7;
    }

    public void setAttribute_8(String attribute_8) {
        this.attribute_8 = attribute_8;
    }

    public void setAttribute_9(String attribute_9) {
        this.attribute_9 = attribute_9;
    }

    public void setAttribute_0(String attribute_0) {
        this.attribute_0 = attribute_0;
    }
    
    public Long getDocumentID(){
        return document_id;
    }
    
    public void setDocumentID(Long document_id){
        this.document_id = document_id;
    }
}
