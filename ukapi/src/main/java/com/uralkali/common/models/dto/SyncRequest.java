package com.uralkali.common.models.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author brzsmg
 */
public class SyncRequest implements Serializable {
    
    private String className;
    
    private Boolean count;
    
    private Long organizationId;
    
    private Integer limit;
    
    private String lastId;
    
    private Date syncDate;
    
    public SyncRequest() { }
    
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Boolean isCount() {
        return count;
    }

    public void setCount(Boolean count) {
        this.count = count;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
    
    

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }
    
}
