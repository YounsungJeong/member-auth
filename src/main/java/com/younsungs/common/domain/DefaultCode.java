package com.younsungs.common.domain;

public enum DefaultCode {
    OK(200, "OK");

    private Integer code;
    private String message;

    DefaultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
