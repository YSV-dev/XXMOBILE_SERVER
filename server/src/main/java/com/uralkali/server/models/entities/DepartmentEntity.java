package com.uralkali.server.models.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

/**
 *
 * @author brzsmg
 */
@Table(name = "XXEAM.XXMOBILE_DEPARTMENS_V")
public class DepartmentEntity implements Serializable {
    
    @Id
    @Column(name = "department_id")
    private Long department_id;

    @Column(name = "organization_id")
    private Long organization_id;
        
    @Column(name = "department_code")
    private String department_code;

    @Column(name = "description")
    private String description;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "created_by")
    private Long created_by;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updated_at;
    
    @Column(name = "updated_by")
    private Long updated_by;

    @Temporal(TemporalType.DATE)
    @Column(name = "removed_at")
    private Date removed_at;
    
    @Column(name = "removed_by")
    private Long removed_by;

    public Long getDepartmentId() {
        return department_id;
    }

    public void setDepartmentId(Long department_id) {
        this.department_id = department_id;
    }

    public Long getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Long organization_id) {
        this.organization_id = organization_id;
    }

    public String getDepartmentCode() {
        return department_code;
    }

    public void setDepartmentCode(String departmentCode) {
        this.department_code = departmentCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
