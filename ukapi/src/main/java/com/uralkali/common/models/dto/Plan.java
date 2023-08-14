package com.uralkali.common.models.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class Plan implements Serializable {

    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "organization_code")
    private String organizationCode;

    @Column(name = "service")
    private String service;

    @Temporal(TemporalType.DATE)
    @Column(name = "plan_date")
    private Date planDate;

    @Column(name = "shift")
    private Integer shift;

    @Column(name = "shift_start")
    private Date shiftStart;

    @Column(name = "shift_end")
    private Date shiftEnd;

    @Column(name = "description")
    private String description;
    
    @Column(name = "line_count")
    private Long lineCount;
    
    @Column(name = "inspection_line_count")
    private Long inspectionLineCount;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_by_name")
    private String createdByName;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Temporal(TemporalType.DATE)
    @Column(name = "removed_at")
    private Date removedAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "removed_by")
    private Long removedBy;

    @Column(name = "removed_by_name")
    private String removedByName;

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getService() {
        return service;
    }

    public void setServiceName(String service) {
        this.service = service;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public Integer getShift() {
        return shift;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }

    public Date getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(Date shiftStart) {
        this.shiftStart = shiftStart;
    }

    public Date getShiftEnd() {
        return shiftEnd;
    }

    public void setShiftEnd(Date shiftEnd) {
        this.shiftEnd = shiftEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLineCount() {
        return lineCount;
    }

    public void setLineCount(Long lineCount) {
        this.lineCount = lineCount;
    }

    public Long getInspectionLineCount() {
        return inspectionLineCount;
    }

    public void setInspectionLineCount(Long inspectionLineCount) {
        this.inspectionLineCount = inspectionLineCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Date getRemovedAt() {
        return removedAt;
    }

    public void setRemovedAt(Date removedAt) {
        this.removedAt = removedAt;
    }

    public Long getRemovedBy() {
        return removedBy;
    }

    public void setRemovedBy(Long removedBy) {
        this.removedBy = removedBy;
    }

    public String getRemovedByName() {
        return removedByName;
    }

    public void setRemovedByName(String removedByName) {
        this.removedByName = removedByName;
    }
}
