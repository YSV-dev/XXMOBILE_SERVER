package com.uralkali.common.models.dto;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

/**
 * Заказ
 * @author brzsmg
 */
public class WorkOrder implements Serializable {
    
    @Column(name = "work_order_id")
    private Long workOrderId;
    
    @Column(name = "work_order_number")
    private String workOrderNumber;
    
    @Column(name = "organization_id")
    private Long organizationId;
    
    @Column(name = "instance_id")
    private Long instanceId;
    
    @Column(name = "instance_number")
    private String instanceNumber;

    @Column(name = "position_number")
    private String positionNumber;
    
    @Column(name = "category")
    private String category;

    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "status_name")
    private String statusName;
    
    @Column(name = "parent_work_order_number")
    private String parentWorkOrderNumber;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "scheduled_start_date")
    private Date scheduledStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "scheduled_end_date")
    private Date scheduledEndDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "actual_start_date")
    private Date actualStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "actual_end_date")
    private Date actualEndDate;

    @Column(name = "activity_type")
    private String activityType;
    
    @Column(name = "activity_cause")
    private String activityCause;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "work_description")
    private String workDescription;
    
    @Column(name = "performer")
    private String performer;
    
    @Column(name = "performer_description")
    private String performerDescription;
    
    @Column(name = "execution_control_code")
    private Integer executionControlCode;
    
    @Column(name = "interval_completed")
    private Boolean intervalCompleted;
    
    @Column(name = "execution_status")
    private Integer executionStatus;  
            
    @Column(name = "execution_status_meaning")
    private String executionStatusMeaning;
    
    @Column(name = "actual_expense")
    private Float actualExpense;
    
    @Column(name = "overtime_factor")
    private Float overtimeFactor;
    
    @Column(name = "comments")
    private String comments;
    
    @Column(name = "contractors")
    private String contractors;
    
    @Column(name = "all_time_meaning")
    private String allTimeMeaning;

    @Transient
    private Boolean hideAssetInfo;

    public void setHideAssetInfo(Boolean showAssetInfo) {
        this.hideAssetInfo = showAssetInfo;
    }

    public Boolean getHideAssetInfo() {
        return hideAssetInfo;
    }

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public String getWorkOrderNumber() {
        return workOrderNumber;
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

    public String getCategory() {
        return category;
    }

    public Long getStatusId() {
        return statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getParentWorkOrderNumber() {
        return parentWorkOrderNumber;
    }
    
    public Date getScheduledStartDate() {
        return scheduledStartDate;
    }

    public Date getScheduledEndDate() {
        return scheduledEndDate;
    }

    public Date getActualStartDate() {
        return actualStartDate;
    }

    public Date getActualEndDate() {
        return actualEndDate;
    }

    public String getActivityType() {
        return activityType;
    }

    public String getActivityCause() {
        return activityCause;
    }

    public String getDescription() {
        return description;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public String getPerformer() {
        return performer;
    }

    public String getPerformerDescription() {
        return performerDescription;
    }

    public Integer getExecutionControlCode() {
        return executionControlCode;
    }
    
    public Boolean getIntervalCompleted() {
        return intervalCompleted;
    }

    public Integer getExecutionStatus() {
        return executionStatus;
    }

    public String getExecutionStatusMeaning() {
        return executionStatusMeaning;
    }

    public Float getActualExpense() {
        return actualExpense;
    }

    public Float getOvertimeFactor() {
        return overtimeFactor;
    }

    public String getComments() {
        return comments;
    }

    public String getContractors() {
        return contractors;
    }

    public String getAllTimeMeaning() {
        return allTimeMeaning;
    }

}
