package com.uralkali.common.models.dto.filters;

public class AssetsBaseFilter extends SortingFilter {

    private boolean isOrganizationLock = false;
    private boolean isCategoryLock = false;
    private boolean isInstanceLock = false;

    private Long instance_id = null;

    private Long organization_id = null;
    private String organization_code = null;

    private String department_name = null;
    private String branch_name = null;
    private String category = null;
    private String position_number = null;
    private String instance_number = null;

    //sorting=[POSITION_NUMBER, INSTANCE_NUMBER]

    public AssetsBaseFilter() { }

    public boolean isOrganizationLock() {
        return isOrganizationLock;
    }

    public void setOrganizationLock(boolean organizationLock) {
        isOrganizationLock = organizationLock;
    }

    public boolean isCategoryLock() {
        return isCategoryLock;
    }

    public void setCategoryLock(boolean categoryLock) {
        isCategoryLock = categoryLock;
    }

    public boolean isInstanceLock() {
        return isInstanceLock;
    }

    public void setInstanceLock(boolean instanceLock) {
        isInstanceLock = instanceLock;
    }

    public Long getInstanceId() {
        return instance_id;
    }

    public void setInstanceId(Long instanceId) {
        this.instance_id = instanceId;
    }
    
    public Long getOrganizationId() {
        return organization_id;
    }

    public void setOrganizationId(Long organizationId) {
        this.organization_id = organizationId;
    }

    public String getOrganizationCode() {
        return organization_code;
    }

    public void setOrganizationCode(String organization_code) {
        this.organization_code = organization_code;
    }

    public String getDepartmentName() {
        return department_name;
    }

    public void setDepartmentName(String department_name) {
        this.department_name = department_name;
    }

    public String getBranchName() {
        return branch_name;
    }

    public void setBranchName(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPositionNumber() {
        return position_number;
    }

    public void setPositionNumber(String position_number) {
        this.position_number = position_number;
    }

    public String getInstanceNumber() {
        return instance_number;
    }

    public void setInstanceNumber(String instance_number) {
        this.instance_number = instance_number;
    }

}
