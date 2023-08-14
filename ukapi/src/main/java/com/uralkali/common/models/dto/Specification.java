package com.uralkali.common.models.dto;

import java.io.Serializable;

import jakarta.persistence.Column;

public class Specification implements Serializable {
    @Column(name = "component_item_id")
    private Long componentItemID;
    
    @Column(name = "component_item")
    private String componentItem;
    
    @Column(name = "segment1")
    private String segment;

    @Column(name = "description")
    private String description;

    @Column(name = "component_quantity")
    private Float componentQuantity;

    @Column(name = "uom")
    private String uom;

    public Float getComponentQuantity() {
        return componentQuantity;
    }

    public Long getComponentItemID() {
        return componentItemID;
    }
    
    public String getComponentItem() {
        return componentItem;
    }

    public String getDescription() {
        return description;
    }
    
    public String getSegment() {
        return segment;
    }

    public String getUom() {
        return uom;
    }

    public Specification(){}
}
