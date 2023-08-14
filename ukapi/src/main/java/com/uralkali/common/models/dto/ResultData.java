package com.uralkali.common.models.dto;

public class ResultData {

    private long code;
    private String message;

    public ResultData(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public long getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
