package com.ljl.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ljl.util.DateUtils;
import org.apache.ibatis.reflection.MetaObject;

/**
 * <P>
 *     mybatis-plus 自动填充
 * </P>
 * @author lvjunlong
 * @date 2019/8/21 上午9:26
 */
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Integer now = DateUtils.getCurrentSecond();

        Object ctime = getFieldValByName("ctime", metaObject);
        if (null == ctime) {
            setFieldValByName("ctime", now, metaObject);
        }

        Object utime = getFieldValByName("utime", metaObject);
        if (null == utime) {
            setFieldValByName("utime", now, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        Integer now = DateUtils.getCurrentSecond();

        Object utime = getFieldValByName("utime", metaObject);
        if (null == utime) {
            setFieldValByName("utime", now, metaObject);
        }
    }

}
