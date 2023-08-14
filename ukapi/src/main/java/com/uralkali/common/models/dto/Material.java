package com.uralkali.common.models.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Материал из справочника.
 * @author brzsmg
 */
public class Material implements Serializable {
    
    @Column(name = "inventory_item_id")
    private Long inventoryItemId;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "units")
    private String units;

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
    

    public Long getInventoryItemId() {
        return inventoryItemId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getUnits() {
        return units;
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
