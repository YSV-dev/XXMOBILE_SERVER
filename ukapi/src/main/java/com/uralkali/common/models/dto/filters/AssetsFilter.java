package com.uralkali.common.models.dto.filters;

import java.io.Serializable;

public class AssetsFilter extends AssetsBaseFilter implements Serializable {

    private Boolean templatesExists = null;

    //sorting=[POSITION_NUMBER, INSTANCE_NUMBER]

    public AssetsFilter() { }

    public Boolean getTemplatesExists() {
        return templatesExists;
    }

    public void setTemplatesExists(Boolean templatesExists) {
        this.templatesExists = templatesExists;
    }

}
