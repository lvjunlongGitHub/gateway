package com.ljl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljl.entity.Channel;

/**
 * <p>
 * 渠道表 服务类
 * </p>
 *
 * @author lvjunlong
 * @since 2019-08-20
 */
public interface ChannelService extends IService<Channel> {

    /**
     * 获取渠道信息
     * @param channelId 渠道ID
     * @return 渠道信息
     */
    Channel getChannel(Integer channelId);

}
