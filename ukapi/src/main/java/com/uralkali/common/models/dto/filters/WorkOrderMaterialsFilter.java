package com.uralkali.common.models.dto.filters;

import java.io.Serializable;

public class WorkOrderMaterialsFilter implements Serializable {

    private boolean isRequest = false;

    private Long work_order_id = null;
    private Long operation_number = null;
    private Long inventory_item_id = null;

    private Long organization_id = null;
    private String organization_code = null;

    private String sorting = null; //WORK_ORDER_NAME //SCHEDULED_START_DATE
    private String sortingOrder = null;

    private Integer limit = null;

    public WorkOrderMaterialsFilter() { }

    public boolean isRequest() {
        return isRequest;
    }

    public void setRequest(boolean request) {
        isRequest = request;
    }

    public void setWorkOrderId(Long workOrderId) {
        this.work_order_id = workOrderId;
    }

    public Long getWorkOrderId() {
        return work_order_id;
    }

    public Long getOperationNumber() {
        return operation_number;
    }

    public void setOperationNumber(Long operationNumber) {
        this.operation_number = operationNumber;
    }

    public Long getInventoryItemId() {
        return inventory_item_id;
    }

    public void setInventoryItemId(Long inventoryItemId) {
        this.inventory_item_id = inventoryItemId;
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
