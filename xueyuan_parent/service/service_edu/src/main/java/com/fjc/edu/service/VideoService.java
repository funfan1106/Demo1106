package com.fjc.edu.service;

import com.fjc.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-28
 */
public interface VideoService extends IService<Video> {

    void removeVideoByCourseId(String courseId);
}
