package com.ljl.interceptor;

import com.ljl.logging.AccessLog;
import com.ljl.logging.GlobalConstant;
import com.ljl.util.IPUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <P>
 *     http拦截器
 * </P>
 * @author lvjunlong
 * @date 2019/8/27 下午2:29
 */
public class HttpInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ua = request.getHeader("User-Agent");
        String ip = IPUtils.getIpAddr(request);
        String uri = request.getRequestURI();
        String referer = request.getHeader("Referer");

        AccessLog accessLog = new AccessLog();
        //accessLog.setStartTime(System.currentTimeMillis());

        accessLog.setIp(ip);
        //accessLog.setAction(uri);
        accessLog.setUa(ua);
        accessLog.setReferer(referer);

        GlobalConstant.accessLog.set(accessLog);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AccessLog accessLog = GlobalConstant.accessLog.get();
        //accessLog.setFinishTime(System.currentTimeMillis());
        //SystemLogger.getLogger(LoggerEnum.ACCESSTRACE).info(accessLog.toString());

        super.afterCompletion(request, response, handler, ex);
    }
}
