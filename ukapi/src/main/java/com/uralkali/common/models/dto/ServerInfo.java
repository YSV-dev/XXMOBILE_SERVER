package com.uralkali.common.models.dto;

import java.io.Serializable;
import java.util.Date;

public class ServerInfo implements Serializable {

    private AppVersion version;
    private AppVersion client_version;
    private Date start_time;
    private Date request_time;
    private String address;
    
    private String user;
    private String sid;
    
    private Long used_memory;
    private Long free_memory;
    private Long total_memory;
    private Long max_memory;
    
    private Integer busy_workers;
    private Integer max_workers;
    
    private Long session_count;

    public ServerInfo() {
    }

    public AppVersion getVersion() {
        return version;
    }

    public void setVersion(AppVersion version) {
        this.version = version;
    }

    public AppVersion getClientVersion() {
        return client_version;
    }

    public void setClientVersion(AppVersion clientVersion) {
        this.client_version = clientVersion;
    }

    public Date getStartTime() {
        return start_time;
    }

    public void setStartTime(Date startTime) {
        this.start_time = startTime;
    }

    public Date getRequestTime() {
        return request_time;
    }

    public void setRequestTime(Date requestTime) {
        this.request_time = requestTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
    
    public Long getUsedMemory() {
        return used_memory;
    }

    public void setUsedMemory(Long usedMemory) {
        this.used_memory = usedMemory;
    }

    public Long getFreeMemory() {
        return free_memory;
    }

    public void setFreeMemory(Long freeMemory) {
        this.free_memory = freeMemory;
    }

    public Long getTotalMemory() {
        return total_memory;
    }

    public void setTotalMemory(Long totalMemory) {
        this.total_memory = totalMemory;
    }

    public Long getMaxMemory() {
        return max_memory;
    }

    public void setMaxMemory(Long maxMemory) {
        this.max_memory = maxMemory;
    }

    public Integer getBusyWorkers() {
        return busy_workers;
    }

    public void setBusyWorkers(Integer busyWorkers) {
        this.busy_workers = busyWorkers;
    }

    public Integer getMaxWorkers() {
        return max_workers;
    }

    public void setMaxWorkers(Integer maxWorkers) {
        this.max_workers = maxWorkers;
    }

    public Long getSessionCount() {
        return session_count;
    }

    public void setSessionCount(Long sessionCount) {
        this.session_count = sessionCount;
    }    

}
