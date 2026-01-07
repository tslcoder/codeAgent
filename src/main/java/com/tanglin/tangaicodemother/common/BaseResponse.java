package com.tanglin.tangaicodemother.common;

import com.tanglin.tangaicodemother.exception.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: tang-ai-code-mother
 * @ClassName BaseResponse
 * @description:
 * @author: TSL
 * @create: 2025-12-29 10:39
 * @Version 1.0
 **/
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}

