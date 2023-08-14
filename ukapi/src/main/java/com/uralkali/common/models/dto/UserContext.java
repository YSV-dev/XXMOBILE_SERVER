package com.uralkali.common.models.dto;

public class UserContext {
    private int userId;
    private int respId;

    public UserContext(int userId, int respId) {
        this.userId = userId;
        this.respId = respId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRespId() {
        return respId;
    }

    public void setRespId(int respId) {
        this.respId = respId;
    }
}
