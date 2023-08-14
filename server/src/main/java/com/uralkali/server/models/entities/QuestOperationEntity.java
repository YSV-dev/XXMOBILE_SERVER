/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uralkali.server.models.entities;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 *
 * @author brzsmg
 */
//@Entity
@Table(name = "XXMOBILE_QUEST_OPERATIONS")
public class QuestOperationEntity implements Serializable {

    @Column(name = "operation_id")
    private Long operation_id;

    @Column(name = "quest_id")
    private Long quest_id;

    @Column(name = "position")
    private Integer position;

    @Column(name = "organization_id")
    private Long organization_id;

    @Column(name = "asset_status")
    private Integer asset_status;

    @Column(name = "measure_point")
    private String measure_point;

    @Column(name = "tag_position")
    private Integer tag_position;

    @Column(name = "node")
    private String node;

    @Column(name = "quest_type")
    private String quest_type;

    @Column(name = "service")
    private String service;

    @Column(name = "answer_type_id")
    private Integer answer_type_id;

    @Deprecated
    @Column(name = "measure_units_id")
    private Integer measure_units_id;

    @Deprecated
    @Column(name = "measure_type")
    private String measure_type;

    @Column(name = "measure_method")
    private String measure_method;

    @Column(name = "measure_units")
    private String measure_units;

    @Column(name = "description")
    private String description;

    @Column(name = "default_result")
    private String default_result;

    @Column(name = "min_value")
    private Float min_value;

    @Column(name = "max_value")
    private Float max_value;

    public QuestOperationEntity() {

    }

    public Long getOperationId() {
        return this.operation_id;
    }

    public Long getQuestId() {
        return this.quest_id;
    }

    public Integer getPosition() {
        return this.position;
    }

    public Long getOrganizationId() {
        return organization_id;
    }

    public String getMeasureMethod() {
        return measure_method;
    }

    public String getMeasureUnits() {
        return measure_units;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDefaultResult() {
        return default_result;
    }

    public Integer getAssetStatus() {
        return asset_status;
    }

    public String getMeasurePoint() {
        return measure_point;
    }

    public Integer getTagPosition() {
        return tag_position;
    }

    public String getNode() {
        return node;
    }

    public String getQuestType() {
        return quest_type;
    }

    public String getService() {
        return service;
    }

    public Integer getAnswerTypeId() {
        return answer_type_id;
    }

    public Float getMinValue() {
        return min_value;
    }

    public Float getMaxValue() {
        return max_value;
    }

    public void setOperationId(Long operation_id) {
        this.operation_id = operation_id;
    }

    public void setQuestId(Long quest_id) {
        this.quest_id = quest_id;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setOrganizationId(Long organization_id) {
        this.organization_id = organization_id;
    }

    public void setAssetStatus(Integer asset_status) {
        this.asset_status = asset_status;
    }

    public void setMeasurePoint(String measure_point) {
        this.measure_point = measure_point;
    }

    public void setTagPosition(Integer tag_position) {
        this.tag_position = tag_position;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public void setQuestType(String quest_type) {
        this.quest_type = quest_type;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setAnswerTypeId(Integer answer_type_id) {
        this.answer_type_id = answer_type_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDefaultResult(String default_result) {
        this.default_result = default_result;
    }

    public void setMinValue(Float min_value) {
        this.min_value = min_value;
    }

    public void setMaxValue(Float max_value) {
        this.max_value = max_value;
    }
    
}
