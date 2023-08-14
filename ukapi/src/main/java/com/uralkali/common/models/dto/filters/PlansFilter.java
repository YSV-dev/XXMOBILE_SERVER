package com.uralkali.common.models.dto.filters;

public class PlansFilter extends SortingFilter {

    private boolean isSearchLock = false;
    private boolean isOrganizationLock = false;

    private Long planId = null;

    private Long organizationId = null;
    private String organizationCode = null;

    private Long dateFrom = null;
    private Long dateTo = null;

    private Integer shiftId = null;
    private String shiftName = null;

    private String service = null;

    //sorting=[PLAN_ID]

    public PlansFilter() { }

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

    public Long getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Long dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Long getDateTo() {
        return dateTo;
    }

    public void setDateTo(Long dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
