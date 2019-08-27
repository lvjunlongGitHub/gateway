package com.ljl.logging;

/**
 * <P>
 *     全局常量定义
 * </P>
 * @author lvjunlong
 * @date 2019/8/22 下午7:11
 */
public class GlobalConstant {

    static final String SQL_TRACE = "SQLTRACE";

    static final String ACCESS_TRACE = "ACCESSTRACE";

    static final String GWS_LOG = "GWS";

    static final String RocketmqCommon = "RocketmqCommon";

    static final String RocketmqRemoting = "RocketmqRemoting";

    static final String RocketmqClient = "RocketmqClient";

    static final String ES_LOG = "ESLOG";

    static final String CON_QUOTE = "`";

    public static ThreadLocal<AccessLog> accessLog= new ThreadLocal<AccessLog>();

    public static ThreadLocal<SqlAccessLog> sqlLog= new ThreadLocal<SqlAccessLog>();


    public static int SID_COOKIE_MAXAGE = 60*60*24*30;

    public static String SID_COOKIE_NAME ="sid";
}
