/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uralkali.server.models;

import com.uralkali.server.models.entities.ApplicationEntity;
import com.uralkali.common.models.dto.ClientStatus;

/**
 *
 * @author brzsmg
 */
public class AppClient {
    private String sid;
    private ApplicationEntity app;
    private ClientStatus status;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public ApplicationEntity getApp() {
        return app;
    }

    public void setApp(ApplicationEntity app) {
        this.app = app;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }
    
}
