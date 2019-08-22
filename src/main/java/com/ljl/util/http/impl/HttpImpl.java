package com.ljl.util.http.impl;

import com.ljl.util.http.HTTP;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

/**
 * @author lvjunlong
 * @date 2019/7/29 上午9:21
 */
public class HttpImpl implements HTTP {

    /**
     * 自定义配置，见类HttpClientConfig.java
     */
    @Autowired
    private OkHttpClient httpClient;

    private final MediaType MEDIA_JSON = MediaType.parse("application/json; charset=utf-8");
    private final MediaType MEDIA_XML = MediaType.parse("application/xml; charset=utf-8");

    /**
     * json格式post请求
     * @param url 请求路径
     * @param jsonBody 请求内容
     * @throws IOException
     */
    @Override
    public byte[] postJsonParam(String url, String jsonBody) throws IOException{
        return post(url, jsonBody, MEDIA_JSON);
    }

    /**
     * 表单格式post请求
     * @param baseUrl
     * @param bodyParams
     */
    @Override
    public byte[] postBodyParam(String baseUrl, Map<String, String> bodyParams) throws IOException {
        return postBody(baseUrl, bodyParams);
    }

    /**
     * xml内容，POST同步请求
     *
     * @param url
     * @param xmlBody
     */
    @Override
    public byte[] postXml(String url, String xmlBody) throws IOException{
        return post(url, xmlBody, MEDIA_XML);

    }

    @Override
    public byte[] get(String baseUrl, Map<String, String> queryParams) throws IOException {
        return getParam(baseUrl, queryParams);
    }

    @Override
    public Response reqExecute(Request request) throws IOException {
        return reqExecuteCall(request).execute();
    }

    @Override
    public Call reqExecuteCall(Request request) {
        return httpClient.newCall(request);
    }

    /**
     * post同步请求
     * @param url 请求路径
     * @param string 请求内容
     * @param mediaType 内容格式 MEDIA_JSON：json内容；MEDIA_XML：xml内容
     * @return json字符串
     * @throws IOException
     */
    private byte[] post(String url, String string, MediaType mediaType) throws IOException{
        RequestBody body = RequestBody.create(mediaType, string);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = reqExecute(request);
        if (!response.isSuccessful()) {
            throw new HttpException(response, "exception code:" + response.code());
        }
        return response.body().bytes();

    }

    /**
     * post表单格式内容请求
     * @param baseUrl 请求路径
     * @param bodyParams 请求内容
     * @return
     * @throws IOException
     */
    private byte[] postBody(String baseUrl, Map<String, String> bodyParams) throws IOException {
        //encode params
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> item : bodyParams.entrySet()) {
            bodyBuilder.addEncoded(item.getKey(), item.getValue());
        }
        FormBody formBody = bodyBuilder.build();
        //发送请求
        Request request = new Request.Builder()
                .url(baseUrl).post(formBody)
                .build();

        Response response = reqExecute(request);
        if (!response.isSuccessful()) {
            throw new HttpException(response, "exception code:" + response.code());
        }
        return response.body().bytes();
    }

    /**
     * get同步请求
     * @param baseUrl
     * @param queryParams
     * @return
     * @throws IOException
     */
    private byte[] getParam(String baseUrl, Map<String, String> queryParams) throws IOException {
        //拼装param
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        for (Map.Entry<String, String> item : queryParams.entrySet()) {
            urlBuilder.addQueryParameter(item.getKey(), item.getValue());
        }
        HttpUrl httpUrl = urlBuilder.build();
        //发送请求
        Request request = new Request.Builder()
                .url(httpUrl.toString()).get()
                .build();
        Response response = reqExecute(request);
        if (!response.isSuccessful()) {
            throw new HttpException(response, "exception code:" + response.code());
        }
        return response.body().bytes();
    }

}
