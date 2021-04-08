package com.fjc.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fjc.edu.entity.Course;
import com.fjc.edu.entity.CourseDescription;
import com.fjc.edu.entity.frontvo.CourseQueryVo;
import com.fjc.edu.entity.frontvo.CourseWebVo;
import com.fjc.edu.entity.vo.CourseInfoVo;
import com.fjc.edu.entity.vo.CoursePublishVo;
import com.fjc.edu.mapper.CourseMapper;
import com.fjc.edu.service.ChapterService;
import com.fjc.edu.service.CourseDescriptionService;
import com.fjc.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fjc.edu.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.awt.geom.QuadCurve2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-28
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ChapterService chapterService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {

        //1.向课程表里添加基本课程信息
        //对象转化将courseInfoVo转成course
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        int insert = baseMapper.insert(course);
        if(insert == 0 ){
            System.out.println("添加基本课程信息失败");
        }

        //添加课程后得到id
        String cid = course.getId();

        //2.向课程简介中添加课程简介
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //将第一次得到的课程id赋给描述的
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1.查询课程表
        Course course = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course,courseInfoVo);

        //2.查询描述表
        CourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1.修改课程表
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        int update = baseMapper.updateById(course);
        if(update == 0){
            System.out.println("修改课程失败");
        }

        //2.修改描述表
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(courseInfoVo.getId());
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(courseDescription);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo coursePublishVo = baseMapper.getPublishCourseInfo(id);
        return  coursePublishVo;
    }

    @Override
    public void removeCourse(String courseId) {
        //1.删除小节
        videoService.removeVideoByCourseId(courseId);
        //2..删除章节
        chapterService.removeChapterByCourseId(courseId);
        //3.删除描述
        courseDescriptionService.removeById(courseId);
        //4.删除课程
        int result = baseMapper.deleteById(courseId);
        if (result==0){
            System.out.println("删除失败");
        }

    }

    @Override
    public Map<String, Object> pageListWeb(Page<Course> pageParam, CourseQueryVo courseQuery) {

        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        //判断条件是否为空 不为空拼接
        if (!StringUtils.isEmpty(courseQuery.getSubjectParentId())) {
            wrapper.eq("subject_parent_id", courseQuery.getSubjectParentId());
        }

        if (!StringUtils.isEmpty(courseQuery.getSubjectId())) {
            wrapper.eq("subject_id", courseQuery.getSubjectId());
        }

        if (!StringUtils.isEmpty(courseQuery.getBuyCountSort())) {
            wrapper.orderByDesc("buy_count");
        }

        if (!StringUtils.isEmpty(courseQuery.getGmtCreateSort())) {
            wrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(courseQuery.getPriceSort())) {
            wrapper.orderByDesc("price");
        }

        //查询
        baseMapper.selectPage(pageParam,wrapper);

        //封装
        List<Course> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public CourseWebVo selectInfoWebById(String courseId) {
        return baseMapper.selectInfoWebById(courseId);
    }
}
