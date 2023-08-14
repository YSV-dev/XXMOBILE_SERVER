package com.uralkali.common.models.dto.filters;

public class WorkRequestsFilter extends AssetsBaseFilter {

    private Long work_request_id = null;
    private String work_request_number = null;

    private Integer status_id = null;
    private String status_name = null;

    //sorting=[WORK_ORDER_NAME, SCHEDULED_START_DATE]

    public WorkRequestsFilter() { }

    public Long getWorkRequestId() {
        return work_request_id;
    }

    public void setWorkRequestId(Long workRequestId) {
        this.work_request_id = workRequestId;
    }

    public String getWorkRequestNumber() {
        return work_request_number;
    }

    public void setWorkRequestNumber(String workRequestNumber) {
        this.work_request_number = workRequestNumber;
    }

    public Integer getStatusId() {
        return status_id;
    }

    public void setStatusName(String statusName) {
        this.status_name = statusName;
    }

    public String getStatusName() {
        return status_name;
    }

    public void setStatusId(Integer statusId) {
        this.status_id = statusId;
    }

}
