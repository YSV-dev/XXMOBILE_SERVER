package com.uralkali.common.models.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Замечание
 * @author brzsmg
 */
public class WorkRequest implements Serializable {

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

    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "status_name")
    private String statusName;

    @Column(name = "work_request_type_id")
    private Long workRequestTypeId;

    @Column(name = "work_request_type")
    private String workRequestType;

    @Temporal(TemporalType.DATE)
    @Column(name = "detection_date")
    private Date detectionDate;

    @Column(name = "remark_type")
    private String remarkType;

    @Column(name = "remark_operation")
    private String remarkOperation;

    @Column(name = "priority_name")
    private String priorityName;

    @Column(name = "executed_work_id")
    private Long executedWorkId;

    @Column(name = "measure")
    private String measure;

    @Column(name = "current_value")
    private Float currentValue;

    @Column(name = "asset_status")
    private String assetStatus;

    @Column(name = "node")
    private String node;

    @Column(name = "service")
    private String service;

    @Column(name = "performed_user")
    private String performedUser;

    @Column(name = "description")
    private String description;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "resolved_date")
    private Date resolvedDate;

    @Column(name = "work_order_id")
    private Long workOrderId;

    @Column(name = "work_order_number")
    private String workOrderNumber;

    @Column(name = "executed_user")
    private String executedUser;

    @Column(name = "executed_organization")
    private String executedOrganization;

    @Column(name = "executed_description")
    private String executedDescription;

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

    public Long getStatusId() {
        return statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public Long getWorkRequestTypeId() {
        return workRequestTypeId;
    }

    public String getWorkRequestType() {
        return workRequestType;
    }

    public Date getDetectionDate() {
        return detectionDate;
    }

    public String getRemarkType() {
        return remarkType;
    }

    public String getRemarkOperation() {
        return remarkOperation;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public Long getExecutedWorkId() {
        return executedWorkId;
    }

    public String getMeasure() {
        return measure;
    }

    public Float getCurrentValue() {
        return currentValue;
    }

    public String getAssetStatus() {
        return assetStatus;
    }

    public String getNode() {
        return node;
    }

    public String getService() {
        return service;
    }

    public String getPerformedUser() {
        return performedUser;
    }

    public String getDescription() {
        return description;
    }

    public Date getResolvedDate() {
        return resolvedDate;
    }

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public String getWorkOrderNumber() {
        return workOrderNumber;
    }

    public String getExecutedUser() {
        return executedUser;
    }

    public String getExecutedOrganization() {
        return executedOrganization;
    }

    public String getExecutedDescription() {
        return executedDescription;
    }

}
