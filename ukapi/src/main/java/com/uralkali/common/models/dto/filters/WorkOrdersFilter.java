package com.uralkali.common.models.dto.filters;

public class WorkOrdersFilter extends AssetsBaseFilter {

    private Long work_order_id = null;
    private String work_order_number = null;

    private String performer = null;
    private String contractor = null;
    private boolean isContractorLock = false;

    private Integer status_id = null;
    //private Integer status_id = null;

    private String status_name = null;
    private boolean isStatusLock = false;

    private String parent_work_order_number = null;
    
    private String activity_type = null;

    private Long date_from = null;
    private Long date_to = null;

    //sorting=[WORK_ORDER_NAME, SCHEDULED_START_DATE]

    public WorkOrdersFilter() { }

    public void setWorkOrderId(Long workOrderId) {
        this.work_order_id = workOrderId;
    }

    public Long getWorkOrderId() {
        return work_order_id;
    }

    public String getWorkOrderNumber() {
        return work_order_number;
    }

    public void setWorkOrderNumber(String workOrderNumber) {
        this.work_order_number = workOrderNumber;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public void setContractorLock(boolean contractorLock) {
        isContractorLock = contractorLock;
    }

    public boolean isContractorLock() {
        return isContractorLock;
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

    public void setStatusLock(boolean statusLock) {
        isStatusLock = statusLock;
    }

    public boolean isStatusLock() {
        return isStatusLock;
    }

    public String getParentWorkOrderNumber() {
        return parent_work_order_number;
    }

    public void setParentWorkOrderNumber(String parentWorkOrderNumber) {
        this.parent_work_order_number = parentWorkOrderNumber;
    }

    public String getActivityType() {
        return activity_type;
    }

    public void setActivityType(String activityType) {
        this.activity_type = activityType;
    }

    public void setStatusId(Integer statusId) {
        this.status_id = statusId;
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
