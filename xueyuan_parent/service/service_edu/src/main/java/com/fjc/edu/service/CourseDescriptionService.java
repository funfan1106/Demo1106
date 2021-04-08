package com.fjc.edu.service;

import com.fjc.edu.entity.CourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-28
 */
public interface CourseDescriptionService extends IService<CourseDescription> {

    void removeDescByCourseID(String courseId);
}
