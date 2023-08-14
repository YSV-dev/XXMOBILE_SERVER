package com.uralkali.common.models.dto.filters;

import java.io.Serializable;

public class SortingFilter implements Serializable {

    private boolean isRequest = false;
    private boolean isFilter = false;

    private String sorting = null; //[POSITION_NUMBER, INSTANCE_NUMBER]
    private String sortingOrder = null;

    private Integer limit = null;

    public SortingFilter() { }

    public boolean isRequest() {
        return isRequest;
    }

    public boolean isFilter() {
        return isFilter;
    }

    public void setFilter(boolean filter) {
        isFilter = filter;
    }

    public void setRequest(boolean request) {
        isRequest = request;
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
