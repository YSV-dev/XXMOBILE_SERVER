package com.uralkali.common.models.dto;

/**
 *
 * @author brzsmg
 */
public class CountResult extends SyncRequest {
    
    private Integer allCount;
    private Integer queryCount;

    public CountResult() { }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }
    
    public Integer getQueryCount() {
        return queryCount;
    }

    public void setQueryCount(Integer queryCount) {
        this.queryCount = queryCount;
    }

}