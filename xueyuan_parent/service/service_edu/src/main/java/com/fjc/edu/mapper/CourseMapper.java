package com.fjc.edu.mapper;

import com.fjc.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fjc.edu.entity.frontvo.CourseWebVo;
import com.fjc.edu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-02-28
 */
public interface CourseMapper extends BaseMapper<Course> {

    public CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo selectInfoWebById(String courseId);
}
