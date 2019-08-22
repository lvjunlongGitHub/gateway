package com.ljl.util.http;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

/**
 * @author lvjunlong
 * @date 2019/7/29 上午9:16
 */
public interface HTTP {

    /**
     * json格式post请求
     * @param url       请求url
     * @param jsonBody  请求参数
     * @throws           IOException
     * @return byte数组
     */
    byte[] postJsonParam(String url, String jsonBody) throws IOException;

    /**
     * 表单格式post请求
     * @param baseUrl
     * @param bodyParams
     */
    byte[] postBodyParam(String baseUrl, Map<String, String> bodyParams) throws IOException;

    /**
     * xml内容，POST同步请求
     * @param url
     * @param xmlBody
     */
    byte[] postXml(String url, String xmlBody) throws IOException;

    /**
     * get同步请求
     * @param baseUrl 请求url
     * @param queryParams 请求内容
     * @throws IOException
     */
    byte[] get(String baseUrl, Map<String, String> queryParams) throws IOException;

    /**
     * 请求方法
     * @param request
     * @return 请求
     * @throws IOException
     */
    Response reqExecute(Request request) throws IOException;

    /**
     * 构造CALL方法
     * @param request 请求
     * @return call
     */
    Call reqExecuteCall(Request request);

}
