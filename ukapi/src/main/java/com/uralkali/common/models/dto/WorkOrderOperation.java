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
public class WorkOrderOperation implements Serializable {
    
    @Column(name = "work_order_id")
    private Long workOrderId;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "operation_number")
    private Long operationNumber;
    
    @Column(name = "department_id")
    private Long departmentId;
    
    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "first_unit_start_date")
    private Date firstUnitStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "first_unit_completion_date")
    private Date firstUnitCompletionDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_unit_start_date")
    private Date lastUnitStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_unit_completion_date")
    private Date lastUnitCompletionDate;

    
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
    
    @Column(name = "units")
    private String units;
    
    @Column(name = "amount")
    private Float amount;
    
    @Column(name = "amount_fact")
    private Float amountFact;
    
    @Column(name = "basis")
    private String basis;
    
    @Column(name = "norm")
    private String norm;
    
    @Column(name = "reason")
    private String reason;
    
    @Column(name = "reason_description")
    private String reasonDescription;
    
    @Column(name = "overtime_factor")
    private Float overtimeFactor;
    
    @Column(name = "execution_control_code")
    private Integer executionControlCode;
    
    @Column(name = "interval_completed")
    private Boolean intervalCompleted;
    
    @Column(name = "created_by")
    private Long createdBy;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date createdAt;
    
    @Column(name = "last_updated_by")
    private Long updatedBy;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "last_update_date")
    private Date updatedAt;

    @Transient
    private Boolean checked;

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public Long getOperationNumber() {
        return operationNumber;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getDescription() {
        return description;
    }

    public Date getFirstUnitStartDate() {
        return firstUnitStartDate;
    }

    public Date getFirstUnitCompletionDate() {
        return firstUnitCompletionDate;
    }

    public Date getLastUnitStartDate() {
        return lastUnitStartDate;
    }

    public Date getLastUnitCompletionDate() {
        return lastUnitCompletionDate;
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

    public String getUnits() {
        return units;
    }

    public Float getAmount() {
        return amount;
    }

    public Float getAmountFact() {
        return amountFact;
    }

    public void setAmountFact(Float amountFact) {
        this.amountFact = amountFact;
    }

    public String getBasis() {
        return basis;
    }

    public String getNorm() {
        return norm;
    }

    public String getReason() {
        return reason;
    }

    public String getReasonDescription() {
        return reasonDescription;
    }

    public Float getOvertimeFactor() {
        return overtimeFactor;
    }

    public Integer getExecutionControlCode() {
        return executionControlCode;
    }

    public Boolean getIntervalCompleted() {
        return intervalCompleted;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Boolean getChecked() {
        return checked;
    }
}
