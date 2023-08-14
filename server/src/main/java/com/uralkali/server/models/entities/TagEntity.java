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
@Entity
@Table(name = "XXEAM.XXMOBILE_TAGS")
public class TagEntity implements Serializable, WhoModel {
    
    @Id
    @Column(name="serial_number", updatable = true, nullable = false)
    private String serial_number;

    @Column(name = "tag_type_id")
    private Integer tag_type_id;

    @Column(name = "organization_id")
    private Long organization_id;
    
    @Column(name = "category")
    private String category;
    
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
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "serial_number")
    private List<TagObjectEntity> objects;

    public String getSerialNumber() {
        return serial_number;
    }

    public List<TagObjectEntity> getObjects() {
        return objects;
    }

    @Override
    public Date getCreatedAt() {
        return this.created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }
    
    @Override
    public Long getCreatedBy() {
        return this.created_by;
    }

    public void setUpdatedAt(Date updated_at) {
        this.updated_at = updated_at;
    }
    
    @Override
    public Date getUpdatedAt() {
        return this.updated_at;
    }

    @Override
    public Long getUpdatedBy() {
        return this.updated_by;
    }

    public void setRemovedAt(Date removed_at) {
        this.removed_at = removed_at;
    }
    
    @Override
    public Date getRemovedAt() {
        return this.removed_at;
    }

    public void setRemovedBy(Long removed_by) {
        this.removed_by = removed_by;
    }    
    
    @Override
    public Long getRemovedBy() {
        return this.removed_by;
    }

    public Integer getTagTypeId() {
        return tag_type_id;
    }

    public Long getOrganizationId() {
        return organization_id;
    }

    public String getCategory() {
        return category;
    }

    public Long getAppId() {
        return app_id;
    }
    
    
}
