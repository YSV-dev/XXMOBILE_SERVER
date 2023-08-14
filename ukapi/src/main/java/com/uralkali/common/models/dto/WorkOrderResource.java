package com.uralkali.common.models.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Заказ
 * @author brzsmg
 */
public class WorkOrderResource implements Serializable {
    
    @Column(name = "work_order_id")
    private Long workOrderId;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "operation_number")
    private Integer operationNumber;
    
    @Column(name = "resource_number")
    private Integer resourceNumber;
    
    @Column(name = "resource_id")
    private Long resourceId;
    
    @Column(name = "person_id")
    private Long personId;
    
    @Column(name = "employee_number")
    private String employeeNumber;
    
    @Column(name = "employee_full_name")
    private String employeeFullName;
    
    @Column(name = "employee_job_name")
    private String employeeJobName;
    
    @Column(name = "employee_department")
    private String employeeDepartment;
    
    @Column(name = "department_id")
    private Long departmentId;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "resource_code")
    private String resourceCode;
    
    @Column(name = "uom_code")
    private String uomCode;
    
    @Column(name = "scheduled_units")
    private Float scheduledUnits;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "completion_date")
    private Date completionDate;    

    @Column(name = "created_by")
    private Long createdBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date createdAt;

    @Column(name = "last_updated_by")
    private Long updatedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_update_date")
    private Date updatedAt;
    

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public Integer getOperationNumber() {
        return operationNumber;
    }

    public Integer getResourceNumber() {
        return resourceNumber;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public Long getPersonId() {
        return personId;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public String getEmployeeJobName() {
        return employeeJobName;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public Long getDepartmentId() {
        return departmentId;
    }
    
    public String getDescription() {
        return description;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public String getUomCode() {
        return uomCode;
    }

    public Float getScheduledUnits() {
        return scheduledUnits;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    
}
