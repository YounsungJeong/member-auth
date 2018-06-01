package com.younsungs.common.domain;

public class BaseResponse<T> {
    private String message;
    private Integer code;
    private T response;

    public BaseResponse(DefaultCode defaultCode) {
        this.message = defaultCode.getMessage();
        this.code = defaultCode.getCode();
    }

    public BaseResponse(DefaultCode defaultCode, T response) {
        this(defaultCode);
        this.response = response;
    }
}
