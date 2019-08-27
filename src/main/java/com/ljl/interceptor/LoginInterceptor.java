package com.ljl.interceptor;

import com.alibaba.fastjson.JSON;
import com.ljl.annotation.Anonymous;
import com.ljl.common.JsonResult;
import com.ljl.constant.RequestUser;
import com.ljl.logging.AccessLog;
import com.ljl.logging.GlobalConstant;
import com.ljl.util.cache.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <P>
 *     登录拦截器，拦截非登录态的接口调用
 * </P>
 * @author lvjunlong
 * @date 2019/8/27 下午2:43
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private CookieUtil cookieUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Anonymous anonymous = (handler instanceof HandlerMethod) ?
                ((HandlerMethod) handler).getMethodAnnotation(Anonymous.class) : null;
        if (null != anonymous && anonymous.value()) {
            return true;
        }

        String token = cookieUtil.getValue(request, CookieUtil.TOKEN);
        //RequestUser.User user = adminManageService.identify(token);
        RequestUser.User user = new RequestUser.User();
        if(null == user) {
            JsonResult jsonResult = new JsonResult(null);
            response.getOutputStream().write(JSON.toJSONString(jsonResult).getBytes());
            return false;
        }

        //延长cookie
        cookieUtil.setToken(request, response, token);
        RequestUser.put(user);

        AccessLog accessLog = GlobalConstant.accessLog.get();
        accessLog.setUid(RequestUser.getId().toString());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestUser.clear();
        super.afterCompletion(request, response, handler, ex);
    }
}
