package com.uralkali.common.models.dto;

import java.io.Serializable;

import jakarta.persistence.Column;

public class TechnicalCard implements Serializable {
    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "segment")
    private String segment;

    @Column(name = "activity_association_id")
    private Long activityAssociationId;

    @Column(name = "asset_activity_id")
    private Long assetActivityId;

    @Column(name = "instance_id")
    private Long instanceId;

    @Column(name = "instance_number")
    private String instanceNumber;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "inventory_item_id")
    private Long inventoryItemId;

    @Column(name = "last_vld_organization_id")
    private Long lastVldOrganizationId;

    @Column(name = "attribute9")
    private String attribute9;

    @Column(name = "tp")
    private String tp;

    public String getAttribute9() {
        return attribute9;
    }

    public String getSegment() {
        return segment;
    }

    public Long getActivityAssociationId() {
        return activityAssociationId;
    }

    public Long getAssetActivityId() {
        return assetActivityId;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public Long getInventoryItemId() {
        return inventoryItemId;
    }

    public Long getLastVldOrganizationId() {
        return lastVldOrganizationId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getTP() {
        return tp;
    }

    public String getInstanceNumber() {
        return instanceNumber;
    }

    public TechnicalCard(){

    }
}
