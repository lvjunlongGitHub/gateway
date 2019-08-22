package com.ljl.feigncall;

import com.ljl.entity.Channel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lvjunlong
 * @date 2019/8/22 上午9:47
 */
@FeignClient(name = "springdemo", url = "${springdemo.host}")
@RequestMapping(value = "/channel", produces = "application/json;charset=UTF-8")
public interface FeignApi {

    @RequestMapping(value = "getChannel", method = RequestMethod.POST)
    Channel getChannel(@RequestParam("channelId") Integer channelId);
}
