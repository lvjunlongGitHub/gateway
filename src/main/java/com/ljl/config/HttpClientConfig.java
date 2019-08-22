package com.ljl.config;

import com.ljl.util.http.HTTP;
import com.ljl.util.http.impl.HttpImpl;
import com.ljl.util.http.impl.HttpLoggerInterceptor;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * <P>
 *     http client配置
 * </P>
 * @author lvjunlong
 * @date 2019/8/22 上午9:41
 */
@Configuration
@EnableAutoConfiguration
public class HttpClientConfig {

    /**
     * 单位：毫秒
     */
    @Value("${http.connectTimeout:10000}")
    private Long connectTimeout = 10000L;
    @Value("${http.readTimeout:10000}")
    private Long readTimeout = 10000L;
    @Value("${http.writeTimeout:10000}")
    private Long writeTimeout = 10000L;

    /**
     * 单位：分钟
     */
    @Value("${http.maxIdleConnections:5}")
    private int maxIdleConnections = 5;
    @Value("${http.keepAliveDurationNs:5}")
    private Long keepAliveDurationNs = 5L;
    /**
     * 连接失败重试，默认打开开关 避免Unreachable IP addresses，和连接池满了拒绝请求情况
     */
    private static boolean retryOnConnectionFailure = true;

    @Autowired
    private HttpLoggerInterceptor httpLoggerInterceptor;

    @Bean
    public HttpLoggerInterceptor getHttpLoggerInterceptor() {
        return new HttpLoggerInterceptor();
    }

    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        ConnectionPool connectionPool = new ConnectionPool(maxIdleConnections, keepAliveDurationNs, TimeUnit.MINUTES);
        builder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
        builder.readTimeout(readTimeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(writeTimeout, TimeUnit.MILLISECONDS);
        builder.retryOnConnectionFailure(retryOnConnectionFailure);
        builder.connectionPool(connectionPool);
        //增加监控
        builder.addInterceptor(httpLoggerInterceptor);
        //由于是服务对服务的 所以不用设置访问所有的HTTPS站点配置
        return builder.build();
    }

    @Bean
    public HTTP httpFactory() {
        return new HttpImpl();
    }
}
