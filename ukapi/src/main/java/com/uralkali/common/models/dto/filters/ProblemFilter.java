package com.uralkali.common.models.dto.filters;

public class ProblemFilter extends SortingFilter {

    private boolean isSearchLock = false;
    private boolean isOrganizationLock = false;

    private Long problemId = null;

    private Long organizationId = null;
    private String organizationCode = null;

    private Long dateFrom = null;
    private Long dateTo = null;

    private String name = null;


    public ProblemFilter() { }

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

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
