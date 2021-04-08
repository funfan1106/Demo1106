package com.fjc.edu.client;

import com.fjc.commonutils.R;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: guli_parent
 * @description: 熔断器的实现类
 * @author: Mr.Fan
 * @create: 2021-03-13 22:31
 **/
@Service
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频失败");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频失败!");
    }
}
