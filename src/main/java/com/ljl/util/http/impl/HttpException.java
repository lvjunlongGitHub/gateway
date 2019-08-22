package com.ljl.util.http.impl;

import okhttp3.Response;

import java.io.IOException;

/**
 * <P>
 *     http自定义异常类
 * </P>
 * @author lvjunlong
 * @date 2019/8/22 上午9:26
 */
public class HttpException extends IOException {

    private Response response;
    private static final long serialVersionUID = -4802242016364002941L;

    public HttpException(IOException e) {
        super(e);
    }

    public HttpException(Response response, String message) {
        super(message);
        this.setResponse(response);
    }

    /**
     * @return the response
     */
    public Response getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(Response response) {
        this.response = response;
    }

}
