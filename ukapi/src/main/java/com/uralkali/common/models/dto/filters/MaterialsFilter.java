package com.uralkali.common.models.dto.filters;

public class MaterialsFilter extends SortingFilter {

    private boolean isSearchLock = false;
    private boolean isOrganizationLock = false;

    private Long inventoryItemId = null;

    private Long organizationId = null;

    private String code = null;
    
    private String name = null;

    //sorting=[POSITION_CODE, NAME]

    public MaterialsFilter() { }

    public boolean isSearchLock() {
        return isSearchLock;
    }

    public void setSearchLock(boolean searchLock) {
        isSearchLock = searchLock;
    }

    public boolean isOrganizationLock() {
        return isOrganizationLock;
    }

    public void setOrganizationLock(boolean organizationLock) {
        isOrganizationLock = organizationLock;
    }

    public Long getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(Long inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
