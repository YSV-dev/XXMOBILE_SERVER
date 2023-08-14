package com.uralkali.common.models.dto;

import java.util.Map;

public class UserData {
    private Map<String, String> userData;

    public UserData(Map<String, String> userData) {
        this.userData = userData;
    }

    public Map<String, String> getUserData() {
        return userData;
    }

    public void setUserData(Map<String, String> userData) {
        this.userData = userData;
    }
}
