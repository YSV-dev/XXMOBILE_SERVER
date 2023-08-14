package com.uralkali.common.models.dto;

import com.uralkali.common.models.InspectionInterface;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Осмотр. DTO.
 * @author brzsmg
 */
public class Inspection implements InspectionInterface, Serializable {

    @Column(name = "inspection_id")
    private Long inspectionId;
    
    @Column(name = "instance_id")
    private Long instanceId;

    @Column(name = "quest_id")
    private Long questId;

    @Column(name = "quest_name")
    private String questName;
    
    @Column(name = "asset_number")
    private String assetNumber;
    
    @Column(name = "position_number")
    private String positionNumber;
    
    @Column(name = "asset_status")
    private Integer assetStatus;
    
    @Column(name = "branch_name")
    private String branchName;
    
    @Column(name = "service")
    private String service;
    
    @Column(name = "brigade")
    private Integer brigade;
    
    @Column(name = "shift")
    private Integer shift;
    
    @Column(name = "source")
    private String source;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "inspection_date")
    private Date inspectionDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "completion_date")
    private Date completionDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate;
    
    @Column(name = "created_by")
    private Long createdBy;
    
    @Column(name = "created_by_full_name")
    private String createdByFullName;
    
    @Column(name = "failed_count")
    private Integer failedCount;

    
    public Long getInspectionId() {
        return inspectionId;
    }

    @Override
    public Long getInstanceId() {
        return instanceId;
    }

    @Override
    public Long getQuestId() {
        return questId;
    }

    @Override
    public String getQuestName() {
        return questName;
    }

    @Override
    public String getAssetNumber() {
        return assetNumber;
    }

    @Override
    public String getPositionNumber() {
        return positionNumber;
    }

    @Override
    public Integer getAssetStatus() {
        return assetStatus;
    }

    @Override
    public String getBranchName() {
        return branchName;
    }

    public Integer getBrigade() {
        return brigade;
    }

    public Integer getShift() {
        return shift;
    }

    public String getSource() {
        return source;
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public String getCreatedByFullName() {
        return createdByFullName;
    }

    public Integer getFailedCount() {
        return failedCount;
    }
    

}
