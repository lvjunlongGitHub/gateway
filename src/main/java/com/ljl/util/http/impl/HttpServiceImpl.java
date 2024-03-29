package com.ljl.util.http.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ljl.enums.SystemCode;
import com.ljl.util.common.ApiResult;
import com.ljl.util.common.CommonResponse;
import com.ljl.util.common.OperationResult;
import com.ljl.util.http.HTTP;
import com.ljl.util.http.HttpService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http
 */
@SuppressWarnings("unchecked")
public class HttpServiceImpl implements HttpService {

    @Autowired
    private HTTP httpClient;

    @Override
    public <T extends CommonResponse> T call(String service, String methodName, Map<String, Object> params, Class<T> clazz) {
        if (StringUtils.isEmpty(service) || StringUtils.isEmpty(methodName) || null == clazz) {
            throw new IllegalArgumentException("远程调用请求错误：service , methodName, clazz不能为空");
        }

        T resp = null;
        try {
            Map<String, String> data = new HashMap<>();
            if (null != params) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    data.put(entry.getKey(), entry.getValue() == null ? "" : entry.getValue().toString());
                }
            }
            data.put("requestId", String.valueOf(System.currentTimeMillis()));

            String url = service + (service.endsWith("/") ? "" : "/") +
                    StringUtils.replace(methodName, ".", "/");
            byte[] bytes = httpClient.postBodyParam(url, data);

            resp = JSON.parseObject(new String(bytes), clazz);

        } catch (Exception e) {
            try {
                //SystemLogger.error(e, "HttpServiceImpl call  POST请求异常");
                resp = clazz.newInstance();
            } catch (Exception ex) {
                //SystemLogger.error(ex, "HttpServiceImpl 解析http响应错误");
            }
        }
        return resp;
    }

    /**
     * 远程调用接口
     *
     * @param url
     * @param uri
     * @param params
     * @param clazz
     * @return
     */
    @Override
    public <T> OperationResult<T> callRemote(String url, String uri, Map<String, Object> params, Class<T> clazz) {
        /**发送接口请求*/
        ApiResult apiResult = call(url, uri, params, ApiResult.class);

        if (null == apiResult || StringUtils.isBlank(apiResult.getCode())) {
            return new OperationResult(SystemCode.SERVER_ERROR);
        }

        if(!apiResult.getCode().equals(SystemCode.SUCCESS.getCode())){
            return new OperationResult(apiResult.getCode(), apiResult.getMessage());
        }

        if (clazz == String.class || clazz == List.class || null == apiResult.getData()) {
            return new OperationResult<>((T) apiResult.getData());
        }

        return new OperationResult<>(JSONObject.parseObject(apiResult.getData().toString(), clazz));
    }

}
