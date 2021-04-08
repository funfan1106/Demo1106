package com.fjc.msm.controller;


import com.fjc.commonutils.R;
import com.fjc.msm.service.MsmService;
import com.fjc.msm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: guli_parent
 * @description:
 * @author: Mr.Fan
 * @create: 2021-03-18 19:16
 **/
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmApiController {

    @Autowired
    private MsmService msmService;


    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone){
        String code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        boolean isSend = msmService.send(param,phone);
        if(isSend) {
            return R.ok();
        }else {
            return R.error().message("短信发送失败");
        }
    }
}
