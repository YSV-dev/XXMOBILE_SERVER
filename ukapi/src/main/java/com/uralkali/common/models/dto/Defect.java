package com.uralkali.common.models.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Дефект
 * @author brzsmg
 */
public class Defect implements Serializable {


    @Column(name = "defect_id")
    private Long defectId;

    @Column(name = "work_request_id")
    private Long workRequestId;

    @Column(name = "work_request_number")
    private String workRequestNumber;

    @Column(name = "organization_id")
    private Long organizationId;
    
    @Column(name = "instance_id")
    private Long instanceId;
    
    @Column(name = "instance_number")
    private String instanceNumber;

    @Column(name = "position_number")
    private String positionNumber;
    
    @Column(name = "defect_group")
    private String defectGroup;
    
    @Column(name = "defect_type")
    private String defectType;
    
    @Column(name = "defect_description")
    private String defectDescription;
    
    @Column(name = "cause_updated_by")
    private Long causeUpdatedBy;

    @Column(name = "cause")
    private String cause;

    @Column(name = "cause_description")
    private String causeDescription;
            
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate;


    public Long getDefectId() {
        return defectId;
    }

    public Long getWorkRequestId() {
        return workRequestId;
    }

    public String getWorkRequestNumber() {
        return workRequestNumber;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public String getInstanceNumber() {
        return instanceNumber;
    }

    public String getPositionNumber() {
        return positionNumber;
    }

    public String getDefectGroup() {
        return defectGroup;
    }

    public String getDefectType() {
        return defectType;
    }

    public String getDefectDescription() {
        return defectDescription;
    }

    public Long getCauseUpdatedBy() {
        return causeUpdatedBy;
    }

    public String getCause() {
        return cause;
    }

    public String getCauseDescription() {
        return causeDescription;
    }

    public Date getCreationDate() {
        return creationDate;
    }
    
}
