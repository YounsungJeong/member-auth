package com.younsungs.common.domain;

import lombok.Getter;

@Getter
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

    private static final BaseResponse successResponse = new BaseResponse(DefaultCode.OK);
    public static BaseResponse okResponse(){
        return successResponse;
    }
}
