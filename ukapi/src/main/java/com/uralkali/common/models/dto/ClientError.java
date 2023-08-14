package com.uralkali.common.models.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class ClientError implements Serializable {

    @Temporal(TemporalType.DATE)
    @Column(name = "occurrence_date")
    private Date occurrence_date;

    @Column(name = "error_type")
    private String error_type;

    @Column(name = "description")
    private String description;

    @Column(name = "app_id")
    private Long app_id;

    @Column(name = "app_version")
    private Integer app_version;

    @Column(name = "organization_id")
    private Long organization_id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "language")
    private String language;

    @Column(name = "exception_class")
    private String exception_class;

    @Column(name = "exception_message")
    private String exception_message;

    @Column(name = "stack_trace")
    private String stack_trace;

    public void setOccurrenceDate(Date occurrenceDate) {
        this.occurrence_date = occurrenceDate;
    }

    public Date getOccurrenceDate() {
        return occurrence_date;
    }

    public void setErrorType(String error_type) {
        this.error_type = error_type;
    }

    public String getErrorType() {
        return error_type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAppId(Long app_id) {
        this.app_id = app_id;
    }

    public Long getAppId() {
        return app_id;
    }

    public void setAppVersion(Integer version) {
        this.app_version = version;
    }

    public Integer getAppVersion() {
        return app_version;
    }

    public void setOrganizationId(Long organization_id) {
        this.organization_id = organization_id;
    }

    public Long getOrganizationId() {
        return organization_id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public Long getUserId() {
        return user_id;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setExceptionClass(String exception_class) {
        this.exception_class = exception_class;
    }

    public String getExceptionClass() {
        return exception_class;
    }

    public void setExceptionMessage(String exception_message) {
        this.exception_message = exception_message;
    }

    public String getExceptionMessage() {
        return exception_message;
    }

    public void setStackTrace(String stack_trace) {
        this.stack_trace = stack_trace;
    }

    public String getStackTrace() {
        return stack_trace;
    }

}
