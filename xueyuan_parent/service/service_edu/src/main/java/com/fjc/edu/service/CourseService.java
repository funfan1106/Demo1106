package com.fjc.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fjc.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fjc.edu.entity.frontvo.CourseQueryVo;
import com.fjc.edu.entity.frontvo.CourseWebVo;
import com.fjc.edu.entity.vo.CourseInfoVo;
import com.fjc.edu.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-28
 */
public interface CourseService extends IService<Course> {

    //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //做信息回显 根据ID查询出课程信息
    CourseInfoVo getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String, Object> pageListWeb(Page<Course> pageParam, CourseQueryVo courseQuery);

    CourseWebVo selectInfoWebById(String courseId);
}
