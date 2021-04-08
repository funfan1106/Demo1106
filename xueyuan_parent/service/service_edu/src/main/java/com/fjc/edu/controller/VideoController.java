package com.fjc.edu.controller;


import com.fjc.commonutils.R;
import com.fjc.edu.client.VodClient;
import com.fjc.edu.entity.Video;
import com.fjc.edu.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-28
 */
@RestController
@RequestMapping("/edu/video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody Video video){
        videoService.save(video);
        return R.ok();
    }

    //删除小节(带视频)
    @DeleteMapping("deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
        Video video = videoService.getById(id);
        String videoSourceId = video.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeAlyVideo(videoSourceId);
        }

        //删除小节
        videoService.removeById(id);
        return R.ok();
    }

    //修改小节
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody Video video){
        videoService.updateById(video);
        return R.ok();
    }

    //查询小节
    @GetMapping("getVedio/{id}")
    public R getVedio(@PathVariable String id){
        Video video = videoService.getById(id);
        return R.ok().data("video",video);
    }

}

