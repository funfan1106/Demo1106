package com.fjc.vod.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @program: guli_parent
 * @description: 初始化对象
 * @author: Mr.Fan
 * @create: 2021-03-11 17:12
 **/
public class InitVodClient {
    public static DefaultAcsClient initVodClient(String accessKeyId,String accessKeySecret) throws Exception{
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId,accessKeyId,accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
