package com.uralkali.common.models.base;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Asset
 */
public class AssetBase implements Serializable {
    
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
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "service")
    private String service;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "removed_date")
    private Date removed_at;

    @Column(name = "removed_by")
    private Long removed_by;
    
    public AssetBase() {

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
        return this.organizationId;
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

    public String getCategory() {
        return this.category;
    }

    public String getDepartmentName() {
        return this.departmentName;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
    
    
    
}
