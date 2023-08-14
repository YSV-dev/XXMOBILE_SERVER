package com.uralkali.common.models.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Отказ
 * @author brzsmg
 */
public class Failure implements Serializable {


    @Column(name = "failure_id")
    private Long failureId;

    @Column(name = "failure_number")
    private String failureNumber;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "warranty_id")
    private Long warrantyId;

    @Column(name = "still_id")
    private Long stillId;

    @Column(name = "instance_id")
    private Long instanceId;

    @Column(name = "instance_number")
    private String instanceNumber;

    @Column(name = "position_number")
    private String positionNumber;

    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "status_name")
    private String statusName;

    @Temporal(TemporalType.DATE)
    @Column(name = "still_start")
    private Date stillStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "still_end")
    private Date stillEnd;

    @Column(name = "still_time")
    private Float stillTime;

    @Column(name = "still_losses")
    private Float stillLosses;
    
    @Column(name = "instance_node")
    private String instanceNode;
    
    @Column(name = "service")
    private String service;

    @Column(name = "failure_code")
    private String failureCode;

    @Column(name = "cause_code")
    private String causeCode;

    @Column(name = "resolution_code")
    private String resolutionCode;

    @Column(name = "failure_comments")
    private String failureComments;

    @Column(name = "failure_description")
    private String failureDescription;

    @Column(name = "cause_description")
    private String causeDescription;

    @Column(name = "resolution_description")
    private String resolutionDescription;

    @Column(name = "failure_type")
    private String failureType;

    @Column(name = "reason")
    private String reason;

    @Column(name = "root_reason")
    private String rootReason;

    @Column(name = "act_number")
    private String actNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "reason_description")
    private String reasonDescription;

    @Column(name = "failure_note")
    private String failureNote;

    @Column(name = "wo_activity")
    private String woActivity;

    @Column(name = "created_by_name")
    private String createdByName;

    public Long getFailureId() {
        return failureId;
    }

    public String getFailureNumber() {
        return failureNumber;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public Long getWarrantyId() {
        return warrantyId;
    }

    public Long getStillId() {
        return stillId;
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

    public Long getStatusId() {
        return statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public Date getStillStart() {
        return stillStart;
    }

    public Date getStillEnd() {
        return stillEnd;
    }

    public Float getStillTime() {
        return stillTime;
    }

    public Float getStillLosses() {
        return stillLosses;
    }

    public String getInstanceNode() {
        return instanceNode;
    }

    public String getService() {
        return service;
    }

    public String getFailureCode() {
        return failureCode;
    }

    public String getCauseCode() {
        return causeCode;
    }

    public String getResolutionCode() {
        return resolutionCode;
    }

    public String getFailureComments() {
        return failureComments;
    }

    public String getFailureDescription() {
        return failureDescription;
    }

    public String getCauseDescription() {
        return causeDescription;
    }

    public String getResolutionDescription() {
        return resolutionDescription;
    }

    public String getFailureType() {
        return failureType;
    }

    public String getReason() {
        return reason;
    }

    public String getRootReason() {
        return rootReason;
    }

    public String getActNumber() {
        return actNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getReasonDescription() {
        return reasonDescription;
    }

    public String getFailureNote() {
        return failureNote;
    }

    public String getWoActivity() {
        return woActivity;
    }

    public String getCreatedByName() {
        return createdByName;
    }
    
}
