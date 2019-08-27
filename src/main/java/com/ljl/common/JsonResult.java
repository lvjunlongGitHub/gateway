package com.ljl.common;

import com.alibaba.fastjson.JSON;
import com.ljl.util.common.CodeStatus;

import java.io.Serializable;

/**
 * <P>
 *     接口请求统一JSON格式响应
 * </P>
 * @author lvjunlong
 * @date 2019/8/27 下午2:38
 */
public class JsonResult<T> implements Serializable {

    private String code;

    private String message;

    private T data;

    public JsonResult(CodeStatus codeStatus) {
        this.code = codeStatus.getCode();
        this.message = codeStatus.getMessage();
    }

    public JsonResult(CodeStatus codeStatus, T data) {
        this.code = codeStatus.getCode();
        this.message = codeStatus.getMessage();
        this.data = data;
    }

    public JsonResult(String code, String message, T data) {
        this.code = code ;
        this.message =message ;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
