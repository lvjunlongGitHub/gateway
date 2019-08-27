package com.ljl.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * <P>
 *     拦截器入口
 * </P>
 * @author lvjunlong
 * @date 2019/8/27 下午2:12
 */
@Configuration
public class BaseInterceptor implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        registry.addInterceptor(httpInterceptor());
//        registry.addInterceptor(corsInterceptor()).addPathPatterns("/web/**");
//        registry.addInterceptor(loginInterceptor());

    }

    @Bean
    public HandlerInterceptor httpInterceptor() {
        return new HttpInterceptor();
    }

    @Bean
    public HandlerInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public HandlerInterceptor corsInterceptor() {
        return new CorsInterceptor();
    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(0, new JsonParameterBinder());
//    }
}
