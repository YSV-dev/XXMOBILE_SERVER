/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uralkali.server.models.entities;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 *
 * @author brzsmg
 */
@Table(name = "XXEAM.XXMOBILE_ORGANIZATIONS")
public class OrganizationEntity implements Serializable {

    @Id
    @Column(name = "organization_id")
    private Long organization_id;

    @Column(name = "code")
    private String code;

    @Column(name = "alias")
    private String alias;

    @Column(name = "name")
    private String name;

    @Column(name = "org_type")
    private String org_type;

    public Long getOrganizationId() {
        return organization_id;
    }

    public void setOrganizationId(Long organization_id) {
        this.organization_id = organization_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgType() {
        return org_type;
    }

    public void setOrgType(String org_type) {
        this.org_type = org_type;
    }
    
}
