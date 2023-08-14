package com.uralkali.common.models.dto;

import java.util.Date;

import jakarta.persistence.Column;

public class ErrorReport extends ClientError {

    @Column(name = "error_report_id")
    private Long error_report_id;

    @Column(name = "registration_date")
    private Date registration_date;

    @Column(name = "device_name")
    private String device_name;

    @Column(name = "system_name")
    private String system_name;

    @Column(name = "system_version")
    private String system_version;

    @Column(name = "organization_alias")
    private String organization_alias;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "full_name")
    private String full_name;

    public Long getErrorReportId() {
        return error_report_id;
    }

    public Date getRegistrationDate() {
        return registration_date;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registration_date = registrationDate;
    }

    public String getDeviceName() {
        return device_name;
    }

    public void setDeviceName(String deviceName) {
        this.device_name = deviceName;
    }

    public String getSystemName() {
        return system_name;
    }

    public void setSystemName(String systemName) {
        this.system_name = systemName;
    }

    public String getSystemVersion() {
        return system_version;
    }

    public void setSystemVersion(String systemVersion) {
        this.system_version = systemVersion;
    }

    public void setErrorReportId(Long errorReportId) {
        this.error_report_id = errorReportId;
    }

    public String getOrganizationAlias() {
        return organization_alias;
    }

    public void setOrganizationAlias(String organizationAlias) {
        this.organization_alias = organizationAlias;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String userName) {
        this.user_name = userName;
    }

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String fullName) {
        this.full_name = fullName;
    }
}
