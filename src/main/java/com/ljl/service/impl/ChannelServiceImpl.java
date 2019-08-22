package com.ljl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljl.entity.Channel;
import com.ljl.mapper.ChannelMapper;
import com.ljl.service.ChannelService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 渠道表 服务实现类
 * </p>
 *
 * @author lvjunlong
 * @since 2019-08-20
 */
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements ChannelService {

    /**
     * 获取渠道信息
     *
     * @param channelId 渠道ID
     * @return 渠道信息
     */
    @Override
    public Channel getChannel(Integer channelId) {
        return baseMapper.selectById(channelId);
    }
}
