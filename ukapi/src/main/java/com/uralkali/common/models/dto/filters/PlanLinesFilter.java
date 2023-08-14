package com.uralkali.common.models.dto.filters;

public class PlanLinesFilter extends AssetsFilter {

    private boolean isSearchLock = false;

    private Long planId = null;
    private Boolean inspected = true;
    private Boolean noInspected = true;

    //sorting=[PLAN_ID]

    public PlanLinesFilter() { }

    public boolean isSearchLock() {
        return isSearchLock;
    }

    public void setSearchLock(boolean searchLock) {
        isSearchLock = searchLock;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Boolean getInspected() {
        return inspected;
    }

    public void setInspected(Boolean inspected) {
        this.inspected = inspected;
    }

    public Boolean getNoInspected() {
        return noInspected;
    }

    public void setNoInspected(Boolean noInspected) {
        this.noInspected = noInspected;
    }

}
