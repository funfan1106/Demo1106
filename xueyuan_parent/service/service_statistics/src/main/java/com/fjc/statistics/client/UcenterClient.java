package com.fjc.statistics.client;

import com.fjc.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

        @GetMapping(value = "/ucenter/member/countregister/{day}")
        public R registerCount(@PathVariable("day") String day);

}
