/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uralkali.server.models.entities;

import jakarta.persistence.*;

/**
 *
 * @author brzsmg
 */
public class AttachmentEntity {
    //@Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="G_AT_ID_S")
    //@SequenceGenerator(name="G_AT_ID_S", sequenceName="XXEAM.XXMOBILE_INSPECT_LINE_ID_SEQ", allocationSize=1)
    //@Column(name="ATTACHMENT_ID", updatable = false, nullable = false)
    private Long attachmentId;
    private Long localId;
    private String fileName;
    private String position;
    private String path;
    private byte [] image;
    private Boolean Checked;

    public byte[] getImage() {
        return image;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPosition() {
        return position;
    }
    
}
