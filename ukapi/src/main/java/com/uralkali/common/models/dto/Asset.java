package com.uralkali.common.models.dto;

import com.uralkali.common.models.base.AssetBase;

import java.io.Serializable;

import jakarta.persistence.Column;

/**
 * Asset
 */
public class Asset extends AssetBase implements Serializable {

    @Column(name = "templates_count")
    private Integer templatesCount;
    
    @Column(name = "inspections_count")
    private Integer inspectionsCount;
    
    @Column(name = "work_orders_count")
    private Integer workOrdersCount;
    
    @Column(name = "work_requests_count")
    private Integer workRequestsCount;
    
    @Column(name = "failures_count")
    private Integer failuresCount;
    
    @Column(name = "tech_cards_count")
    private Integer techCardsCount;
    
    @Column(name = "work_orders_new_count")
    private Integer workOrdersNewCount;
    
    @Column(name = "work_requests_new_count")
    private Integer workRequestsNewCount;
    
    @Column(name = "failures_new_count")
    private Integer failuresNewCount;
    
    public Asset() {

    }

    public Integer getTemplatesCount() {
        return this.templatesCount;
    }

    public Integer getInspectionsCount() {
        return inspectionsCount;
    }

    public Integer getWorkOrdersCount() {
        return this.workOrdersCount;
    }

    public Integer getWorkRequestsCount() {
        return this.workRequestsCount;
    }

    public Integer getFailuresCount() {
        return this.failuresCount;
    }

    public Integer getWorkOrdersNewCount() {
        return workOrdersNewCount;
    }

    public Integer getWorkRequestsNewCount() {
        return workRequestsNewCount;
    }

    public Integer getFailuresNewCount() {
        return failuresNewCount;
    }
    
    public Integer getTechCardsCount() {
        return techCardsCount;
    }
    
}
