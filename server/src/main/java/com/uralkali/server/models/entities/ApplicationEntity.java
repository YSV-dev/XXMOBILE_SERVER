package com.uralkali.server.models.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

/**
 * Сущность приложения в БД.
 * 
 * @author brzsmg
 */
@Entity
@Table(name = "XXMOBILE_APPS")
public class ApplicationEntity implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="G_APP_ID_S")
    @SequenceGenerator(name="G_APP_ID_S", sequenceName="XXMOBILE_APP_ID_S", allocationSize=1)
    @Column(name="APP_ID", updatable = false, nullable = false)
    private Long app_id;
    
    @Column(nullable = false)
    private String app_name;
    
    @Column(nullable = false)
    private Integer version;

    @Type(type="yes_no")
    private Boolean dev_mode;
    //@DatabaseField
    private String token;
    //@DatabaseField
    private String software_id;
    //@DatabaseField
    private String imei;
    //@DatabaseField
    private String device_name;
    //@DatabaseField
    private String system_name;
    //@DatabaseField
    private String system_version;
    //@DatabaseField
    private String description;
    
    @Column(name="CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
    
    private Integer created_by;
    
    @Column(name="LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    @Column(name="LAST_UPDATED_BY")
    private Integer updated_by;
    
    @Column(name="REMOVED_DATE") //REMOVE_DATE
    @Temporal(TemporalType.TIMESTAMP)
    private Date removed_at;
    
    public Long getAppId() {
        return app_id;
    }

    public void setAppId(Long app_id) {
        this.app_id = app_id;
    }

    public String getAppName() {
        return app_name;
    }

    public void setAppName(String app_name) {
        this.app_name = app_name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean isDevMode() {
        return dev_mode;
    }

    public void setDevMode(Boolean devMode) {
        this.dev_mode = devMode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public String getSoftwareId() {
        return software_id;
    }

    public void setSoftwareId(String softwareId) {
        this.software_id = softwareId;
    }
    
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDeviceName() {
        return device_name;
    }

    public void setDeviceName(String device_name) {
        this.device_name = device_name;
    }

    public String getSystemName() {
        return system_name;
    }

    public void setSystemName(String system_name) {
        this.system_name = system_name;
    }

    public String getSystemVersion() {
        return system_version;
    }

    public void setSystemVersion(String system_version) {
        this.system_version = system_version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

    public Integer getCreatedBy() {
        return created_by;
    }

    public void setCreatedBy(Integer created_by) {
        this.created_by = created_by;
    }

    public Date getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getUpdatedBy() {
        return updated_by;
    }

    public void setUpdatedBy(Integer updated_by) {
        this.updated_by = updated_by;
    }

    public Date getRemovedAt() {
        return removed_at;
    }

    public void setRemovedAt(Date removedAt) {
        this.removed_at = removedAt;
    }
    
    
}
