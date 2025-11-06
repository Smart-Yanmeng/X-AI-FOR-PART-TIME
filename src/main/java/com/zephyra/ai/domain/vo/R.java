package com.zephyra.ai.domain.vo;

import com.zephyra.ai.domain.enums.ResultCode;

/**
 * Common response object for API calls.
 *
 * @author York
 */
public record R<T>(Integer code, String msg, T data) {

    public static <T> R<T> success(T data) {
        return new R<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> R<T> fail(String msg) {
        return new R<>(ResultCode.INTERNAL_SERVER_ERROR.getCode(), ResultCode.INTERNAL_SERVER_ERROR.getMessage(), null);
    }

    public static <T> R<T> fail(ResultCode resultCode) {
        return new R<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    public static <T> R<T> fail(ResultCode resultCode, T data) {
        return new R<>(resultCode.getCode(), resultCode.getMessage(), data);
    }
}
