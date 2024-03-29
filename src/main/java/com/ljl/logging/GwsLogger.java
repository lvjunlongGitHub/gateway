package com.ljl.logging;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <P>
 *     全局log类
 * </P>
 * @author lvjunlong
 * @date 2019/8/22 下午7:19
 */
public final class GwsLogger {

    //定义基础的三个Log日志
    private static Logger accessLog = LogManager.getLogger(GlobalConstant.ACCESS_TRACE);
    private static Logger sqltracelog = LogManager.getLogger(GlobalConstant.SQL_TRACE);
    private static Logger gwslog = LogManager.getLogger(GlobalConstant.GWS_LOG);
    private static Logger rocketmqCommonLog = LogManager.getLogger(GlobalConstant.RocketmqCommon);
    private static Logger rocketmqRemotingLog = LogManager.getLogger(GlobalConstant.RocketmqRemoting);
    private static Logger rocketmqClientLog = LogManager.getLogger(GlobalConstant.RocketmqClient);
    private static Logger eslog = LogManager.getLogger(GlobalConstant.ES_LOG);

    private static ConcurrentHashMap<GwsLoggerTypeEnum, Logger> logMaps = new ConcurrentHashMap<>();

    static{
        logMaps.put(GwsLoggerTypeEnum.GWS, gwslog);
        logMaps.put(GwsLoggerTypeEnum.ACCESSTRACE, accessLog);
        logMaps.put(GwsLoggerTypeEnum.SQLTRACE, sqltracelog);

        logMaps.put(GwsLoggerTypeEnum.RocketmqCommon, rocketmqCommonLog);
        logMaps.put(GwsLoggerTypeEnum.RocketmqRemoting, rocketmqRemotingLog);
        logMaps.put(GwsLoggerTypeEnum.RocketmqClient, rocketmqClientLog);
        logMaps.put(GwsLoggerTypeEnum.ESLOG, eslog);
    }

    /**
     *
     * 全局日志logger调用
     *
     * @author liuyi 2016年4月19日
     * @param loggerType
     * @return
     */
    public static Logger getLogger(GwsLoggerTypeEnum loggerType){
        Logger logger= logMaps.get(loggerType);
        if(logger==null){
            //发现未定义的日志发现未创建，则自动创建，只需在枚举类创建即可
            logger =logMaps.put(loggerType, LogManager.getLogger(loggerType));
        }
        return logger;
    }

    /**
     *
     * 全局日志默认GWS枚举
     *
     * @author liuyi 2016年4月19日
     * @return
     */
    public static Logger getLogger(){
        return logMaps.get(GwsLoggerTypeEnum.GWS);
    }

    /**
     *
     * 输出已定义日志类别的info日志
     *
     * @author liuyi 2016年4月20日
     * @param loggerType
     * @param msg
     * @param args
     */
    public static void info(GwsLoggerTypeEnum loggerType,String msg, Object... args){
        Logger logger = getLogger(loggerType);
        msg = addImportantToLog(msg);
        logger.info(msg, args);
    }

    /**
     *
     * 输出已定义日志类别的info日志
     *
     * @author liuyi 2016年4月20日
     * @param loggerType
     * @param throwable
     * @param msg
     * @param args
     */
    public static void info(GwsLoggerTypeEnum loggerType,Throwable throwable, String msg, Object... args) {
        Logger logger = getLogger(loggerType);
        msg = addImportantToLog(format(msg,args));
        logger.info(msg, throwable);
    }

    /**
     *
     * 输出默认的GWS类别info日志
     *
     * @author liuyi 2016年4月20日
     * @param msg
     * @param args
     */
    public static void info(String msg, Object... args){
        Logger logger = getLogger(GwsLoggerTypeEnum.GWS);
        msg = addImportantToLog(msg);
        logger.info(msg, args);
    }

    /**
     *
     * 输出默认的GWS类别info日志
     *
     * @author liuyi 2016年4月20日
     * @param throwable
     * @param msg
     * @param args
     */
    public static void info(Throwable throwable, String msg, Object... args) {
        Logger logger = getLogger();
        msg = addImportantToLog(format(msg,args));
        logger.info(msg, throwable);
    }


    /**
     *
     *  输出默认的GWS类别debug日志
     *
     * @author liuyi 2016年4月20日
     * @param msg
     * @param args
     */
    public static void debug(String msg, Object... args){
        Logger logger = getLogger();
        msg = addImportantToLog(msg);
        logger.debug(msg, args);
    }

    /**
     *
     *输出默认GWS日志类别的debug日志
     *
     * @author liuyi 2016年4月20日
     * @param throwable
     * @param msg
     * @param args
     */
    public static void debug(Throwable throwable, String msg, Object... args){
        Logger logger = getLogger();
        msg = addImportantToLog(format(msg,args));
        logger.debug(msg, throwable);
    }

    /**
     *
     * 输出默认GWS日志类别的错误日志
     *
     * @author liuyi 2016年4月20日
     * @param msg
     * @param args
     */
    public static void error(String msg, Object... args){
        Logger logger = getLogger();
        msg = addImportantToLog(msg);
        logger.error(msg, args);
    }

    /**
     *
     *  输出默认GWS日志类别的错误日志
     *
     * @author liuyi 2016年4月20日
     * @param throwable
     * @param msg
     * @param args
     */
    public static void error(Throwable throwable, String msg, Object... args){
        Logger logger = getLogger();
        msg = addImportantToLog(format(msg,args));
        logger.error(msg, throwable);
    }

    /**
     *
     * 增加重要信息到日志中
     *
     * @author liuyi 2016年4月20日
     * @param msg
     * @return
     */
    private static String addImportantToLog(String msg) {
//		AccessLog threadData = GlobalConstant.accessLog.get();
//		String action = "";
//
//		if (threadData != null) {
//			StringBuilder sb = new StringBuilder();
//			sb.append(msg);
//			if (!msg.endsWith(GlobalConstant.CON_QUOTE)) {
//				sb.append(GlobalConstant.CON_QUOTE);
//			}
//			sb.append(String.format("action={}`userid={}`sid={}`",
//					action, threadData.getUserId(), threadData.getSid()));
//			return sb.toString();
//		} else {
//			return msg;
//		}

        AccessLog accessLog = GlobalConstant.accessLog.get();

        String action = (null != accessLog) ? accessLog.getUri() : StringUtils.EMPTY;
        String sid = (null != accessLog) ? accessLog.getDeviceId() : StringUtils.EMPTY;
        String uid = (null != accessLog) ? accessLog.getUid() : StringUtils.EMPTY;
        String appId = (null != accessLog) ? accessLog.getAppId() : StringUtils.EMPTY;
        String channelId = (null != accessLog) ? accessLog.getChannelId() : StringUtils.EMPTY;

        StringBuilder sb = new StringBuilder();
        sb.append("action=").append(action).append(GlobalConstant.CON_QUOTE);
        sb.append("appId=").append(appId).append(GlobalConstant.CON_QUOTE);
        sb.append("channelId=").append(channelId).append(GlobalConstant.CON_QUOTE);
        sb.append("uid=").append(uid).append(GlobalConstant.CON_QUOTE);
        sb.append("sid=").append(sid).append(GlobalConstant.CON_QUOTE);
        sb.append("msg===>").append(msg);

        return sb.toString();
    }

    /**
     *
     * 格式化文本
     *
     * @author liuyi 2016年4月20日
     * @param format
     * @param args
     * @return
     */
    private static String format(String format, Object... args) {
        if (args != null && args.length > 0) {
            return String.format(format, args);
        } else {
            return format;
        }
    }
}
