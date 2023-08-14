package com.uralkali.common.models.dto;

import com.swlibs.common.database.models.WhoModel;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author brzsmg
 */
public class UploadResult implements Serializable, WhoModel {

    private Long syncId;
    private Date createdAt;
    private Long createdBy;
    private Date updatedAt;
    private Long updatedBy;
    private Date removedAt;
    private Long removedBy;

    public UploadResult() { }

    public UploadResult(
            Long syncId,
            Date createdAt,
            Long createdBy,
            Date updatedAt,
            Long updatedBy,
            Date removedAt,
            Long removedBy
    ) {
        this.syncId = syncId;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.removedAt = removedAt;
        this.removedBy = removedBy;
    }

    public Long getSyncId() {
        return syncId;
    }

    @Override
    public Date getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public Long getCreatedBy() {
        return this.createdBy;
    }

    @Override
    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public Long getUpdatedBy() {
        return this.updatedBy;
    }

    @Override
    public Date getRemovedAt() {
        return this.removedAt;
    }

    @Override
    public Long getRemovedBy() {
        return this.removedBy;
    }


}
