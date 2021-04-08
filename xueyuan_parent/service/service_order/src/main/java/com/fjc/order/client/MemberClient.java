package com.fjc.order.client;

import com.fjc.commonutils.ordervo.MemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient("service-ucenter")
public interface MemberClient {

    //根据用户id获取用户信息
    @PostMapping("/ucenter/member/getInfoUc/{id}")
    public MemberOrder getInfoUc(@PathVariable String id);
}
