package com.ljl.util.http;

import com.ljl.util.common.CommonResponse;
import com.ljl.util.common.OperationResult;

import java.util.Map;

/**
 * @author yangjh
 */
public interface HttpService {

    <T extends CommonResponse> T call(String service, String methodName, Map<String, Object> params, Class<T> clazz);

    /**
     * 远程调用接口
     * @param url
     * @param uri
     * @param params
     * @param clazz
     * @param <T>
     * @return
     */
    <T> OperationResult<T> callRemote(String url, String uri, Map<String, Object> params, Class<T> clazz);

}
