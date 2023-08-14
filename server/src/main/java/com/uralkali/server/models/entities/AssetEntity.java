package com.uralkali.server.models.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

/**
 *
 * @author brzsmg
 */
@Table(name = "XXEAM.XXMOBILE_ASSETS")
public class AssetEntity implements Serializable {
    
    @Id
    @Column(name = "instance_id")
    private Long instanceId;

    @Column(name = "instance_number")
    private String instanceNumber;

    @Column(name = "instance_description")
    private String instanceDescription;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "position_number")
    private String positionNumber;

    @Column(name = "group_id")
    private Long groupId;
    
    @Column(name = "group_name")
    private String groupName;
    
    @Column(name = "criticality")
    private String criticality;
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "service")
    private String service;
    
    @Column(name = "serial_number")
    private String serialNumber;
    
    @Column(name = "asset_number")
    private String assetNumber;

    @Column(name = "templates_count")
    private Integer templatesCount;
    
    @Column(name = "inspections_count")
    private Integer inspectionsCount;
    
    @Column(name = "work_orders_count")
    private Integer workOrdersCount;
    
    @Column(name = "work_requests_count")
    private Integer workRequestsCount;
    
    @Column(name = "failures_count")
    private Integer failuresCount;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "created_by")
    private Long created_by;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updated_at;
    
    @Column(name = "updated_by")
    private Long updated_by;

    @Temporal(TemporalType.DATE)
    @Column(name = "removed_at")
    private Date removed_at;
    
    @Column(name = "removed_by")
    private Long removed_by;
    
    public AssetEntity() {

    }

    public Long getInstanceId() {
        return this.instanceId;
    }

    public String getInstanceNumber() {
        return this.instanceNumber;
    }

    public String getInstanceDescription() {
        return this.instanceDescription;
    }

    public String getOrganizationName() {
        return this.organizationName;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public String getPositionNumber() {
        return this.positionNumber;
    }

    public Long getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public String getCriticality() {
        return criticality;
    }

    public String getCategory() {
        return category;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getAssetNumber() {
        return assetNumber;
    }

    public Integer getTemplatesCount() {
        return this.templatesCount;
    }

    public Integer getInspectionsCount() {
        return inspectionsCount;
    }

    public Integer getWorkOrdersCount() {
        return workOrdersCount;
    }

    public Integer getWorkRequestsCount() {
        return workRequestsCount;
    }

    public Integer getFailuresCount() {
        return failuresCount;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    
}
