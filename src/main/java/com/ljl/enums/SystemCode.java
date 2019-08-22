package com.ljl.enums;

import com.ljl.util.common.CodeStatus;

/**
 * @author lvjunlong
 * @date 2019/8/22 上午9:35
 */
public enum SystemCode implements CodeStatus {
    SUCCESS("2000", "成功"),
    NEED_LOGIN("2001", "未登录"),
    ACCOUNT_ERROR("2002", "账户异常"),
    NEED_AUTH("2003", "权限不足"),
    PARAM_ERROR("2005", "参数异常"),
    SERVER_ERROR("2006", "服务器异常"),
    SIGN_EMPTY("2007", "签名为空"),
    SIGN_ERROR("2008", "签名不对"),
    APPKEY_EMPTY("2009", "AppKey为空");

    private String code;
    private String message;

    private SystemCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
