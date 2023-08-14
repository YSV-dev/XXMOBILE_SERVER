package com.uralkali.common.models.dto;

import java.util.Date;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Lob;
import java.io.Serializable;

/**
 *
 * @author brzsmg
 */
public class OebsUser implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long user_id;
    
    @Column(name = "person_id")
    private Long person_id;
    
    @Column(name = "email_address")
    private String email_address;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "full_name")
    private String full_name;
    
    @Column(name = "encrypted_pin")
    private String encrypted_pin;

    @Column(name = "avatar")
    @Lob
    private byte[] avatar;    

    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creation_date;
    
    @Column(name = "created_by")
    private Long created_by;
    
    @Column(name = "last_update_date")
    @Temporal(TemporalType.DATE)
    private Date last_update_date;
    
    @Column(name = "last_updated_by")
    private Long last_updated_by;
    
    @Column(name = "last_update_login")
    private Long last_update_login;

    public Long getUserId() {
        return user_id;
    }

    public Long getPersonId() {
        return person_id;
    }

    public String getEmailAddress() {
        return email_address;
    }

    public String getUserName() {
        return user_name;
    }

    public String getFullName() {
        return full_name;
    }

    public String getEncryptedPin() {
        return encrypted_pin;
    }

    public byte[] getAvatar() {
        return avatar;
    }
    
    public Date getCreationDate() {
        return creation_date;
    }
    
    public Date getCreatedAt() {
        return creation_date;
    }

    public Long getCreatedBy() {
        return created_by;
    }

    public Date getUpdatedAt() {
        return last_update_date;
    }

    public Long getUpdatedBy() {
        return last_updated_by;
    }
    
}