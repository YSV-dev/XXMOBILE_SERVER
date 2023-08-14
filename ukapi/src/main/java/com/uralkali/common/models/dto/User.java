package com.uralkali.common.models.dto;

import java.util.Date;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.swlibs.common.database.models.WhoModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author brzsmg
 */
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable, WhoModel {

    @Id
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "person_id")
    private Long personId;
    
    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "full_name")
    private String fullName;
    
    @Column(name = "encrypted_pin")
    private String encryptedPin;
    
    @Column(name = "company")
    private String company;

    @Column(name = "avatar")
    @Lob
    private byte[] avatar;
    
    @Column(name = "photo")
    @Lob
    private byte[] photo;
    
    //@Type(type="yes_no")
    @Column(name = "developer")
    private Boolean developer;
    
    @Column(name = "super_user")
    private Boolean superUser;

    @Column(name = "reliability_user")
    private Boolean reliabilityUser;
    
    @Column(name = "technologist")
    private Boolean technologist;
    
    @Column(name = "customer")
    private Boolean customer;

    @Column(name = "repairer")
    private Boolean repairer;

    @Column(name = "subcontractor")
    private Boolean subcontractor;
    
    @Column(name = "truncated_data")
    private Boolean truncatedData;

    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    
    @Column(name = "created_by")
    private Long createdBy;
    
    @Column(name = "last_update_date")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
    
    @Column(name = "last_updated_by")
    private Long updatedBy;
    
    @Column(name = "last_update_login")
    private Long lastUpdateLogin;
    
    @Column(name = "removed_date") //TODO: remove_date
    @Temporal(TemporalType.DATE)
    private Date removedAt;
    
    @Column(name = "removed_by")
    private Long removedBy;
    
}