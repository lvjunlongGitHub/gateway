package com.ljl.util.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * <P>
 *     cookie工具类
 * </P>
 * @author lvjunlong
 * @date 2019/8/27 下午1:52
 */
@Configuration
public class CookieUtil {

    @Value("${gateway.cookie.domain}")
    private String domain;

    @Value("${gateway.cookie.maxAge}")
    private Integer maxAge;

    public static final String TOKEN = "token";

    /**
     * 设置cookie
     * @param request 请求
     * @param response 响应
     * @param cookieValue value
     */
    public void setToken(HttpServletRequest request, HttpServletResponse response, String cookieValue) {
        setCookie(request, response, TOKEN, cookieValue, "/", maxAge);
    }

    public void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                          String cookieValue, String path, int maxAge) {
        try {
            Cookie cookie = new Cookie(cookieName, URLEncoder.encode(cookieValue, "utf-8"));
            cookie.setMaxAge(maxAge);
            cookie.setPath(path);
            cookie.setDomain(domain);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取cookie值
     *
     * @param request 请求
     * @param name key
     * @return cookie
     */
    public String getValue(HttpServletRequest request, String name) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    try {
                        value = URLDecoder.decode(cookie.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                    break;
                }
            }
        }
        return value;
    }

    /**
     * 删除cookie信息
     *
     * @param response 响应
     * @param name key
     */
    public void delCookie(HttpServletRequest request, HttpServletResponse response,
                          String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            // 遍历浏览器发送到服务器端的所有Cookie，找到自己设置的Cookie
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (cookieName.equals(name)) {
                    cookie = new Cookie(name, null);
                    // 设置Cookie立即失效
                    cookie.setMaxAge(0);
                    /*
                     * 删除Cookie时，只设置maxAge=0将不能够从浏览器中删除cookie,
                     * 因为一个Cookie应当属于一个path与domain，所以删除时，Cookie的这两个属性也必须设置。
                     */
                    cookie.setDomain(domain);
                    // 必须设置path属性的值
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }
}
