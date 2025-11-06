package com.zephyra.ai.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 统一请求返回码
 *
 * @author yorky
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    SUCCESS(200, "请求成功"),

    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未认证的身份"),
    VERIFIED_FAILED(4010, "身份认证失败: 账号或密码错误"),
    USERNAME_NOT_EXIST(4011, "身份认证失败: 用户名不存在"),
    CODE_VERIFIED_FAILED(4012, "身份认证失败: 验证码错误或失效"),
    EMAIL_VERIFIED_FAILED(4013, "身份认证失败: 邮箱验证未通过"),
    OOS_CHECKING_FAILED(4014, "身份认证失败: 已在异地登录"),
    TEMP_TOKEN_INVALID(4015, "身份认证失败: 临时 Token 无效或已过期"),
    ILLEGAL_REQUEST(4016, "请求核验失败: 请求 IP 不合法"),

    FORBIDDEN(403, "权限不足"),
    TOKEN_INVALID(4030, "身份认证失败: Token 无效或已过期"),

    NOT_FOUND(404, "请求资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不支持"),
    REQUEST_TIMEOUT(408, "请求超时"),
    PARAMS_ERROR(422, "参数验证失败"),

    INTERNAL_SERVER_ERROR(500, "服务器内部错误, 请联系管理员上报错误"),
    EXECUTING(5000, "服务器正在执行中, 请稍后再试"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),

    NO_DATA_FOUND(1006, "查询无数据"),
    OPERATION_FAILED(1007, "操作失败");

    private final Integer code;

    private final String message;
}