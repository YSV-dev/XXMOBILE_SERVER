package com.uralkali.common.models.dto;

import java.io.Serializable;

public class ServerStatus implements Serializable {

    private AppVersion version;
    private String date_time;

    public ServerStatus() {
    }

    public AppVersion getVersion() {
        return version;
    }

    public void setVersion(AppVersion version) {
        this.version = version;
    }

    public String getDateTime() {
        return date_time;
    }

    public void setDateTime(String dateTime) {
        this.date_time = dateTime;
    }

}
