package com.ljl.constant;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <P>
 *     请求辅助工具类
 * </P>
 * @author lvjunlong
 * @date 2019/8/27 下午2:04
 */
public class RequestUser {

    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

    public static void put(User user) {
        currentUser.set(user);
    }

    public static Integer getId() {
        RequestUser.User user = currentUser.get();
        return null == user ? null : user.getId();
    }

    public static String getName() {
        RequestUser.User user = currentUser.get();
        return null == user ? null : user.getName();
    }

    public static Integer getAge() {
        RequestUser.User user = currentUser.get();
        return null == user ? null : user.getAge();
    }

    public static void clear() {
        currentUser.remove();
    }


    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public static class User implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 用户唯一表示
         */
        private Integer id;

        /**
         * 香茗
         */
        private String name;

        /**
         * 年龄
         */
        private Integer age;
    }
}
