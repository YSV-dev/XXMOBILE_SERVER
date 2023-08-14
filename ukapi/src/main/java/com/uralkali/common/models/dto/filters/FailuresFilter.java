package com.uralkali.common.models.dto.filters;

public class FailuresFilter extends AssetsBaseFilter {

    private Long failure_id = null;
    private String failure_number = null;

    private Integer status_id = null;
    private String status_name = null;

    //sorting=[FAILURE_NAME]

    public FailuresFilter() { }

    public Long getFailureId() {
        return failure_id;
    }

    public void setFailureId(Long failureId) {
        this.failure_id = failureId;
    }

    public String getFailureNumber() {
        return failure_number;
    }

    public void setFailureNumber(String failureNumber) {
        this.failure_number = failureNumber;
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

    /*public String getActivityType() {
        return activity_type;
    }

    public void setActivityType(String activityType) {
        this.activity_type = activityType;
    }*/

    public void setStatusId(Integer statusId) {
        this.status_id = statusId;
    }

    /*public Long getDateFrom() {
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
    }*/

}
