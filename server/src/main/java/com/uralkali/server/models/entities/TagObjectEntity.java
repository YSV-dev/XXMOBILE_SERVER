package com.uralkali.server.models.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

/**
 *
 * @author brzsmg
 */
@Entity
@Table(name = "XXEAM.XXMOBILE_TAG_OBJECTS")
public class TagObjectEntity implements Serializable {
    
    @Id
    @Column(name = "tag_object_number")
    private String tag_object_number;
        
    @Column(name = "serial_number")
    private String serial_number;

    @Column(name = "organization_id")
    private Long organization_id;

    @Column(name = "object_type")
    private Integer object_type;
    
    @Column(name = "object_id")
    private Long object_id;

    @Column(name = "tag_position")
    private Integer tag_position;

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

    public String getTagObjectNumber() {
        return tag_object_number;
    }

    public Date getUpdatedAt() {
        return updated_at;
    }

    public Long getUpdatedBy() {
        return updated_by;
    }

    public void setRemovedAt(Date removed_at) {
        this.removed_at = removed_at;
    }

    public void setRemovedBy(Long removed_by) {
        this.removed_by = removed_by;
    }

    public Date getRemovedAt() {
        return removed_at;
    }

    public Long getRemovedBy() {
        return removed_by;
    }

    public String getSerialNumber() {
        return serial_number;
    }

    public Long getOrganizationId() {
        return organization_id;
    }

    public Integer getObjectType() {
        return object_type;
    }

    public Long getObjectId() {
        return object_id;
    }

    public Integer getTagPosition() {
        return tag_position;
    }
    
    
    
}
