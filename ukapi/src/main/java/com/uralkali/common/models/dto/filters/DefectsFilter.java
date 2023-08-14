package com.uralkali.common.models.dto.filters;

import java.io.Serializable;

public class DefectsFilter implements Serializable {

    private boolean isRequest = false;

    private Long work_request_id = null;
    private Long defect_id = null;

    private Long organization_id = null;
    private String organization_code = null;

    private String sorting = null; //WORK_ORDER_NAME //SCHEDULED_START_DATE
    private String sortingOrder = null;

    private Integer limit = null;

    public DefectsFilter() { }

    public boolean isRequest() {
        return isRequest;
    }

    public void setRequest(boolean request) {
        isRequest = request;
    }

    public Long getWorkRequestId() {
        return work_request_id;
    }

    public void setWorkRequestId(Long work_request_id) {
        this.work_request_id = work_request_id;
    }

    public Long getDefectId() {
        return defect_id;
    }

    public void setDefectId(Long defect_id) {
        this.defect_id = defect_id;
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

    public void setOrganizationCode(String organizationCode) {
        this.organization_code = organizationCode;
    }

    public String getSorting() {
        return sorting;
    }

    public void setSorting(String sorting) {
        this.sorting = sorting;
    }

    public String getSortingOrder() {
        return sortingOrder;
    }

    public void setSortingOrder(String sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
