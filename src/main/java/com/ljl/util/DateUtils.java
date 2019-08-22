package com.ljl.util;

/**
 * @author lvjunlong
 * @date 2019/8/21 上午9:29
 */
public class DateUtils {

    /**
     * 获取当前系统时间
     *
     * @return  unix时间戳
     */
    public static int getCurrentSecond() {
        return Long.valueOf(System.currentTimeMillis() / 1000).intValue();
    }
}
