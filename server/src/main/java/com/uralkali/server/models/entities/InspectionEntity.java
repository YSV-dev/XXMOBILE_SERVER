/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uralkali.server.models.entities;

import com.swlibs.common.database.models.WhoModel;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;

/**
 *
 * @author brzsmg
 */
//@Entity
//@Table(name = "XXEAM.XXMOBILE_INSPECTIONS")
public class InspectionEntity implements Serializable, WhoModel {

    @Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="G_INS_ID_S")
    //@SequenceGenerator(name="G_INS_ID_S", sequenceName="XXEAM.XXMOBILE_INSPECTION_ID_SEQ", allocationSize=1)
    @Column(name="INSPECTION_ID", updatable = false, nullable = false)
    private Long inspection_id;

    @Column(name = "instance_id")
    private Long instance_id;

    @Column(name = "organization_id")
    private Long organization_id;

    @Column(name = "asset_number")
    private String asset_number;

    @Column(name = "position_number")
    private String position_number;
    
    @Column(name = "branch_name")
    private String branch_name;
    
    @Column(name = "department") //TODO:department_name
    private String department_name;
    
    @Column(name = "service")
    private String service;

    @Temporal(TemporalType.DATE)
    @Column(name = "inspection_date")
    private Date inspection_date;

    @Temporal(TemporalType.DATE)
    @Column(name = "completion_date")
    private Date completion_date;

    @Column(name = "asset_status")
    private Integer asset_status;

    @Column(name = "quest_id")
    private Long quest_id;

    @Column(name = "quest_name")
    private String quest_name;

    @Temporal(TemporalType.DATE)
    @Column(name = "expired_date")
    private Date expired_date;

    @Column(name = "brigade")
    private Integer brigade;

    @Column(name = "shift")
    private Integer shift;
    
    @Column(name = "app_id")
    private Long app_id;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date created_at;

    @Column(name = "created_by")
    private Long created_by;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_update_date")
    private Date updated_at;
    
    @Column(name = "last_updated_by")
    private Long updated_by;

    @Temporal(TemporalType.DATE)
    @Column(name = "removed_date")
    private Date removed_at;
    
    @Column(name = "removed_by")
    private Long removed_by;
    
    //@OneToMany(mappedBy = "lines", targetEntity = InspectionLine.class)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "inspection_id")
    private List<InspectionLineEntity> lines;

    public Long getInspectionId() {
        return inspection_id;
    }

    public Long getInstanceId() {
        return instance_id;
    }

    public Long getOrganizationId() {
        return organization_id;
    }

    public String getAssetNumber() {
        return asset_number;
    }

    public String getPositionNumber() {
        return position_number;
    }

    public String getBranchName() {
        return branch_name;
    }

    public String getDepartmentName() {
        return department_name;
    }

    public String getService() {
        return service;
    }

    public Date getInspectionDate() {
        return inspection_date;
    }

    public Date getCompletionDate() {
        return completion_date;
    }

    public Integer getAssetStatus() {
        return asset_status;
    }

    public Long getQuestId() {
        return quest_id;
    }

    public String getQuestName() {
        return quest_name;
    }

    public Date getExpiredDate() {
        return expired_date;
    }

    public Integer getBrigade() {
        return brigade;
    }

    public Integer getShift() {
        return shift;
    }
    
    public Long getAppId() {
        return app_id;
    }

    public void setAppId(Long app_id) {
        this.app_id = app_id;
    }

    @Override
    public Date getCreatedAt() {
        return this.created_at;
    }

    @Override
    public Long getCreatedBy() {
        return this.created_by;
    }

    @Override
    public Date getUpdatedAt() {
        return this.updated_at;
    }

    @Override
    public Long getUpdatedBy() {
        return this.updated_by;
    }

    @Override
    public Date getRemovedAt() {
        return this.removed_at;
    }

    @Override
    public Long getRemovedBy() {
        return this.removed_by;
    }
    
    public List<InspectionLineEntity> getLines() {
        return lines;
    }
    
}
