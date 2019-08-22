package com.ljl;

import com.ljl.entity.Channel;
import com.ljl.service.ChannelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lvjunlong
 * @date 2019/8/21 下午8:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class UserTest {

    @Autowired
    private ChannelService channelService;

    @Test
    public void testChannel() {
        Channel channel = channelService.getChannel(16);
        System.out.println(channel);
    }

}
