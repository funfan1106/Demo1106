package com.fjc.oss.controller;

import com.fjc.commonutils.R;
import com.fjc.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: guli_parent
 * @description:
 * @author: Mr.Fan
 * @create: 2021-02-23 18:35
 **/
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin //解决跨域问题
public class OssController {
    @Autowired
    private OssService ossService;

    //上传头像的方法
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        //获取MultipartFile文件
        //返回上传到OSS路径
        String url = ossService.uploadOssFile(file);
        return R.ok().data("url",url);
    }
}
