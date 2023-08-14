package com.uralkali.common.models.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Матерьял для операции.
 * @author brzsmg
 */
public class WorkOrderMaterial implements Serializable {
    
    @Column(name = "work_order_id")
    private Long workOrderId;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "operation_number")
    private Long operationNumber;
    
    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "inventory_item_id")
    private Long inventoryItemId;

    @Column(name = "position_code")
    private String positionCode;

    @Column(name = "required_quantity")
    private Float requiredQuantity;

    @Column(name = "released_quantity")
    private Float releasedQuantity;

    @Column(name = "fact_quantity")
    private Float factQuantity;
    
    @Column(name = "units")
    private String units;

    @Column(name = "description")
    private String description;

    @Column(name = "comments")
    private String comment;

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

    public Long getInventoryItemId() {
        return inventoryItemId;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public Float getRequiredQuantity() {
        return requiredQuantity;
    }

    public Float getReleasedQuantity() {
        return releasedQuantity;
    }

    public Float getFactQuantity() {
        return factQuantity;
    }

    public void setFactQuantity(Float factQuantity) {
        this.factQuantity = factQuantity;
    }

    public String getUnits() {
        return units;
    }

    public String getDescription() {
        return description;
    }

    public String getComment() {
        return comment;
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
    
    
}
