package com.ljl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P>
 *    Controller访问控制，游客模式访问
 * </P>
 * @author lvjunlong
 * @date 2019/8/27 下午2:41
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Anonymous {
    boolean value() default true;
}
