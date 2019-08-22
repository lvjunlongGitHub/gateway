package com.ljl.annotation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lvjunlong
 * @date 2019/8/21 下午2:48
 */
public class JsonParameterBinder implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(JsonParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Class<?> parameterType = methodParameter.getParameterType();

        String name = methodParameter.getParameterName();

        HttpServletRequest request = nativeWebRequest
                .getNativeRequest(HttpServletRequest.class);

        if (parameterType.getName().equals(Object.class.getName())) {
            throw new RuntimeException(String.format(
                    "no exact type assigned for parameter '%s'", name));

        }

        String value = request.getParameter(name);

        if (value != null) {
            Object obj;

            try {
                obj = JSON.parse(value);
            } catch (Exception e) {
                return null;
            }

            return parseResult(methodParameter, obj);
        }

        return null;
    }

    /**
     * 将JSON对象转换成指定的用户返回值类型
     * @param parameter {@link MethodParameter}
     * @param resultObject {@link Object}
     * @return object
     * @throws JSONException 转换异常
     */
    private Object parseResult(MethodParameter parameter, Object resultObject)
            throws JSONException {
        if (resultObject == null) {
            throw new JSONException("result is empty.");
        }

        Object result;

        Class<?> parameterType = parameter.getParameterType();

        boolean isArray = parameterType.isArray();
        boolean isCollection = Collection.class.isAssignableFrom(parameterType);

        Class<?> componentType = parameterType.getComponentType();

        if ((isArray || isCollection) && resultObject instanceof JSONArray) {

            if (!isArray) {
                componentType = (Class<?>) (((ParameterizedType) parameter
                        .getGenericParameterType()).getActualTypeArguments()[0]);
            }

            JSONArray jsonArray = (JSONArray) resultObject;
            int size = jsonArray.size();

            if (isArray) {
                result = Array.newInstance(componentType, size);
            } else {
                result = new ArrayList(size);
            }

            for (int i = 0; i < size; i++) {

                Object value = jsonArray.getObject(i, componentType);

                if (isArray) {
                    Array.set(result, i, value);
                } else {
                    ((List) result).add(value);
                }
            }
        } else {
            if (resultObject instanceof JSONObject) {
                result = JSONObject.toJavaObject((JSONObject) resultObject,
                        parameterType);
            } else {
                result = TypeUtils.castToJavaBean(resultObject, parameterType);
            }
        }

        return result;
    }
}
