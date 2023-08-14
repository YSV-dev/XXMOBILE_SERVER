package com.uralkali.common.models.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class PlanLine implements Serializable {
    
    @Column(name = "plan_line_id")
    private Long planLineId;
    
    @Column(name = "plan_id")
    private Long planId;
    
    @Column(name = "instance_id")
    private Long instanceId;
    
    @Column(name = "inspection_id")
    private Long inspectionid;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "instance_number")
    private String instanceNumber;

    @Column(name = "position_number")
    private String positionNumber;

    @Column(name = "location_code")
    private String locationCode;

    @Column(name = "department_name")
    private String departmentName;

    @Temporal(TemporalType.DATE)
    @Column(name = "inspection_date")
    private Date inspectionDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "completion_date")
    private Date completionDate;
    
    @Column(name = "failed_count")
    private Integer failedCount;
    
    @Column(name = "tags_count")
    private Integer tagsCount;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "removed_at")
    private Date removedAt;

    @Column(name = "removed_by")
    private Long removedBy;

    public Long getPlanLineId() {
        return planLineId;
    }

    public void setPlanLineId(Long planLineId) {
        this.planLineId = planLineId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public Long getInspectionid() {
        return inspectionid;
    }

    public void setInspectionid(Long inspectionid) {
        this.inspectionid = inspectionid;
    }

    public String getInstanceNumber() {
        return instanceNumber;
    }

    public void setInstanceNumber(String instanceNumber) {
        this.instanceNumber = instanceNumber;
    }

    public String getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(String positionNumber) {
        this.positionNumber = positionNumber;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public Integer getFailedCount() {
        return failedCount;
    }

    public Integer getTagsCount() {
        return tagsCount;
    }
    
    
}
