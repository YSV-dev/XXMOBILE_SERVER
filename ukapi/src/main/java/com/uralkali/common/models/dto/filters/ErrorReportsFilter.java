package com.uralkali.common.models.dto.filters;

public class ErrorReportsFilter extends SortingFilter {

    private boolean isSearchLock = false;
    private boolean isOrganizationLock = false;

    private Long errorReportId = null;

    private Long organization_id = null;
    private String organization_code = null;

    private Long date_from = null;
    private Long date_to = null;

    //sorting=[ERROR_REPORT_ID, FULL_NAME]

    public ErrorReportsFilter() { }

    public boolean isOrganizationLock() {
        return isOrganizationLock;
    }

    public void setOrganizationLock(boolean organizationLock) {
        isOrganizationLock = organizationLock;
    }

    public boolean isSearchLock() {
        return isSearchLock;
    }

    public void setSearchLock(boolean searchLock) {
        isSearchLock = searchLock;
    }

    public Long getErrorReportId() {
        return errorReportId;
    }

    public void setErrorReportId(Long errorReportId) {
        this.errorReportId = errorReportId;
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

    public Long getDateFrom() {
        return date_from;
    }

    public void setDateFrom(Long date_from) {
        this.date_from = date_from;
    }

    public Long getDateTo() {
        return date_to;
    }

    public void setDateTo(Long date_to) {
        this.date_to = date_to;
    }

}
